package com.fpoly.Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.beanutils.BeanUtils;


import com.fpoly.Dao.RoomDao;
import com.fpoly.entity.Booking;
import com.fpoly.entity.FeedBack;
import com.fpoly.entity.Room;



import com.fpoly.Dao.BookingDAO;
import com.fpoly.Dao.FeedBackDAO;
import com.fpoly.Dao.PaymentDAO;
import com.fpoly.Dao.UserDAO;

import com.fpoly.entity.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet({ "/admin/management/room-manager", "/admin/management/room-manager/edit/*",
		"/admin/management/room-manager/delete/*", "/admin/management/user-manager",
		"/admin/management/user-manager/index", "/admin/management/user-manager/edit/*",
		"/admin/management/user-manager/update", "/admin/management/payment-manager",
		"/admin/management/feedback-manager", "/admin/management/feedback-manager/delete/*" })

//@WebServlet({ "/admin/management/room-manager","/admin/management/room-manager/edit/*","/admin/management/room-manager/delete/*", "/admin/management/user-manager", "/admin/management/user-manager/index", "/admin/management/user-manager/edit/*",  "/admin/management/user-manager/update", "/admin/management/payment-manager",
//"/admin/management/feedback-manager" })


public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("room-manager")) {
			this.RoomManager(req, resp);
			req.setAttribute("view", "/views/admin/RoomManger.jsp");
		} else if (uri.contains("user-manager")) {
			this.manageUser(req, resp);
			req.setAttribute("view", "/views/admin/UserManager.jsp");
		} else if (uri.contains("payment-manager")) {
			this.doPayment(req, resp);
			req.setAttribute("view", "/views/admin/PaymentManger.jsp");
		} else if (uri.contains("feedback-manager")) {
			FeedBackDAO fbDao = new FeedBackDAO();
			if (uri.contains("delete")) {
				String id = uri.substring(uri.lastIndexOf("/") + 1);
				fbDao.remove(Long.parseLong(id));
			}

			List<FeedBack> fb = fbDao.FindAll();
			System.out.println(fb);
			req.setAttribute("listFB", fb);
			req.setAttribute("view", "/views/admin/FeedBackManger.jsp");

		}
		req.getRequestDispatcher("/views/AdminHome.jsp").forward(req, resp);

	}

	protected void doPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String usn = ss.getAttribute("usn") + "";
		System.out.println("Kiểm tra có đăng nhập chưa: " + usn);
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUsername(usn);
		if (usn == null || usn.equals("")) {
			// Chuyển tới trang đăng nhập
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		} else {
			PaymentDAO dao = new PaymentDAO();
			req.setAttribute("itemsManagerPayment", dao.findAll()); // items: list<User>
		}

	}

	private void manageUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		User user = new User();
		String uri = req.getRequestURI();
		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			user = userDao.findById(Integer.parseInt(id));
		}else if (uri.contains("update")) {
			if (ValidateupdatemanageUser(req, resp)) {
				try {
					BeanUtils.populate(user, req.getParameterMap());

					userDao.update(user);
					req.setAttribute("message", "Cập nhật người dùng thành công");
				} catch (Exception e) {
					req.setAttribute("message", "Lỗi cập nhật người dùng");
					e.printStackTrace();
				}
			}
		}
		req.setAttribute("formManagerUser", user);
		req.setAttribute("itemsManagerUser", userDao.findAllByUse(false));

		BookingDAO bkdao = new BookingDAO();
		Booking booking = new Booking();

		req.setAttribute("itemsId", bkdao.findAll());

	}

//	private boolean ValidatecreatemanageUser(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		String uri = req.getRequestURI();				
//	    Integer userID = null;
//	    Boolean stt = true;  // hoặc true tùy vào trạng thái bạn muốn tìm kiếm
//
//	    if (uri.contains("edit")) {
//	        String id = uri.substring(uri.lastIndexOf("/") + 1);
//	        user = userDao.findById(Integer.parseInt(id));
//	        userID = user.getId();
//	    }
//	    else if (uri.contains("update")) {
//	        if (ValidateupdatemanageUser(req, resp)) {
//	            try {
//	                BeanUtils.populate(user, req.getParameterMap());
//	                userDao.update(user);
//	                req.setAttribute("message", "Cập nhật người dùng thành công");
//	            } catch (Exception e) {
//	                req.setAttribute("message", "Lỗi cập nhật người dùng");
//	                e.printStackTrace();
//	            }
//	        }
//	    } 
//		req.setAttribute("formManagerUser", user);
//		req.setAttribute("itemsManagerUser", userDao.findAllByUse(false));
//								
//	
//		BookingDAO bkdao = new BookingDAO();
//	    List<Booking> bookings = bkdao.finByUserID(userID, stt);
//	    req.setAttribute("itemsId", bookings);
//		
//		
//	}

	

	private boolean ValidateupdatemanageUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean check = true;
		String alertUsername = "";
		String alertFullname = "";
		String alertPassword = "";
		String alertEmail = "";
		String alertPhone = "";
		String username = req.getParameter("username");
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		UserDAO daoUser = new UserDAO();
		User user = daoUser.findByUsername(username);
		if (username == null || username.isEmpty()) {
			alertUsername = "Tên đăng nhập không được bỏ trống!";
			check = false;
		}
		if (fullname == null || fullname.isEmpty()) {
			alertFullname = "Họ và tên không được bỏ trống!";
			check = false;
		}
		String regexPass = "^[a-zA-Z0-9]{8}$";
		Pattern patternPass = Pattern.compile(regexPass);
		Matcher matcherPass = patternPass.matcher(password);
		if (password == null || password.isEmpty()) {
			alertPassword = "Mật khẩu không được bỏ trống!";
			check = false;
		} else if (!matcherPass.matches()) {
			alertPassword = "Mật khẩu không hợp lệ(ít nhất 8 kí tự gồm chữ và số)";
			check = false;
		}

		String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		// Biên dịch biểu thức chính quy
		Pattern patternEmail = Pattern.compile(regexEmail);
		Matcher matcherEmail = patternEmail.matcher(email);
		if (email == null || email.isEmpty()) {
			alertEmail = "Email không được bỏ trống!";
			check = false;
		} else if (!matcherEmail.matches()) {
			alertEmail = "Email không hợp lệ!";
			check = false;
		}
		String regexPhone = "^0\\d{9}$";
		Pattern patternPhone = Pattern.compile(regexPhone);
		Matcher matcherPhone = patternPhone.matcher(phone);
		if (phone == null || phone.isEmpty()) {
			alertPhone = "Số điện thoại không được bỏ trống!";
			check = false;
		} else if (!matcherPhone.matches() || phone.length() != 10) {
			alertPhone = "Số điện thoại không hợp lệ";
			check = false;
		}
		req.setAttribute("alertUsername", alertUsername);
		req.setAttribute("alertFullname", alertFullname);
		req.setAttribute("alertPassword", alertPassword);
		req.setAttribute("alertEmail", alertEmail);
		req.setAttribute("alertPhone", alertPhone);
		return check;
	}

//	private boolean ValidateupdatemanageUser(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		boolean check = true;
//		String alertUsername = "";
//		String alertFullname = "";
//		String alertPassword = "";
//		String alertEmail = "";
//		String alertPhone = "";
//		String username = req.getParameter("username");
//		String fullname = req.getParameter("fullname");
//		String password = req.getParameter("password");
//		String email = req.getParameter("email");
//		String phone = req.getParameter("phone");
//		UserDAO daoUser = new UserDAO();
//		User user = daoUser.findByUsername(username);
//		if (username == null || username.isEmpty()) {
//			alertUsername = "Tên đăng nhập không được bỏ trống!";
//			check = false;
//		}
//		if (fullname == null || fullname.isEmpty()) {
//			alertFullname = "Họ và tên không được bỏ trống!";
//			check = false;
//		}
//		String regexPass = "^[a-zA-Z0-9]{8}$";
//		Pattern patternPass = Pattern.compile(regexPass);
//		Matcher matcherPass = patternPass.matcher(password);
//		if (password == null || password.isEmpty()) {
//			alertPassword = "Mật khẩu không được bỏ trống!";
//			check = false;
//		} else if (!matcherPass.matches()) {
//			alertPassword = "Mật khẩu không hợp lệ(ít nhất 8 kí tự gồm chữ và số)";
//			check = false;
//		}
//
//		String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//		// Biên dịch biểu thức chính quy
//		Pattern patternEmail = Pattern.compile(regexEmail);
//		Matcher matcherEmail = patternEmail.matcher(email);
//		if (email == null || email.isEmpty()) {
//			alertEmail = "Email không được bỏ trống!";
//			check = false;
//		} else if (!matcherEmail.matches()) {
//			alertEmail = "Email không hợp lệ!";
//			check = false;
//		}
//		String regexPhone = "^0\\d{9}$";
//		Pattern patternPhone = Pattern.compile(regexPhone);
//		Matcher matcherPhone = patternPhone.matcher(phone);
//		if (phone == null || phone.isEmpty()) {
//			alertPhone = "Số điện thoại không được bỏ trống!";
//			check = false;
//		} else if (!matcherPhone.matches() || phone.length() != 10) {
//			alertPhone = "Số điện thoại không hợp lệ";
//			check = false;
//		}
//		req.setAttribute("alertUsername", alertUsername);
//		req.setAttribute("alertFullname", alertFullname);
//		req.setAttribute("alertPassword", alertPassword);
//		req.setAttribute("alertEmail", alertEmail);
//		req.setAttribute("alertPhone", alertPhone);
//
//		return check;
//	}

	// code của đoàn đẹp trai
	private void RoomManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomDao dao = new RoomDao();
		Room room = new Room();
		String uri = req.getRequestURI();
		String method = req.getMethod();
		String btn = req.getParameter("btn");

		if (method.equalsIgnoreCase("post")) {
			System.out.println("btn:" + btn);
			if (btn.equalsIgnoreCase("create")) {
				if (validateFormRoom(req, resp)) {
					this.createRoom(req, resp);
				}
			} else if (btn.equalsIgnoreCase("update")) {
				if (validateFormRoom(req, resp)) {
					this.updateRoom(req, resp);
				}
			} else if (btn.equalsIgnoreCase("reset")) {
				session = req.getSession();
				session.removeAttribute("room");
			}
		}
		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			room = dao.findById(Integer.parseInt(id));
			req.setAttribute("room", room);	
			session = req.getSession();
			session.setAttribute("room", room);
			req.setAttribute("uploadedPhotoURL","http://localhost:8000/RoomBooking/img/"+ room.getImage());
			System.out.println(">>Edit :" + room);
		} else if (uri.contains("delete")) {
			this.deleteRoom(req, resp);
		}
		// fill lên bảng
		List<Room> rooms = dao.findAllIsActive();
		req.setAttribute("rooms", rooms);
	}


	private void createRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RoomDao dao = new RoomDao();
			Room room = new Room();
			BeanUtils.populate(room, req.getParameterMap());
			LocalDate date = LocalDate.now();
			room.setCreatedAt(date);
			room.setUpdatedAt(date);
			room.setIsActive(true);
			// xử lý hình ảnh
			dao.create(room);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	private Boolean validateFormRoom(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Boolean check = true;
		String roomName = req.getParameter("roomName");
		String roomType = req.getParameter("roomType");
		String availability = req.getParameter("availability");
		String location = req.getParameter("location");
		String amenities = req.getParameter("amenities");
		String description = req.getParameter("description");
		String price = req.getParameter("price");

		if (roomName == null || roomName.isEmpty()) {
			req.setAttribute("alertName", "Không được bỏ trống");
			check = false;
		}
		if (roomType == null || roomType.isEmpty()) {
			req.setAttribute("alertType", "Không được bỏ trống");
			check = false;
		}
		if (availability == null || availability.isEmpty()) {
			req.setAttribute("alertAvai", "Không được bỏ trống");
			check = false;
		}
		if (location == null || location.isEmpty()) {
			req.setAttribute("alertLocal", "Không được bỏ trống");
			check = false;
		}
		if (amenities == null || amenities.isEmpty()) {
			req.setAttribute("alertameni", "Không được bỏ trống");
			check = false;
		}
		if (description == null || description.isEmpty()) {
			req.setAttribute("alertDes", "Không được bỏ trống");
			check = false;
		}
		if (price == null || price.isEmpty()) {
			req.setAttribute("alertPrice", "Không được bỏ trống");
			check = false;
		} else {
			if (Float.parseFloat(price) <= 0) {
				req.setAttribute("alertPrice", "Không được bé hơn hoặc bằng 0");
				check = false;
			}
		}
		System.out.println(check);
		return check;
	}

	private void updateRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String uri = req.getRequestURI();
			RoomDao dao = new RoomDao();
			Room room = (Room) session.getAttribute("room");
			String img = room.getImage();
			BeanUtils.populate(room, req.getParameterMap());
			LocalDate date = LocalDate.now();
			room.setUpdatedAt(date);
			dao.update(room);
			session.removeAttribute("room");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	private void deleteRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String uri = req.getRequestURI();
			RoomDao dao = new RoomDao();
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			Room room = dao.findById(Integer.parseInt(id));
			LocalDate date = LocalDate.now();
			room.setUpdatedAt(date);
			room.setIsActive(false);
			dao.update(room);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}

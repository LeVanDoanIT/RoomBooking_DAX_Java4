package com.fpoly.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.Dao.PaymentDAO;
import com.fpoly.Dao.RoomDao;
import com.fpoly.Dao.UserDAO;
import com.fpoly.entity.Payment;
import com.fpoly.entity.Room;
import com.fpoly.entity.User;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet({"/user/index","/user/edit/*","/room/index", "/room/edit/*", "/room/create", "/room/update", "/room/delete","/payment/index","/payment/edit/*"})
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("/user")) {
			manageUser(req, resp);
		}
		if (uri.contains("/room")) {
			managerRoom(req, resp);
			
		}
		//Toàn Gay làm
		if (uri.contains("/payment")) {
			managerPayment(req, resp);
		}
	}
	
	private void managerRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomDao dao = new RoomDao();
		Room room = new Room();
		String uri = req.getRequestURI();
		// user/edit/id
		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			room = dao.findById(Integer.parseInt(id));
		} else if (uri.contains("create")) {

			try {
				BeanUtils.populate(room, req.getParameterMap());				
				dao.create(room);
				req.setAttribute("message", "Thêm mới thành công");
			} catch (Exception e) {
				// TODO: handle exception
				req.setAttribute("message", "Thêm mới thất bại");
			}			
		} else if (uri.contains("update")) {
			try {
				BeanUtils.populate(room, req.getParameterMap());
				dao.update(room);
				req.setAttribute("message", "Cập nhật thành công");
				System.out.println(uri);
			} catch (Exception e) {
				// TODO: handle exception
				req.setAttribute("message", "Cập nhật thất bại");
			}

		} else if (uri.contains("delete")) {
			try {
				String id = req.getParameter("id");
				dao.remove(Integer.parseInt(id));
				req.setAttribute("message", "Xoá thành công");
			} catch (Exception e) {
				// TODO: handle exception
				req.setAttribute("message", "Xoá thất bại");
			}
		}
		else {
		    // Xử lý xem chi tiết phòng
		    String id = uri.substring(uri.lastIndexOf("/") + 1);
		    room = dao.findById(Integer.parseInt(id));
		}
		List<Room> items = dao.findAll();
		req.setAttribute("room", room);
		req.setAttribute("items", dao.findAll()); // items: list<User>
		req.getRequestDispatcher("/views/RoomManager.jsp").forward(req, resp);
	}
	private void manageUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		User user = new User();
		String uri = req.getRequestURI();
		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			user = userDao.finById(id);
		}
		req.setAttribute("formManagerUser", user);
		req.setAttribute("itemsManagerUser", userDao.findAllByUse(false));

		req.getRequestDispatcher("/views/RoomManager.jsp").forward(req, resp);
	}
	private void managerPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
    	String usn = ss.getAttribute("usn")+"";
    	System.out.println("Kiểm tra có đăng nhập chưa: "+ usn);
    	UserDAO userDAO = new UserDAO();
    	User user = userDAO.findByUsername(usn);
    	if (usn==null || usn.equals("")) {
			//Chuyển tới trang đăng nhập
    		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}else if(!user.getRole()) {
			System.out.println("Bạn không phải admin không có quyển xem trang admin: "+ user.getRole());
			req.getRequestDispatcher("/Home").forward(req, resp);
		}else {
			System.out.println("Cho phép xem");
			PaymentDAO dao = new PaymentDAO();
	    	Payment pm = new Payment();
	   		req.setAttribute("formPayment", pm); // form: user
	   		req.setAttribute("itemsManagerPayment", dao.findAll()); // items: list<User>
	   		System.out.println("Cho phép xem");
	   		req.getRequestDispatcher("/views/RoomManager.jsp").forward(req, resp);
	   		System.out.println("Cho phép xem");
		}
	}
}
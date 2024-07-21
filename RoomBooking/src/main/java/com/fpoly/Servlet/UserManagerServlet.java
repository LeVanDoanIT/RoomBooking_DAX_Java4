
package com.fpoly.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.Dao.BookingDAO;
import com.fpoly.Dao.UserDAO;
import com.fpoly.entity.Booking;
import com.fpoly.entity.User;

/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet({"/usermanager/index", "/usermanager/edit/*","/usermanager/create","/usermanager/update","/usermanager/delete/*"})
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("/user")) {
			manageUser(req, resp);
		}
		
	}

    private void manageUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        User user = new User();
        String uri = req.getRequestURI();
        
        // Kiểm tra và lấy ID từ URI
        String id = null;
        if (uri.contains("edit") || uri.contains("update")) {
            id = uri.substring(uri.lastIndexOf("/") + 1);
            user = userDao.findById(Integer.parseInt(id));
        }

        if (uri.contains("create")) {
            if (ValidatecreatemanageUser(req, resp)) {
                try {
                    BeanUtils.populate(user, req.getParameterMap());
                    userDao.create(user);
                    req.setAttribute("message", "Thêm mới thành công");
                } catch (Exception e) {
                    req.setAttribute("message", "Thêm mới thất bại");
                }
            }
        } else if (uri.contains("update")) {
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
        
        // Tìm tất cả người dùng
        List<User> users = userDao.findAllByUse(false);
        
        // Đặt thuộc tính và chuyển hướng
        req.setAttribute("formManagerUser", user);
        req.setAttribute("itemsManagerUser", users);
        
        BookingDAO bkdao = new BookingDAO();
        req.setAttribute("itemsId", bkdao.findAll());
        
        req.getRequestDispatcher("/views/UserManager.jsp").forward(req, resp);
    }
	
	private boolean ValidatecreatemanageUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		} else if (username.equals(user.getUsername())) {
			alertUsername = "Tên đăng nhập không được trùng!";
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
		} else if (username.equals(user.getUsername())) {
			alertUsername = "Tên đăng nhập không được trùng!";
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
	
	

}
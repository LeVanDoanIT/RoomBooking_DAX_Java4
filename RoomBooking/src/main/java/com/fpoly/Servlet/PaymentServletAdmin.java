package com.fpoly.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.Dao.PaymentDAO;
import com.fpoly.Dao.UserDAO;
import com.fpoly.entity.Payment;
import com.fpoly.entity.User;

@WebServlet("/PaymentServletAdmin")
public class PaymentServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentServletAdmin() {
		super();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String usn = ss.getAttribute("usn") + "";
		System.out.println("Kiểm tra có đăng nhập chưa: " + usn);
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUsername(usn);
		if (usn == null || usn.equals("")) {
			// Chuyển tới trang đăng nhập
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		} else if (!user.getRole()) {
			System.out.println("Bạn không phải admin không có quyển xem trang admin: " + user.getRole());
			req.getRequestDispatcher("/Home").forward(req, resp);
		} else {
			System.out.println("Cho phép xem");
			PaymentDAO dao = new PaymentDAO();
			Payment pm = new Payment();
			req.setAttribute("formPayment", pm); // form: user
			req.setAttribute("itemsManagerPayment", dao.findAll()); // items: list<User>
			req.getRequestDispatcher("/views/RoomManager.jsp").forward(req, resp);
		}
	}
}

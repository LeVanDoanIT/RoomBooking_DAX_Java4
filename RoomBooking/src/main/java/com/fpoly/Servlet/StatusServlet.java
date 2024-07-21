package com.fpoly.Servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.Dao.BookingDAO;
import com.fpoly.Dao.FeedBackDAO;
import com.fpoly.Dao.PaymentDAO;
import com.fpoly.Dao.UserDAO;
import com.fpoly.entity.Booking;
import com.fpoly.entity.FeedBack;
import com.fpoly.entity.Payment;
import com.fpoly.entity.User;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet({ "/account/status/history", "/account/status/addfeedback", "/account/status/newfeedback/*",
		"/account/status/check-in/*", "/account/status/cancel-check-in", "/account/status/cancle-booking" })
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("history")) {
			fillTable(req, resp);
			req.getRequestDispatcher("/views/PaymentStatus.jsp").forward(req, resp);
		} else if (uri.contains("check-in")) {
			waitCheckIn(req, resp);
			req.getRequestDispatcher("/views/PaymentStatus.jsp").forward(req, resp);
		} else if (uri.contains("cancle-booking")) {
			fillTableCancelBooking(req, resp);
			req.getRequestDispatcher("/views/PaymentStatus.jsp").forward(req, resp);
		} else if (uri.contains("newfeedback")) {
			HttpSession session = req.getSession();
			session.setAttribute("bookingID", uri.substring(uri.lastIndexOf("/") + 1));
			System.out.println(session.getAttribute("bookingID"));
			req.getRequestDispatcher("/views/status/FeedbackForm.jsp").forward(req, resp);
			req.getRequestDispatcher("/views/PaymentStatus.jsp").forward(req, resp);
		}else if(uri.contains("addfeedback")) {
			addFeedback(req, resp);
			req.getRequestDispatcher("/account/status/history").forward(req, resp);
		}
		
	}

	public void addFeedback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equalsIgnoreCase("post")) {
			HttpSession session = req.getSession();
			UserDAO daoUser = new UserDAO();
			User user = daoUser.findByUsername(session.getAttribute("usn").toString());
			String message = req.getParameter("comment");
			LocalDate now = LocalDate.now();
			BookingDAO daoBooking = new BookingDAO();
			Booking booking = new Booking();
			booking = daoBooking.findBookingID(Integer.parseInt(session.getAttribute("bookingID").toString()));
			FeedBack feedBack= new FeedBack();
			feedBack.setUserId(user.getId());
			feedBack.setMessage(message);
			feedBack.setBookingId(Integer.parseInt(booking.getBookingId().toString()));
			feedBack.setTimestamp(now);
			feedBack.setCreatedAt(now);
			feedBack.setUpdateAt(now);
			feedBack.setCreateBy(user.getId());
			feedBack.setUpdateBy(user.getId());
			feedBack.setRoomID(booking.getRoomId());
			FeedBackDAO daoFB = new FeedBackDAO();
			daoFB.create(feedBack);
		}
	}


	public void fillTableCancelBooking(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		HttpSession session = req.getSession();
		User user = userDao.findByUsername(session.getAttribute("usn").toString());
		BookingDAO bookingDao = new BookingDAO();
		List<Booking> listBooking = bookingDao.finByUserID(user.getId(), false);
		req.setAttribute("listBooking", listBooking);
		if (!listBooking.isEmpty()) {
			req.setAttribute("view", "/views/status/CancleBooking.jsp");
		} else {
			req.setAttribute("view", "/views/status/StatusNull.jsp");
		}
	}

	public void fillTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		HttpSession session = req.getSession();
		User user = userDao.findByUsername(session.getAttribute("usn").toString());
		BookingDAO bookingDao = new BookingDAO();
		List<Booking> listBooking = bookingDao.finByUserID(user.getId(), true);
		req.setAttribute("listBooking", listBooking);
		if (!listBooking.isEmpty()) {
			req.setAttribute("view", "/views/status/HistoryBooking.jsp");

		} else {
			req.setAttribute("view", "/views/status/StatusNull.jsp");
		}
	}

	public void waitCheckIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		BookingDAO bookingDao = new BookingDAO();
		if (uri.contains("cancel")) {
			Booking booking = bookingDao.findById(Integer.parseInt(id));
			booking.setStatus(false);
			System.out.println("booking status:" + booking.getStatus());
			bookingDao.update(booking);
			System.out.println("Huỷ thành công");
		}
		UserDAO userDao = new UserDAO();
		HttpSession session = req.getSession();
		User user = userDao.findByUsername(session.getAttribute("usn").toString());
		LocalDateTime localDateTime = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault(); // Múi giờ hệ thống
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
		Date date = Date.from(zonedDateTime.toInstant());
		List<Booking> listBooking = bookingDao.finDateCheckIn(user.getId(), date);
		req.setAttribute("listBooking", listBooking);
		if (!listBooking.isEmpty()) {
			req.setAttribute("view", "/views/status/CheckIn.jsp");

		} else {
			req.setAttribute("view", "/views/status/StatusNull.jsp");
		}
	}
}

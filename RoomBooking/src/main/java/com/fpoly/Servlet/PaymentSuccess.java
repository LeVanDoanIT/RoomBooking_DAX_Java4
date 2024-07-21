package com.fpoly.Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.Dao.BookingDAO;
import com.fpoly.Dao.PaymentDAO;
import com.fpoly.Dao.UserDAO;
import com.fpoly.entity.Booking;
import com.fpoly.entity.Payment;
import com.fpoly.entity.User;

@WebServlet("/PaymentSuccess")
public class PaymentSuccess extends HttpServlet {

	// vnpay_return.jsp post
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy giá trị từ Session từ post của ajaxservlet
		HttpSession session = request.getSession();
		String roomIdString = session.getAttribute("roomId") + "";
		String checkInString = session.getAttribute("checkIn") + "";
		String checkOutString = session.getAttribute("checkOut") + "";
		String totalString = session.getAttribute("total") + "";
		// session.setAttribute("usn", usID);
		String username = session.getAttribute("usn") + "";
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUsername(username);
		int userId = user.getId();// Xử lý lại chỗ này bằng cách lấy session
		System.out.println("UserId: " + userId);
		// Chuyển đôi kiểu dữ liệu string --> phù hợp
		int roomId = Integer.parseInt(roomIdString);
		float total = Float.parseFloat(totalString);
		//
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime checkIn = LocalDateTime.parse(checkInString, formatter);
		LocalDateTime checkOut = LocalDateTime.parse(checkOutString, formatter);
		ZonedDateTime zonedCheckIn = checkIn.atZone(ZoneId.systemDefault());
		ZonedDateTime zonedCheckOut = checkOut.atZone(ZoneId.systemDefault());
		Date dateCheckIn = Date.from(zonedCheckIn.toInstant());
		Date dateCheckOut = Date.from(zonedCheckOut.toInstant());
		System.out.println(dateCheckIn);
		System.out.println(dateCheckOut);

		// Thực hiện createBooking
		BookingDAO bookingDAO = new BookingDAO();
		Booking booking = new Booking();
		Date bookDay = new Date();
		booking.setBookDay(bookDay);// 4
		booking.setCheckIn(dateCheckIn);// 5
		booking.setCheckOut(dateCheckOut);// 6
		booking.setFeedbackID((long) 7);// 8
		booking.setRoomId(roomId);// 3
		booking.setStatus(true);// 7
		booking.setTotalPrice(total);// 9
		booking.setCreatedAt(dateCheckIn);// 10
		booking.setCreatedBy(userId);// 12
		booking.setIsActive(null);// 12
		booking.setUpdatedAt(dateCheckIn);// 11
		booking.setUpdatedBy(userId);// 11
		booking.setUserid(userId);// 2
		System.out.println("UserID: " + booking.getUserid());
		try {
			bookingDAO.create(booking);
			System.out.println("Thêm booking thành công");

			// Thực hiện thêm payment
			PaymentDAO payDAO = new PaymentDAO();
			Payment payment = new Payment();
			System.out.println("1");
			List<Booking> listBookingOfUserId = bookingDAO.findMaxBookingIdByUserId(userId);
			Booking bookingMaxOfUserId = listBookingOfUserId.get(0);// Lấy booking max của user mới tạo booking
			System.out.println(bookingMaxOfUserId.getBookingId());
			System.out.println("2");
			int pay_userId = userId;// get session nếu như đăng nhập
			int pay_bookingId = bookingMaxOfUserId.getBookingId().intValue();
			System.out.println("3");
			Date pay_timestamp = new Date();
			String pay_method = "VNPay";
			String pay_notes = "Không";
			payment.setUserid(pay_userId);
			payment.setBookingid(pay_bookingId);
			payment.setTimestamp(pay_timestamp);
			payment.setPaymentmethod(pay_method);
			payment.setNote(pay_notes);
			// createAt nó đã lấy bên Entity rồi
			// updateAt nó đã lấy bên Entity rồi
			payment.setCreatedBy(userId);
			payment.setUpdatedBy(userId);
			try {
				System.out.println("4");
				payDAO.create(payment);
				System.out.println("5");
				System.out.println("Thêm payment thành công");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
				// Gửi Email cho người dùng
				SendMail(request, response);
				System.out.println("Gửi Email thành công");
			} catch (Exception e) {
				System.out.println("Thêm payment thất bại");
			}

		} catch (Exception e) {
			System.out.println("Thêm booking thất bại");
			e.printStackTrace();
		}
		
		//Xóa ss
		session.removeAttribute("roomId");
		session.removeAttribute("checkIn");
		session.removeAttribute("checkOut");
		session.removeAttribute("total");
	}

	private void SendMail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		final String username = "huylhpc07715@fpt.edu.vn"; // Điền địa chỉ email của mình
		final String password = "kogu kqam lvaj wbkl"; // Điền mật khẩu email của mình

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		HttpSession session2 = req.getSession();
		String userId = session2.getAttribute("usn") + "";// Lấy username trên ss
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUsername(userId);// Tạo user để chứa username ss
		System.out.println(user.getEmail());
		//
		String subject = "PAYMENT SUCCESS";
		String emailTo = user.getEmail();
		//String emailTo = "hoanghuy382004hh@gmail.com";
		String fullname = user.getFullname();
		String total = session2.getAttribute("total") + "";
		String content = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n" + "    <title>PAYMENT SUCCESS</title>\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
				+ "        integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\" />\r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js\"\r\n"
				+ "        integrity=\"sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r\"\r\n"
				+ "        crossorigin=\"anonymous\"></script>\r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js\"\r\n"
				+ "        integrity=\"sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+\"\r\n"
				+ "        crossorigin=\"anonymous\"></script>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
				+ "    <div class=\"container\">\r\n" + "        <div class=\"row\">\r\n"
				+ "            <div class=\"col col-sm-8 offset-sm-2\">\r\n"
				+ "                <div class=\"card\" style=\"width: 18rem;\">\r\n"
				+ "                    <div class=\"card-body\">\r\n"
				+ "                        <h1 class=\"card-title text-primary\">PAYMENT SUCCESS</h1>\r\n"
				+ "                        <h2 class=\"card-subtitle mb-2 text-body-secondary\">User ID: " + userId
				+ "</h2>\r\n" + "                        <p class=\"card-text\">Xin chao: " + fullname + " </p>\r\n"
				+ "                        <p class=\"card-text\">Thanh toan thanh cong don gia: " + total
				+ " VND</p>\r\n"
				+ "                        <p class=\"card-text\">Tro lai trang chu de tiep tuc dat phong</p> \r\n"
				+ "                        <a href=\"<%= request.getContextPath() %>/Home\" class=\"card-link\">Cam on ban da su dung dich vu</a>\r\n"
				+ "                    </div>\r\n" + "                </div>\r\n" + "            </div>\r\n"
				+ "        </div>\r\n" + "    </div>\r\n" + "</body>\r\n" + "\r\n" + "</html>";

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setContent(content,"text/html");
			Transport.send(message);
			System.out.println("Gửi mail thành công.");
			req.setAttribute("message", "Gửi mail thành công");
		} catch (MessagingException e) {
			e.printStackTrace();
			req.setAttribute("message", "Gửi mail thất bại: " + e.getMessage());
		}
	}
}

package com.fpoly.Servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.Dao.UserDAO;
import com.fpoly.Utils.MaHoa;
import com.fpoly.entity.User;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet({ "/account/login", "/account/register", "/account/logout", "/account/forgot-password",
		"/account/change-password", "/account/edit-profile", "/account/info", "/account/FAQ" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		if (uri.contains("login")) {
			this.doSignIn(req, resp);
		} else if (uri.contains("register")) {
			this.doSignUp(req, resp);
		} else if (uri.contains("info")) {
			this.doGetInfoAndUpdate(req, resp);
		} else if (uri.contains("change-password")) {
			this.doChangePass(req, resp);
		} else if (uri.contains("logout")) {
			this.doLogout(req, resp);
		} else if (uri.contains("FAQ")) {
			this.FAQ(req, resp);
		}
	}

	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("usn");
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("usn") != null) {
			UserDAO dao = new UserDAO();
			User user = dao.findByUsername(session.getAttribute("usn").toString());
			if (user.getRole() == true) {
				// Chuyển hướng đến admin
				req.getRequestDispatcher("/admin/management/user-manager").forward(req, resp);
			} else {
				// Chuyển hướng đến người dùng
				req.getRequestDispatcher("/Home").forward(req, resp);
			}
		} else {

			if (validateFormLogin(req, resp)) {
				String method = req.getMethod();
				if (method.equalsIgnoreCase("POST")) {
					String usID = req.getParameter("username");
					String pw = req.getParameter("password");
					String alert = "";
					String messageResponse = "";
					try {
						UserDAO dao = new UserDAO();
						User user = dao.findByUsername(usID);

						if (!user.getPassword().equals(pw)) {
							// sai password
							req.setAttribute("alert", "danger");
							req.setAttribute("messageResponse", "Sai tên đăng nhập hoặc mật khẩu!");
							req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
						} else {
							// đúng password login thành công
							if (user.getRole() == true) {
								// Chuyển hướng đến admin
								req.getRequestDispatcher("/admin/management/user-manager").forward(req, resp);
							} else {
								// Chuyển hướng đến người dùng
								req.getRequestDispatcher("/Home").forward(req, resp);
							}
							// gọi ham checkRole để mở giao diện phù hơp
							// Lưu session login
							session.setAttribute("usn", usID);
							System.out.println("SS usn" + session.getAttribute("usn"));

							int thirtyDaysInSeconds = 30 * 24 * 60 * 60; // lưu ss trong 30 ngày
							session.setMaxInactiveInterval(thirtyDaysInSeconds);
						}
					} catch (Exception e) {
						// sai tên đăng nhập không tìm thấy username
						req.setAttribute("alert", "danger");
						req.setAttribute("messageResponse", "Sai tên đăng nhập hoặc mật khẩu!");
						req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
					}
				}
			}
		}
	}

	// hàm bắt lỗi form đăng nhập
	public Boolean validateFormLogin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Boolean check = false;
		String un = req.getParameter("username");
		String pw = req.getParameter("password");
		if (un == null || pw == null || un.isEmpty() || pw.isEmpty()) {
			req.setAttribute("alert", "danger");
			req.setAttribute("messageResponse", "Vui lòng điền đẩy đủ thông tin!");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			check = false;
		} else {
			check = true;
		}
		return check;
	}

	private void doSignUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
		String method = request.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			if (validateRegister(request, response)) {
				// To do Dang ky
				try {
					User entiy = new User();
					UserDAO dao = new UserDAO();
					BeanUtils.populate(entiy, request.getParameterMap());
					dao.create(entiy);
					System.out.println("Đăng ký thành công");

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Đăng ký thất bại " + e);
				}
				request.setCharacterEncoding("UTF-8");
				final String username = "khoatdpc07807@fpt.edu.vn"; // Điền địa chỉ email của mình
				final String password = "xpwr twhp zses yfjg"; // Điền mật khẩu email của mình

				String emailFrom = request.getParameter("email");

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
				try {
					MimeMessage message = new MimeMessage(session);
					message.setSubject("RoomBooking Register");
					message.setContent("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Đăng ký thành công</title>\r\n" + "    <style>\r\n" + "        body {\r\n"
							+ "            font-family: Arial, sans-serif;\r\n" + "            margin: 0;\r\n"
							+ "            padding: 0;\r\n" + "            background-color: #f4f4f4;\r\n"
							+ "        }\r\n" + "\r\n" + "        .container {\r\n"
							+ "            max-width: 600px;\r\n" + "            margin: 20px auto;\r\n"
							+ "            padding: 20px;\r\n" + "            background-color: #fff;\r\n"
							+ "            border-radius: 8px;\r\n"
							+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
							+ "            border-style: groove;\r\n" + "        }\r\n" + "\r\n" + "        h1 {\r\n"
							+ "            color: #333;\r\n" + "            text-align: center;\r\n" + "        }\r\n"
							+ "\r\n" + "        p {\r\n" + "            font-size: 16px;\r\n"
							+ "            line-height: 1.6;\r\n" + "            margin-bottom: 20px;\r\n"
							+ "        }\r\n" + "\r\n" + "        .btn {\r\n" + "            display: inline-block;\r\n"
							+ "            padding: 10px 20px;\r\n" + "            background-color: #007bff;\r\n"
							+ "            color: #fff;\r\n" + "            text-decoration: none;\r\n"
							+ "            border-radius: 5px;\r\n" + "            width: 100px;\r\n"
							+ "            text-align: center;\r\n" + "        }\r\n" + "\r\n"
							+ "        .btn:hover {\r\n" + "            background-color: #0056b3;\r\n"
							+ "        }\r\n" + "    </style>\r\n" + "</head>\r\n" + "<body>\r\n"
							+ "    <div class=\"container\">\r\n" + "        <h1>Welcome to Bee Booking!</h1>\r\n"
							+ "        <p>Thank you for registering as a member with us. Now you have become part of Bee Booking.</p>\r\n"
							+ "        <p>Click the button below to log in:</p>\r\n"
							+ "        <p><a href=\"http://localhost:8080/RoomBooking/account/login\" class=\"btn\">Login</a></p>\r\n"
							+ "        <p>If you have any questions, don't hesitate to contact us via email: <a href=\"mailto:khoatdpc07807@fpt.edu.vn\">khoatdpc07807@fpt.edu.vn</a></p>\r\n"
							+ "        <p >Thank you,</p>\r\n" + "        <p>Bee Booking</p>\r\n" + "    </div>\r\n"
							+ "</body>\r\n" + "</html>", "text/html");
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailFrom));
//	            message.setText(message);
					Transport.send(message);
					System.out.println("Gửi mail thành công.");
					request.setAttribute("message", "Đăng ký thành công. Vui lòng kiểm tra email của bạn");
				} catch (MessagingException e) {
					e.printStackTrace();
					request.setAttribute("message", "Gửi mail thất bại: " + e.getMessage());
				}
			}
		}
		request.getRequestDispatcher("/views/Register.jsp").forward(request, response);

	}

	public void doGetInfoAndUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// lay session lay username
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("usn");
		// tim username va fill len form
		UserDAO daoUser = new UserDAO();
		User user = daoUser.findByUsername(username);
		req.setAttribute("user", user);

		// get Method
		String method = req.getMethod();
		if (method.equalsIgnoreCase("post")) {
			if (validateFormInfo(req, resp)) {
				try {
					user.setFullname(req.getParameter("fullname"));
					user.setEmail(req.getParameter("email"));
					user.setPhone(req.getParameter("phone"));
					daoUser.update(user);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Lỗi" + e);
				}
			}
		}
		if (user.getRole()) {
			req.getRequestDispatcher("/views/UserInformationAdmin.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/views/UserInformation.jsp").forward(req, resp);
		}
	}

	public Boolean validateFormInfo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Boolean check = true;
		String alertFullname = "";
		String alertEmail = "";
		String alertPhone = "";
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		if (fullname == null || fullname.isEmpty()) {
			alertFullname = "Họ và tên không được bỏ trống!";
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

		if (phone == null || phone.isEmpty()) {
			alertPhone = "Số điện thoại không được bỏ trống!";
			check = false;
		} else if (phone.length() != 10) {
			alertPhone = "Số điện thoại không hợp lệ";
			check = false;
		}
		req.setAttribute("alertFullname", alertFullname);
		req.setAttribute("alertEmail", alertEmail);
		req.setAttribute("alertPhone", alertPhone);
		return check;
	}

	public Boolean validateRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Boolean check = true;
		String alertUsername = "";
		String alertFullname = "";
		String alertPassword = "";
		String alertConfirmPass = "";
		String alertEmail = "";
		String alertPhone = "";
		String username = req.getParameter("username");
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirmPassword");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		UserDAO daoUser = new UserDAO();
		User user = daoUser.findByUsername(username);

		if (username == null || username.isEmpty()) {
			alertUsername = "Tên đăng nhập không được bỏ trống!";
			check = false;
		} else if (username.equals(user.getUsername())) {
			alertUsername = "Tên đăng nhập đã tồn tại!";
			check = false;
		}

		if (fullname == null || fullname.isEmpty()) {
			alertFullname = "Họ và tên không được bỏ trống!";
			check = false;
		}
		if (!password.equals(confirm)) {
			alertConfirmPass = "Mật khẩu không trùng khớp!";
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
		} else {
			password = MaHoa.hashPassword(password); // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
			check = true;
		}

		if (confirm == null || confirm.isEmpty()) {
			alertConfirmPass = "Mật khẩu không được bỏ trống!";
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
		} else if (email.equals(user.getEmail())) {
			alertEmail = "Email đã tồn tại";
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
		req.setAttribute("alertConfirmPass", alertConfirmPass);
		req.setAttribute("alertEmail", alertEmail);
		req.setAttribute("alertPhone", alertPhone);
		return check;
	}

	public void doChangePass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lay session lay username
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("usn");

		String newPassword = request.getParameter("newPassword");
		UserDAO daoUser = new UserDAO();
		User user = daoUser.findByUsername(username);
		// get Method
		String method = request.getMethod();
		if (method.equalsIgnoreCase("post")) {
			if (validateChangePass(request, response))
				try {
					user.setPassword(request.getParameter("newPassword"));
					daoUser.update(user);
					request.setAttribute("message", "Đổi mật khẩu thành công!");
					System.out.println("update thanh cong");

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Lỗi" + e);
				}
		}
		if (user.getRole()) {
			request.getRequestDispatcher("/views/UserInformationAdmin.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/UserInformation.jsp").forward(request, response);
		}
	}

	public Boolean validateChangePass(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		String alertoldPassword = "";
		String alertnewPassword = "";
		String alertconfirmPassword = "";
		boolean check = true;
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("usn");
		UserDAO daoUser = new UserDAO();
		User user = daoUser.findByUsername(username);
		String regexPass = "^[a-zA-Z0-9]{8}$";
		Pattern patternPass = Pattern.compile(regexPass);
		Matcher matcherPass = patternPass.matcher(newPassword);
		if (!oldPassword.equals(user.getPassword())) {
			alertoldPassword = "Mật khẩu cũ không giống";
			check = false;
		}
		if (oldPassword.equals(newPassword)) {
			alertnewPassword = "Mật khẩu mới không được giống mật khẩu cũ";
			check = false;
		}
		if (newPassword == null || newPassword.isEmpty()) {
			alertnewPassword = "Mật khẩu mới không được trống!";
			check = false;
		} else if (!matcherPass.matches()) {
			alertnewPassword = "Mật khẩu mới không hợp lệ(ít nhất 8 kí tự gồm chữ và số)";
			check = false;
		}
		if (!confirmPassword.equals(newPassword)) {
			alertconfirmPassword = "Không trùng với mật khẩu mới!";
			check = false;
		} else if (confirmPassword == null || confirmPassword.isEmpty()) {
			alertconfirmPassword = "Không được bỏ trống!";
			check = false;
		}
		req.setAttribute("alertoldPassword", alertoldPassword);
		req.setAttribute("alertnewPassword", alertnewPassword);
		req.setAttribute("alertconfirmPassword", alertconfirmPassword);
		return check;
	}

	public void FAQ(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		final String username = "baochpc07738@fpt.edu.vn"; // Điền địa chỉ email của mình
		final String password = "psoe ohiq vcjw cxzw"; // Điền mật khẩu email của mình

		HttpSession sessionMail = req.getSession();
		String usename = sessionMail.getAttribute("usn").toString();
		UserDAO dao = new UserDAO();
		User user = dao.findByUsername(usename);
		String email = user.getEmail();
		String body = req.getParameter("body");
		String subject = req.getParameter("subject");

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			System.out.println("Gửi mail thành công.");
			req.setAttribute("message", "Gửi mail thành công");
		} catch (MessagingException e) {
			e.printStackTrace();
			req.setAttribute("message", "Gửi mail thất bại: " + e.getMessage());
		}
		req.getRequestDispatcher("/views/FAQ.jsp").forward(req, resp);
	}
}

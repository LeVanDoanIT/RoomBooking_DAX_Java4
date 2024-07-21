package com.fpoly.Servlet;

import java.io.IOException;
import java.security.SecureRandom;
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

import com.fpoly.Dao.UserDAO;
import com.fpoly.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet({ "/ForgotPass/SendMail", "/ForgotPass/CheckRandomPass", "/ForgotPass/ChangePass" })
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.service(req, resp);
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		if (uri.contains("SendMail")) {
			this.SendMail(req, resp);
		} else if (uri.contains("CheckRandomPass")) {
			this.CheckRandomPass(req, resp);
			req.getRequestDispatcher("/views/ChangePass.jsp").forward(req, resp);
		} else if (uri.contains("ChangePass")) {
			this.ChangePass(req, resp);
		}
	}

	private void SendMail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		final String senderEmail = "baochpc07738@fpt.edu.vn"; // Địa chỉ email cứng người gửi
		final String password = "psoe ohiq vcjw cxzw";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, password); // Sử dụng senderEmail ở đây
			}
		});
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();

		// Tạo session
		HttpSession SessionRE = req.getSession(); // session email
		HttpSession SessionRP = req.getSession(); // sesion random pass

		// Khai Báo giá trị
		String recipientEmail = req.getParameter("YourEmail");
		String randomPassword = PasswordGenerator.generatePassword(); // tạo mật khẩu ngẫy nhiên
		System.out.println("Mật khẩu 1: " + randomPassword);
		// set giá trị vào sesion
		SessionRE.setAttribute("recipientEmail", recipientEmail);
		SessionRP.setAttribute("randomPassword", randomPassword);

		// tạo thời gian tồn tại của session
		SessionRE.setMaxInactiveInterval(300);
		SessionRP.setMaxInactiveInterval(300);
		// ------------------------------------------------------------------------------------------
		String confirmationLink = "http://localhost:8080/RoomBooking/views/TemporaryPass.jsp";
		try {
			List<User> usersList = findByEmail(recipientEmail); // Sử dụng phương thức findByEmail để lấy danh sách User
			if (!usersList.isEmpty()) {
				User user = usersList.get(0);
				Integer UserID = user.getId();
				String FullName = user.getFullname();
				String UserName = user.getUsername();
				// String Title = "XIN CHÀO " + user.getFullname();
				// String body = Title + " Mật khẩu tạm thời của bạn là: " + randomPassword
				// + " Vui lòng truy cập vào trang " + confirmationLink + " để xác nhận và đổi
				// mật khẩu ";
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(senderEmail)); // Đặt email người gửi là senderEmail
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
					message.setSubject("Password Recovery");
					// message.setText(body);
					message.setContent("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n" + "    <title>Insert title here</title>\r\n"
							+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\"\r\n"
							+ "        rel=\"stylesheet\"\r\n"
							+ "        integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\"\r\n"
							+ "        crossorigin=\"anonymous\" />\r\n" + "</head>\r\n" + "<body>\r\n"
							+ "    <div class=\"container\">\r\n" + "        <div class=\"row\">\r\n"
							+ "            <div class=\"col col-sm-8 offset-sm-2\">\r\n"
							+ "                <div class=\"card\" style=\"width: 18rem;\">\r\n"
							+ "                    <div class=\"card-body\">\r\n"
							+ "                        <h1 class=\"card-title text-primary\">FORGOT PASSWORD</h1>\r\n"
							+ "                        <h2 class=\"card-subtitle mb-2 text-body-secondary\">User ID: "
							+ UserID + "</h2>\r\n" + "                        <p class=\"card-text\">HELLO : "
							+ UserName + " </p>\r\n"
							+ "                        <p class=\"card-text\">This is your temporary password: "
							+ randomPassword + "</p>\r\n"
							+ "                        <p class=\"card-text\">Visit the link below to continue. </p>\r\n"
							+ "                        <a href=\"" + confirmationLink
							+ "\" class=\"card-link\">Another link</a>\r\n" + "                    </div>\r\n"
							+ "                </div>\r\n" + "            </div>\r\n" + "        </div>\r\n"
							+ "    </div>\r\n" + "    <script\r\n"
							+ "        src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js\"\r\n"
							+ "        integrity=\"sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r\"\r\n"
							+ "        crossorigin=\"anonymous\"></script>\r\n" + "    <script\r\n"
							+ "        src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js\"\r\n"
							+ "        integrity=\"sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+\"\r\n"
							+ "        crossorigin=\"anonymous\"></script>\r\n" + "</body>\r\n" + "</html>",
							"text/html");
					Transport.send(message);
					System.out.println("Gửi mật khẩu đến mail thành công.");
					req.setAttribute("message", "Gửi mật khẩu đến mail thành công");
				} catch (MessagingException e) {
					e.printStackTrace();
					req.setAttribute("message", "Gửi mật khẩu đến mail thất bại: " + e.getMessage());
				}
			} else {
				req.setAttribute("message", "Email không tồn tại trong hệ thống.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "Email không tồn tại trong hệ thống.");
		} finally {
			em.close();
			emf.close();
		}
		req.getRequestDispatcher("/views/ForgotPassword.jsp").forward(req, resp);
	}

	private void CheckRandomPass(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession SessionRP = req.getSession(false); // Truy cập session hiện tại, không tạo mới nếu chưa có
		if (SessionRP == null) {
			req.setAttribute("Rmessage", "Session không tồn tại.");
			System.out.println("Session không tồn tại.");
			return;
		}
		String tempPass = req.getParameter("TemporaryPass");
		System.out.println("mật khẩu 2: " + tempPass);
		String storedRandomPassword = (String) SessionRP.getAttribute("randomPassword");

		if (tempPass != null && tempPass.equals(storedRandomPassword)) { // Mật khẩu nhập vào đúng
			req.setAttribute("Rmessage", "Mật khẩu đúng, bạn có thể mở trang email.");
			System.out.println("Mật khẩu đúng, chuyển hướng đến ChangePass.jsp");
			req.getRequestDispatcher("/views/ChangePass.jsp").forward(req, resp);
			return; // Dừng thực hiện các dòng mã tiếp theo
		} else { // Mật khẩu nhập vào không đúng
			req.setAttribute("Rmessage", "Mật khẩu không đúng, vui lòng kiểm tra lại.");
			System.out.println("Mật khẩu không đúng, hiển thị lại ForgotPassword.jsp");
		}
	}

	private void ChangePass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession SessionRE = req.getSession(false); // Truy cập session hiện tại, không tạo mới nếu chưa có
		if (SessionRE == null) {
			req.setAttribute("Cmessage", "Session không tồn tại.");
			System.out.println("Session không tồn tại.");
			return;
		}

		String recipientEmail = (String) SessionRE.getAttribute("recipientEmail");
		if (recipientEmail == null || recipientEmail.isEmpty()) {
			req.setAttribute("Cmessage", "Không thể xác định email của người dùng.");
			System.out.println("Không thể xác định email của người dùng.");
			return;
		}

		UserDAO daoUser = new UserDAO();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();

		try {
			// List<User> usersList = findByEmail(recipientEmail, em);
			List<User> usersList = findByEmail(recipientEmail);
			if (!usersList.isEmpty()) {
				User user = usersList.get(0);
				user.setPassword(req.getParameter("NewPass"));
				// daoUser.update(user, em); // Truyền EntityManager vào hàm update
				daoUser.update(user);
				req.setAttribute("Cmessage", "Đổi mật khẩu thành công!");
				System.out.println("đổi mật khẩu thành công");
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			} else {
				req.setAttribute("Cmessage", "Đổi mật khẩu thất bại!");
				System.out.println("đổi mật khẩu thất bại");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public class PasswordGenerator {
		private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		private static final String NUMBER = "0123456789";

		private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;
		private static final SecureRandom random = new SecureRandom();

		public static String generatePassword() {
			int minLength = 7; // Độ dài tối thiểu của mật khẩu

			StringBuilder password = new StringBuilder(minLength);

			// Thêm ít nhất một ký tự chữ cái vào mật khẩu
			password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
			// Thêm ít nhất một ký tự số vào mật khẩu
			password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));

			// Thêm các ký tự ngẫu nhiên còn lại để đạt đến độ dài tối thiểu
			for (int i = 2; i < minLength; i++) {
				password.append(PASSWORD_ALLOW_BASE.charAt(random.nextInt(PASSWORD_ALLOW_BASE.length())));
			}

			// Đảo lộn thứ tự các ký tự trong mật khẩu
			for (int i = 0; i < minLength; i++) {
				int randomIndexToSwap = random.nextInt(minLength);
				char temp = password.charAt(randomIndexToSwap);
				password.setCharAt(randomIndexToSwap, password.charAt(i));
				password.setCharAt(i, temp);
			}

			return password.toString();
		}
	}

	public List<User> findByEmail(String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", email); // Đặt giá trị cho tham số email
		List<User> list = query.getResultList();
		return list;
	}
}

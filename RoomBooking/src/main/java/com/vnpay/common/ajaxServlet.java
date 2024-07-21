/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.common;

import com.fpoly.Dao.BookingDAO;
import com.fpoly.entity.Booking;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;

/**
 *
 * @author CTT VNPAY
 */
@WebServlet("/vnpayajax")
public class ajaxServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy từ trang vnpay_pay.jsp
		int roomId = Integer.parseInt(request.getParameter("roomIdP"));
		float total = Float.parseFloat(request.getParameter("totalP"));
		String checkIn = null;
		String checkOut = null;

		// Xử lý ngày Fri Apr 19 09:07:00 ICT 2024 --> 2024-04-20 09:22:00
		String CheckInString = request.getParameter("CheckInP");
		String CheckOutString = request.getParameter("CheckInP");
		SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		try {
			Date CheckInDate = inputFormat.parse(CheckInString);
			Date CheckOutDate = inputFormat.parse(CheckOutString);

			checkIn = outputFormat.format(CheckInDate);
			checkOut = outputFormat.format(CheckOutDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("roomId", roomId);
		request.setAttribute("CheckIn", checkIn);
		request.setAttribute("CheckOut", checkOut);
		request.setAttribute("total", total);

		// Lấy user cần gửi Email
		HttpSession sessions = request.getSession();
		String userId = (String) sessions.getAttribute("usn");
		System.out.println("Kiểm tra có đăng nhập chưa trước khi thanh toán: "+userId);
		
		if (userId == null || userId.equals("")) {
			System.out.println("Bạn cần đăng nhập để thanh toán");
			// Chuyển đến trang đăng nhập
			request.getRequestDispatcher("views/login.jsp").forward(request, response);

		} else {
			// Hiển thị trang lựa chọn thanh toán
			System.out.println("Không null có thể thanh toán");
			request.getRequestDispatcher("vnpay_pay.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy các thông tin cần thiết
		String roomId = req.getParameter("roomId");
		String checkIn = req.getParameter("CheckIn");
		String checkOut = req.getParameter("CheckOut");
		String total = req.getParameter("total");

		// set session
		HttpSession session = req.getSession();
		session.setAttribute("roomId", roomId);
		session.setAttribute("checkIn", checkIn);
		session.setAttribute("checkOut", checkOut);
		session.setAttribute("total", total);

		// Làm sao để set cái total vô amount
		// amout này nằm ở trong payment_method
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";
		long amount = (long) (Float.parseFloat(req.getParameter("amount")) * 100);
		String bankCode = req.getParameter("bankCode");

		String vnp_TxnRef = Config.getRandomNumber(8);
		String vnp_IpAddr = Config.getIpAddress(req);

		String vnp_TmnCode = Config.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));// vnp_Amount tại tảng return
		vnp_Params.put("vnp_CurrCode", "VND");

		if (bankCode != null && !bankCode.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bankCode);
		}
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = req.getParameter("language");
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("code", "00");
		job.addProperty("message", "success");
		job.addProperty("data", paymentUrl);
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(job));
	}

}

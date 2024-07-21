package com.fpoly.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Check;

import com.fpoly.Dao.BookingDAO;
import com.fpoly.entity.Booking;


@WebServlet("/Booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Các thuộc tính cần truyền sang trang payment_method
		String RoomId = req.getParameter("RoomId");
		
		if(checkDate(req, resp)) {
			if(checkAvailibleBooking(req, resp)) {
				Date ngayCheckIn = null;
				Date ngayCheckOut = null;
				int daysOfStay = 0;
				float price = Float.parseFloat(req.getParameter("price"));
				float total = 0;
				System.out.println(req.getParameter("CheckIn"));
				System.out.println(req.getParameter("CheckOut"));			
				//Tính số ngày ở
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				try {
				    ngayCheckIn = formatter.parse(req.getParameter("CheckIn"));
				   
				    ngayCheckOut = formatter.parse(req.getParameter("CheckOut"));
				    
				} catch (ParseException e) {
				    e.printStackTrace();
				}

				if (ngayCheckIn != null && ngayCheckOut != null) {
				    LocalDate checkIn = LocalDate.ofInstant(ngayCheckIn.toInstant(), ZoneId.systemDefault());
				    LocalDate checkOut = LocalDate.ofInstant(ngayCheckOut.toInstant(), ZoneId.systemDefault());
				    daysOfStay = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
				    total = price * daysOfStay;
				    
					//Ngày miễn phí hủy trước CheckIn 1 ngày
					LocalDate freeCancellation = checkIn.minusDays(1);
					req.setAttribute("freeCancellation", freeCancellation);	
				}							
				
				//Các thông tin cần chuyển cho trang PaymentServlet
				req.setAttribute("roomId", RoomId);//System.out.println("Mã phòng: "+RoomId);//6
				req.setAttribute("CheckIn", ngayCheckIn);//System.out.println("CheckIn: "+ngayCheckIn);	 Thu Apr 18 23:59:00 ICT 2024
				req.setAttribute("CheckOut", ngayCheckOut);//System.out.println("CheckOut: "+ngayCheckOut); Fri Apr 26 23:59:00 ICT 2024
				req.setAttribute("daysOfStay", daysOfStay);//System.out.println("Số ngày ở: "+daysOfStay); 8
				req.setAttribute("price", price);//System.out.println("Tiền phòng: "+price); 600000.0	
				req.setAttribute("total", total);//System.out.println("Tổng tiền: "+total); 4800000.0
							
				//Chuyển sang trang payment
				req.getRequestDispatcher("payment_method.jsp").forward(req, resp);
			}else {
				//Nếu thất bại thì chuyển tới trang này lại
				String uri = "product/"+RoomId;
		        RequestDispatcher rd = req.getRequestDispatcher(uri);
	            rd.forward(req, resp);
			}	
		}else {
			//Nếu thất bại thì chuyển tới trang này lại
			String uri = "product/"+RoomId;
	        RequestDispatcher rd = req.getRequestDispatcher(uri);
            rd.forward(req, resp);
		}
		
	}
	
	public Boolean checkDate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Boolean check = true;
		Date ngayCheckIn = null;
		Date ngayCheckOut = null;
		String ErrorNgayCheckIn = "";
		String ErrorNgayCheckOut = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Calendar cal = Calendar.getInstance();
		
		//Ngày trống
		if(req.getParameter("CheckIn") == null || req.getParameter("CheckIn").isEmpty()) {
			ErrorNgayCheckIn = "Ngày CheckIn không được bỏ trống!";
			check = false;
		}
		if(req.getParameter("CheckOut") == null || req.getParameter("CheckOut").isEmpty()) {
			ErrorNgayCheckOut = "Ngày CheckOut không được bỏ trống!";
			check = false;
		}
		
		// Nếu ngày không trống thì bắt lỗi tiếp
		if (req.getParameter("CheckIn") == null || req.getParameter("CheckIn").isEmpty()|| req.getParameter("CheckOut") == null || req.getParameter("CheckOut").isEmpty()) {
		    check = false;
		} else {
		    try {
		        ngayCheckIn = formatter.parse(req.getParameter("CheckIn"));
		        ngayCheckOut = formatter.parse(req.getParameter("CheckOut"));

		        //Ngày checkin phải lớn hơn ngày hiện tại
		        if (ngayCheckIn.before(cal.getTime())) {
		            check = false;
		            ErrorNgayCheckIn = "Ngày CheckIn không được nhỏ hơn ngày hiện tại";
		        }

		        //Ngày checkout phải lớn hơn ngày checkin
		        if (ngayCheckOut.before(ngayCheckIn)) {
		            check = false;
		            ErrorNgayCheckOut = "Ngày CheckOut phải lớn hơn ngày hiện tại";
		        }

		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		}
		req.setAttribute("ErrorNgayCheckIn", ErrorNgayCheckIn);
		req.setAttribute("ErrorNgayCheckOut", ErrorNgayCheckOut);
		
		return check;
	}
	
	public Boolean checkAvailibleBooking(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Boolean check = true;
		
		//Bắt đầu kiểm tra
		String ErrorBookingDateChoose = "";
		String RoomId = req.getParameter("RoomId");
		Date ngayCheckIn = null;
		Date ngayCheckOut = null;
		int daysOfStay = 0;
		float price = Float.parseFloat(req.getParameter("price"));
		float total = 0;
		System.out.println(req.getParameter("CheckIn"));
		System.out.println(req.getParameter("CheckOut"));			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
		    ngayCheckIn = formatter.parse(req.getParameter("CheckIn"));
		   
		    ngayCheckOut = formatter.parse(req.getParameter("CheckOut"));
		    
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		//Bắt lỗi thành công
		BookingDAO bookingDAO = new BookingDAO();
		List<Booking> bookinglist = bookingDAO.findBookingOfBoooked(Integer.parseInt(RoomId), ngayCheckIn, ngayCheckOut);
		if (bookinglist.size() == 0) {
			System.out.println("Có thể đặt: " + bookinglist.size());
			ErrorBookingDateChoose = "";
			check = true;
		}else {
			System.out.println("No thể đặt: " + bookinglist.size());
			ErrorBookingDateChoose = "Phòng đã có người đặt trong ngày bạn đã chọn vui lòng chọn lại!";
			check = false;
		}
		//Kết thúc kiểm tra
		req.setAttribute("ErrorBookingDateChoose", ErrorBookingDateChoose);
		return check;
	}
	
}

package com.fpoly.Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.Dao.BookingDAO;
import com.fpoly.Dao.RoomDao;
import com.fpoly.entity.Booking;
import com.fpoly.entity.Room;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public PaymentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//Lấy giá trị tử trang http://localhost:8080/RoomBooking/product/1 khi nhấn Đặt phòng ngay
//		int roomId = Integer.parseInt(request.getParameter("roomId"));
//		System.out.println("Room được chọn đặt: "+roomId);
//		RoomDao roomDAO = new RoomDao();
//		Room room = roomDAO.findById(roomId);
//		System.out.println(room.getPrice());//Hiển thị thành công 200000...
//		request.setAttribute("room", room);
		//Hiển thị trang 1
		
		//Từ trang Booking.jsp sẽ Integer.parseInt(request.getParameter("roomId"))
		int BookingId = 17;
		BookingDAO dao = new BookingDAO();
		Booking booking = dao.findById(BookingId);
		request.setAttribute("booking", booking);
		
		//Hiển thị ngày được hủy miễn phí trước 1 ngày
		//ngày checkIn
		Date ngayCheckIn = booking.getCheckIn();
		Date ngayCheckOut = booking.getCheckOut();
		LocalDate checkIn = LocalDate.ofInstant(ngayCheckIn.toInstant(), ZoneId.systemDefault());
		LocalDate checkOut = LocalDate.ofInstant(ngayCheckOut.toInstant(), ZoneId.systemDefault());
		LocalDate freeCancellation = checkIn.minusDays(1);
		System.out.println(freeCancellation);
		request.setAttribute("freeCancellation", freeCancellation);	
		
		//Ngày ở
		long daysOfStay = ChronoUnit.DAYS.between(checkIn, checkOut);
		request.setAttribute("daysOfStay", daysOfStay);	
		request.getRequestDispatcher("payment_method.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

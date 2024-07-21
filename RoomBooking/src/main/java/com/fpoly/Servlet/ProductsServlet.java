package com.fpoly.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.Dao.FeedBackDAO;
import com.fpoly.Dao.RoomDao;
import com.fpoly.entity.FeedBack;
import com.fpoly.entity.Room;

@WebServlet({ "/products", "/product/*", "/find", "/arrange" })
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("products")) {
			// Hiển thị danh sách các phòng
			displayRoomList(req, resp);
		} else if (uri.contains("product")) {
			// Hiển thị thông tin chi tiết của một phòng
			this.fillFeedback(req, resp);
			displayRoomDetails(req, resp);
			// Hiẻn thị danh sách feedback

		} else if (uri.contains("find")) {
			// Xử lý yêu cầu tìm kiếm
			String roomType = req.getParameter("roomType");
			String location = req.getParameter("location");
			if (roomType != null && location != null) {
				performSearch(req, resp, roomType, location);
			} else {
				// Hiển thị trang chính nếu không có yêu cầu tìm kiếm
				displayRoomList(req, resp);
			}
		} else if (uri.contains("arrange")) {
			// Xử lý yêu cầu sắp xếp
			String orderBy = req.getParameter("orderBy");
			if ("asc".equalsIgnoreCase(orderBy)) {
				arrangeRoomsByPriceAsc(req, resp);
			} else if ("desc".equalsIgnoreCase(orderBy)) {
				arrangeRoomsByPriceDesc(req, resp);
			} else {
				// Hiển thị trang chính nếu không có yêu cầu sắp xếp
				displayRoomList(req, resp);
			}
		}
	}

	private void fillFeedback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		Integer roomID = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
		System.out.println(roomID);
		FeedBackDAO dao = new FeedBackDAO();

		List<FeedBack> list = dao.findFeedBack(roomID);
		System.out.println(list);
		req.setAttribute("list", list);
	}

	private void arrangeRoomsByPriceAsc(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RoomDao roomDao = new RoomDao();
		List<Room> rooms = roomDao.findAllRoomsOrderByPriceAsc();
		req.setAttribute("items", rooms);
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}

	private void arrangeRoomsByPriceDesc(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RoomDao roomDao = new RoomDao();
		List<Room> rooms = roomDao.findAllRoomsOrderByPriceDesc();
		req.setAttribute("items", rooms);
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}

	private void displayRoomList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RoomDao roomDao = new RoomDao();
		List<Room> rooms = roomDao.findAll();
		req.setAttribute("items", rooms); // Đặt danh sách các phòng vào "items"
		req.getRequestDispatcher("/views/products.jsp").forward(req, resp);
	}

	private void displayRoomDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Lấy ID phòng từ URI
		String uri = req.getRequestURI();
		Integer roomID = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));

		// Lấy thông tin chi tiết của phòng từ cơ sở dữ liệu
		RoomDao daoRoom = new RoomDao();
		Room room = daoRoom.findById(roomID);

		// Đặt thông tin phòng vào request attribute
		req.setAttribute("room", room);

		// Xác định màu cho trạng thái
		String mauTrangThai = room.getAvailability().equalsIgnoreCase("Còn Phòng") ? "success" : "danger";
		req.setAttribute("classTextCSS", mauTrangThai);

		// Chuyển hướng đến trang hiển thị thông tin chi tiết của phòng
		req.getRequestDispatcher("/views/DetailedRoom.jsp").forward(req, resp);
	}

	private void performSearch(HttpServletRequest request, HttpServletResponse response, String roomType,
			String location) throws ServletException, IOException {
		RoomDao roomDao = new RoomDao();
		List<Room> searchResults = roomDao.findRoomsByTypeAndLocation(roomType, location);
		request.setAttribute("items", searchResults);
		// request.getRequestDispatcher("/views/products.jsp").forward(request,
		// response);
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}

}

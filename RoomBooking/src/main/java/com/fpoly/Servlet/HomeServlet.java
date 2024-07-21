package com.fpoly.Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.Dao.RoomDao;
import com.fpoly.entity.Room;

@WebServlet({ "/Home", "/Home/detailedRoom"})
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        RoomDao roomDao = new RoomDao();
        List<Room> rooms = roomDao.findAll();

        // Đặt danh sách phòng vào request attribute
        request.setAttribute("items", rooms);

        // Chuyển hướng yêu cầu đến trang home.jsp
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}

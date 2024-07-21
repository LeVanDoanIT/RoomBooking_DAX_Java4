package com.fpoly.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fpoly.Dao.BookingDAO;
import com.fpoly.entity.Booking;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
   	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   		BookingDAO dao = new BookingDAO();
   		Booking bk = new Booking();
   		
   		String uri = req.getRequestURI();
   		
   		
   		// user/edit/id
   		req.setAttribute("form", bk); // form: user
   		req.setAttribute("items", dao.findAll()); // items: list<User>
   		req.getRequestDispatcher("/views/UserInformation.jsp").forward(req, resp);
   	}

}

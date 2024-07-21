package com.fpoly.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.entity.User;


/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter({"/account/info","/account/logout","/PaymentServlet","/account/status/*"})
public class AuthFilter implements HttpFilter {
       


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		try {
			String username = session.getAttribute("usn").toString();
			String error = "";
			
			if (username == null) {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				System.out.println("Vui lòng đăng nhập!");
			} else {
				chain.doFilter(request, response);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			System.out.println("Vui lòng đăng nhập!");
			System.out.println(e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi :"+e);
		}
		
	}

}

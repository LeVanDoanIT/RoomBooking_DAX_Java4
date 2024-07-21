package com.fpoly.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Part photo = request.getPart("photo");
		//UTC

		String path =  "/img/" + photo.getSubmittedFileName();
		String filename = request.getServletContext().getRealPath(path);
		
		photo.write(filename);
		
		request.setAttribute("uploadedPhotoURL", "img\\" + photo.getSubmittedFileName());
		request.setAttribute("uploadedPhotoName", photo.getSubmittedFileName());;
		request.getRequestDispatcher("/admin/management/room-manager").forward(request, response);
    }
}

package net.slipp.support.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slipp.factory.ServiceFactory;
import net.slipp.service.user.PasswordMismatchException;

@SuppressWarnings("serial")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	
    	try {
    		req.setAttribute(
    				"loginUser",
    				ServiceFactory.getUserService().login(
    						req.getParameter("userId"), 
    						req.getParameter("password")));
 
    		resp.sendRedirect("/");
    	} catch (PasswordMismatchException e) {
    		req.setAttribute("errorMessage", e.getMessage());
    		req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
    	} catch (Exception e) {
    		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    	}
    }
}

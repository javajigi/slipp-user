package net.slipp.support.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SlippUserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and <code>POST</code> methods.
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Handler handler = handlerMapping(request);
			String view = handler.excute(request);
			dispatch(request, response, view);
		} catch(Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private Handler handlerMapping(HttpServletRequest request) {
		String url = (String) request.getRequestURI();
		Handler handler = null;
		
		if (url.endsWith("/login.do")) {
			handler = new UserLoginHandler();
		} else if (url.endsWith("/join.do")) {
			handler = new UserJoinHandler();
		}
		
		return handler;
	}
	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String viewPage) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}

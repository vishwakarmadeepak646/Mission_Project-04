package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.util.ServletUtility;

/**
 * WelcomeCtl is a simple Servlet controller that handles routing to the Welcome page.
 * This is usually the landing page presented after a successful login.
*  @author Deepak Vishwakarma
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	/**
	 * Handles HTTP GET requests to forward the user to the Welcome view.
	 * * @param request  the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Returns the view page associated with the Welcome controller.
	 * * @return the logical view string
	 */
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;

	}

}
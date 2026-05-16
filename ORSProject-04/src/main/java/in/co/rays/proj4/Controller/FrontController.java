package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj4.util.ServletUtility;

/**
 * FrontController is a Servlet Filter that intercepts requests mapped to secure
 * URLs. It verifies if a user is logged into the session before allowing access
 * to internal resources.
 * 
 * @author Deepak Vishwakarma
 */
@WebFilter(filterName = "FrontCtl", urlPatterns = { "/ctl/*", "/doc/*" })
public class FrontController implements Filter {

	/**
	 * Initializes the filter. Called by the web container to indicate to a filter
	 * that it is being placed into service. * @param filterConfig the filter
	 * configuration object
	 * 
	 * @throws ServletException if an error occurs during initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * Intercepts the request to check for a valid user session. If the session is
	 * expired or invalid, it redirects the user to the login view. Otherwise, it
	 * passes the request along the filter chain. * @param request the Servlet
	 * request
	 * 
	 * @param response the Servlet response
	 * @param chain    the filter chain to proceed to the next resource
	 * @throws IOException      if an I/O error occurs
	 * @throws ServletException if a servlet-specific error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {
			ServletUtility.setErrorMessage("Your session has been Expired, Please login again", req);
			ServletUtility.forward(ORSView.LOGIN_VIEW, req, res);
		}

		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * Destroys the filter. Called by the web container to indicate to a filter that
	 * it is being taken out of service.
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
package in.co.rays.proj4.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.Controller.BaseCtl;
import in.co.rays.proj4.Controller.ORSView;
import in.co.rays.proj4.bean.BaseBean;

/**
 * ServletUtility is a utility class providing standardized methods to handle common 
 * Servlet operations, such as request forwarding, redirecting, message setting, 
 * and managing data flow between controllers and views.
*  @author Deepak Vishwakarma
 */
public class ServletUtility {

	/**
	 * Forwards the request to the specified page/view.
	 * * @param page the destination view URL
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @throws IOException if an input or output error occurs
	 * @throws ServletException if a servlet-specific error occurs
	 */
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * Redirects the response to the specified page.
	 * * @param page the destination URL
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @throws IOException if an input or output error occurs
	 * @throws ServletException if a servlet-specific error occurs
	 */
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	/**
	 * Extracts an error message stored in the request attribute.
	 * * @param property the attribute name
	 * @param request the HTTP request
	 * @return the error message, or an empty string if null
	 */
	public static String getErrorMessage(String property, HttpServletRequest request) {

		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Extracts a standard message stored in the request attribute.
	 * * @param property the attribute name
	 * @param request the HTTP request
	 * @return the message string, or an empty string if null
	 */
	public static String getMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets the generic error message attribute within the request.
	 * * @param msg the message string
	 * @param request the HTTP request
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * Retrieves the generic error message attribute from the request.
	 * * @param request the HTTP request
	 * @return the error message, or an empty string if null
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets the generic success message attribute within the request.
	 * * @param msg the message string
	 * @param request the HTTP request
	 */
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * Retrieves the generic success message attribute from the request.
	 * * @param request the HTTP request
	 * @return the success message, or an empty string if null
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a Bean object as an attribute into the HTTP request.
	 * * @param bean the bean object to attach
	 * @param request the HTTP request
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	/**
	 * Retrieves the attached Bean object from the HTTP request.
	 * * @param request the HTTP request
	 * @return the stored BaseBean object
	 */
	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	/**
	 * Standardizes parameter fetching, ensuring a null value is returned as an empty string.
	 * * @param property the parameter name
	 * @param request the HTTP request
	 * @return the parameter value, or an empty string
	 */
	public static String getParameter(String property, HttpServletRequest request) {
		String val = (String) request.getParameter(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a List object into the HTTP request (typically for generating tables).
	 * * @param list the data list to set
	 * @param request the HTTP request
	 */
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	/**
	 * Retrieves the List object from the HTTP request.
	 * * @param request the HTTP request
	 * @return the stored List object
	 */
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	/**
	 * Sets the current page number for pagination purposes.
	 * * @param pageNo the page number integer
	 * @param request the HTTP request
	 */
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	/**
	 * Retrieves the current page number from the request.
	 * * @param request the HTTP request
	 * @return the current page number
	 */
	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}

	/**
	 * Sets the page size limit (number of records per page) for pagination.
	 * * @param pageSize the size integer
	 * @param request the HTTP request
	 */
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * Retrieves the page size limit from the request.
	 * * @param request the HTTP request
	 * @return the page size
	 */
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}

	/**
	 * Centralized method to handle application exceptions by routing the user 
	 * to a standardized Error view.
	 * * @param e the exception thrown
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @throws IOException if an I/O error occurs during redirection
	 * @throws ServletException if a servlet processing error occurs
	 */
	public static void handleException(Exception e, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);
	}
}
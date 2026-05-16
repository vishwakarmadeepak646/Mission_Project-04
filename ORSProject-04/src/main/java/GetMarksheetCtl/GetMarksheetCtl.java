package GetMarksheetCtl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.rays.proj4.Controller.BaseCtl;
import in.co.rays.proj4.Controller.ORSView;
import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * GetMarksheetCtl is a Servlet controller that manages the retrieval and
 * display of a specific student's marksheet based on their Roll Number.
 * * @author Deepak Vishwakarma
 */
@WebServlet(name = "GetMarksheetCtl", urlPatterns = { "/ctl/GetMarksheetCtl" })
public class GetMarksheetCtl extends BaseCtl {

	/**
	 * Validates input data entered by the user on the Get Marksheet form. Ensures
	 * that the Roll Number field is not empty before processing. * @param request
	 * the HTTP servlet request
	 * 
	 * @return true if validation passes, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates the MarksheetBean from the HTTP request parameters. Specifically
	 * sets the Roll Number used to search for the marksheet. * @param request the
	 * HTTP servlet request
	 * 
	 * @return the populated BaseBean object containing the search criteria
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		return bean;
	}

	/**
	 * Handles HTTP GET requests to display the Get Marksheet input view. * @param
	 * request the HTTP servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests to fetch the marksheet details from the database.
	 * If the operation is "GO", it searches by Roll Number and displays the result
	 * or an error message if the record does not exist. * @param request the HTTP
	 * servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		MarksheetModel model = new MarksheetModel();

		MarksheetBean bean = (MarksheetBean) populateBean(request);

		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				bean = model.findByRollNo(bean.getRollNo());
				if (bean != null) {
					ServletUtility.setBean(bean, request);
				} else {
					ServletUtility.setErrorMessage("RollNo Does Not exists", request);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view page associated with the Get Marksheet controller. * @return
	 * the logical view string
	 */
	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}
}
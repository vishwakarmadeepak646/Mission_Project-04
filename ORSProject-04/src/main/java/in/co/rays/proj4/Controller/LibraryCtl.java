package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.LibraryModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "LibraryCtl", urlPatterns = {"/ctl/LibraryCtl"})
public class LibraryCtl extends BaseCtl {

    private static final Logger log = Logger.getLogger(LibraryCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("bookName"))) {
            request.setAttribute("bookName", PropertyReader.getValue("error.require", "Book Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("authorName"))) {
            request.setAttribute("authorName", PropertyReader.getValue("error.require", "Author Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("issueDate"))) {
            request.setAttribute("issueDate", PropertyReader.getValue("error.require", "Issue Date"));
            pass = false;
        } else if (!DataValidator.isDate(request.getParameter("issueDate"))) {
            request.setAttribute("issueDate", PropertyReader.getValue("error.date", "Issue Date"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("price"))) {
            request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
            pass = false;
        } else if (!DataValidator.isLong(request.getParameter("price"))) {
            request.setAttribute("price", "Price must be a valid number");
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        LibraryBean bean = new LibraryBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setBookName(DataUtility.getString(request.getParameter("bookName")));
        bean.setAuthorName(DataUtility.getString(request.getParameter("authorName")));
        bean.setIssueDate(DataUtility.getDate(request.getParameter("issueDate")));
        bean.setPrice(DataUtility.getLong(request.getParameter("price")));

        return bean; 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        LibraryModel model = new LibraryModel();

        if (id > 0) {
            try {
                LibraryBean bean = model.findByPk(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = DataUtility.getString(request.getParameter("operation"));
        LibraryModel model = new LibraryModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            LibraryBean bean = (LibraryBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Book added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Book Name " + bean.getBookName() + "  already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            LibraryBean bean = (LibraryBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Book updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Book Name " + bean.getBookName() + "  already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.LIBRARY_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.LIBRARY_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.LIBRARY_VIEW;
    }
}
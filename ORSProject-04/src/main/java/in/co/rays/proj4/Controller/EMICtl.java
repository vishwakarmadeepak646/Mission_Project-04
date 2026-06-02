package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.EMIBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.EMIModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "EMICtl", urlPatterns = {"/ctl/EMICtl"})
public class EMICtl extends BaseCtl {

    private static final Logger log = Logger.getLogger(EMICtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("amount"))) {
            request.setAttribute("amount", PropertyReader.getValue("error.require", "Amount"));
            pass = false;
        } else if (!DataValidator.isLong(request.getParameter("amount"))) {
            request.setAttribute("amount", "Amount must be a valid number");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("dueDate"))) {
            request.setAttribute("dueDate", PropertyReader.getValue("error.require", "Due Date"));
            pass = false;
        } else if (!DataValidator.isDate(request.getParameter("dueDate"))) {
            request.setAttribute("dueDate", PropertyReader.getValue("error.date", "Due Date"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("status"))) {
            request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        EMIBean bean = new EMIBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
        bean.setDueDate(DataUtility.getDate(request.getParameter("dueDate")));
        bean.setStatus(DataUtility.getString(request.getParameter("status")));

        populateDTO(bean, request); // Populates the audit columns
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        EMIModel model = new EMIModel();

        if (id > 0) {
            try {
                EMIBean bean = model.findByPk(id);
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
        EMIModel model = new EMIModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            EMIBean bean = (EMIBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("EMI added successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            EMIBean bean = (EMIBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("EMI updated successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.EMI_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.EMI_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.EMI_VIEW;
    }
}
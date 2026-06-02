package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.ATMModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "ATMCtl", urlPatterns = {"/ctl/ATMCtl"})
public class ATMCtl extends BaseCtl {

    private static final Logger log = Logger.getLogger(ATMCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("bankName"))) {
            request.setAttribute("bankName", PropertyReader.getValue("error.require", "Bank Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("location"))) {
            request.setAttribute("location", PropertyReader.getValue("error.require", "Location"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("cashAvailable"))) {
            request.setAttribute("cashAvailable", PropertyReader.getValue("error.require", "Cash Available"));
            pass = false;
        } else if (!DataValidator.isLong(request.getParameter("cashAvailable"))) {
            request.setAttribute("cashAvailable", "Cash Available must be a valid number");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("securityCode"))) {
            request.setAttribute("securityCode", PropertyReader.getValue("error.require", "Security Code"));
            pass = false;
        } else if (!DataValidator.isInteger(request.getParameter("securityCode"))) {
            request.setAttribute("securityCode", "Security Code must be an integer");
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        ATMBean bean = new ATMBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setBankName(DataUtility.getString(request.getParameter("bankName")));
        bean.setLocation(DataUtility.getString(request.getParameter("location")));
        bean.setCashAvailable(DataUtility.getLong(request.getParameter("cashAvailable")));
        bean.setSecurityCode(DataUtility.getInt(request.getParameter("securityCode")));

        populateDTO(bean, request); // Populates the audit columns
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        ATMModel model = new ATMModel();

        if (id > 0) {
            try {
                ATMBean bean = model.findByPk(id);
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
        ATMModel model = new ATMModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            ATMBean bean = (ATMBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("ATM added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Location " + bean.getLocation() + " already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            ATMBean bean = (ATMBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("ATM updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Location " + bean.getLocation() + " already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.ATM_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.ATM_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.ATM_VIEW;
    }
}
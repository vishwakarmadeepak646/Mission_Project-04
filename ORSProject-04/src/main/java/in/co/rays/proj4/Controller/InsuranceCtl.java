package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.InsuranceBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.InsuranceModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "InsuranceCtl", urlPatterns = {"/ctl/InsuranceCtl"})
public class InsuranceCtl extends BaseCtl {

    private static final Logger log = Logger.getLogger(InsuranceCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("customerName"))) {
            request.setAttribute("customerName", PropertyReader.getValue("error.require", "Customer Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("policyType"))) {
            request.setAttribute("policyType", PropertyReader.getValue("error.require", "Policy Type"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("premiumAmount"))) {
            request.setAttribute("premiumAmount", PropertyReader.getValue("error.require", "Premium Amount"));
            pass = false;
        } else if (!DataValidator.isInteger(request.getParameter("premiumAmount"))) {
            request.setAttribute("premiumAmount", "Premium Amount must be a number");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("claimStatus"))) {
            request.setAttribute("claimStatus", PropertyReader.getValue("error.require", "Claim Status"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        InsuranceBean bean = new InsuranceBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
        bean.setPolicyType(DataUtility.getString(request.getParameter("policyType")));
        bean.setPremiumAmount(DataUtility.getLong(request.getParameter("premiumAmount")));
        bean.setClaimStatus(DataUtility.getString(request.getParameter("claimStatus")));

        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        InsuranceModel model = new InsuranceModel();

        if (id > 0) {
            try {
                InsuranceBean bean = model.findByPk(id);
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
        InsuranceModel model = new InsuranceModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            InsuranceBean bean = (InsuranceBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Record added successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
            catch (DuplicateRecordException e) {
            	ServletUtility.setBean(bean, request);
            	ServletUtility.setErrorMessage("Customer Name " + bean.getCustomerName() + " already exists", request);
                e.printStackTrace();
                
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            InsuranceBean bean = (InsuranceBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Record updated successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
            catch (DuplicateRecordException e) {
            	ServletUtility.setBean(bean, request);
            	ServletUtility.setErrorMessage("Customer Name " + bean.getCustomerName() + " already exists", request);
                e.printStackTrace();
            
            }
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.INSURANCE_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.INSURANCE_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.INSURANCE_VIEW;
    }
}
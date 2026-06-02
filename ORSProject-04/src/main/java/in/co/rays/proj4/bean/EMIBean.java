package in.co.rays.proj4.bean;

import java.util.Date;

public class EMIBean extends BaseBean {

    private long amount;
    private Date dueDate;
    private String status;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getValue() {
        return status + " - " + amount;
    }
}
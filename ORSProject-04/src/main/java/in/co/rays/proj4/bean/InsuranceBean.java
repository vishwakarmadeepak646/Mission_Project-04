package in.co.rays.proj4.bean;

public class InsuranceBean extends BaseBean {

    private String customerName;
    private String policyType;
    private long premiumAmount;
    private String claimStatus;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public long getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(long premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    @Override
    public String getValue() {
        return customerName + " (" + policyType + ")";
    }
}
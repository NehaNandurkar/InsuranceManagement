package com.hexaware.entities;
import java.util.Date;

public class Policy {
    private int policyId;
    private String policyNumber;
    private String policyType;
    private double coverageAmount;
    private double premiumAmount;
    private Date startDate;
    private Date endDate;

   

    public Policy() {
    }

    public Policy(int policyId, String policyNumber, String policyType, double coverageAmount,
                  double premiumAmount, Date startDate, Date endDate) {
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

   

    public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyType=" + policyType
				+ ", coverageAmount=" + coverageAmount + ", premiumAmount=" + premiumAmount + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}


}

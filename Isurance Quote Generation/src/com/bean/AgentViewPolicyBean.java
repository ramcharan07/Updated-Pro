package com.bean;

public class AgentViewPolicyBean {

	private String agentName;
	private String insuredName;
	private long policyNumber;
	private double policyPremium;
	private long accountNumber;
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public double getPolicyPremium() {
		return policyPremium;
	}
	public void setPolicyPremium(double policyPremium) {
		this.policyPremium = policyPremium;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "AgentViewPolicy [agentName=" + agentName + ", insuredName=" + insuredName + ", policyNumber="
				+ policyNumber + ", policyPremium=" + policyPremium + ", accountNumber=" + accountNumber + "]";
	}
	
	
}

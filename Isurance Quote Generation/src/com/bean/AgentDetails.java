package com.bean;

public class AgentDetails{
	private String agentName;
	private int noOfCustomers;
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public int getNoOfCustomers() {
		return noOfCustomers;
	}
	public void setNoOfCustomers(int noOfCustomers) {
		this.noOfCustomers = noOfCustomers;
	}
	@Override
	public String toString() {
		return "AgentDetails [agentName=" + agentName + ", noOfCustomers=" + noOfCustomers + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

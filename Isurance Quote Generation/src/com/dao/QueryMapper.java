package com.dao;

public interface QueryMapper {
	
	String INSERT_QUERY = "INSERT INTO agent_create_account VALUES(?,?,?,?,?,?,?,?,?)";
	String FIND_AGENT_NAME = "Select username from profiledetails where username=?";
	String VIEW_POLICY_DETAILS_QUERY = "Select insuredName,policyNumber,PremimumAmount,accountNumber from viewPolicy where agentName=?";
	String SEARCH_POLICY = "Select question,answer1,answer2,answer3 from question_bank where business_Segment=?";
	String CREATE_POLICY = "Insert into policy_creation values(?,?,?,?,?)";
	String FIND_ROLE = "Select roleCode from profiledetails where username=?";
	String CREATE_PROFILE = "Insert into profiledetails values(?,?,?)";

}

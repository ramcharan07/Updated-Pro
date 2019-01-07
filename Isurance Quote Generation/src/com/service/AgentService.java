package com.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.AgentBean;
import com.bean.AgentDetails;
import com.bean.AgentPolicyCreationBean;
import com.bean.QuestionBean;
import com.bean.AgentViewPolicyBean;
import com.bean.CustomerDetails;
import com.bean.ProfileCreation;
import com.exception.AgentException;

public interface AgentService {

	public void accountCreation(AgentBean agentBean) throws AgentException, SQLException, IOException;
	AgentViewPolicyBean getPolicyDetails(String agentName) throws AgentException, SQLException, IOException ;
	public ArrayList<QuestionBean> getQuestionAnswer(QuestionBean questionBean) throws AgentException, IOException;
	public void policyCreation(AgentPolicyCreationBean agentPolicyCreationBean) throws SQLException, IOException, AgentException;
	public void  addProfile(ProfileCreation profileCreation) throws AgentException, SQLException, IOException;
	public List<AgentDetails> viewPolicy() throws SQLException, IOException;
	public List<CustomerDetails> viewCustomers() throws SQLException, IOException;
	public List<CustomerDetails> customerDetails() throws SQLException, IOException;
	public ArrayList<QuestionBean> createPolicy(String string);
	public int PolicyQuestion(QuestionBean questionBean);
}

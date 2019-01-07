package com.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bean.AgentBean;
import com.bean.AgentDetails;
import com.bean.AgentPolicyCreationBean;
import com.bean.QuestionBean;
import com.bean.AgentViewPolicyBean;
import com.bean.CustomerDetails;
import com.bean.LoginBean;
import com.bean.ProfileCreation;
import com.dao.AgentDao;
import com.dao.AgentDaoIMPL;
import com.exception.AgentException;

public class AgentServiceIMPL implements AgentService{

	AgentDao agentDao;
	AgentDaoIMPL agentDaoImpl;
	
	public String checkUser(LoginBean loginBean) throws AgentException, SQLException, IOException
	{
		String role="";
		agentDaoImpl=new AgentDaoIMPL();
		role=agentDaoImpl.checkUser(loginBean);
		return role;
	}
	@Override
	public void accountCreation(AgentBean agentBean) throws AgentException, SQLException, IOException {
		
		agentDao=new AgentDaoIMPL();
		agentDao.accountCreation(agentBean);
	}

	public void validateAgent(AgentBean agentBean) throws AgentException, SQLException, IOException
	{
		
		List<String> validationErrors=new ArrayList<>();
		if(!(isValidInsuredName(agentBean.getInsuredName())))
		{
			validationErrors.add("\nFirst letter of insured name should be capital!!");
		}
		if(!(isValidInsuredStreet(agentBean.getInsuredStreet())))
		{
			validationErrors.add("\nFirst letter of city name should be capital & should contain min three characters!!");
		}
		if(!(isValidInsuredCity(agentBean.getInsuredCity())))
		{
			validationErrors.add("\nFirst letter of city name should be capital & should contain min three characters!!");
		}
		if(!(isValidInsuredState(agentBean.getInsuredState())))
		{
			validationErrors.add("\nFirst letter of state name should be capital & should contain min three characters!!");
		}
		if(!(isValidInsuredZip(agentBean.getInsuredZip())))
		{
			validationErrors.add("\nZip code should be 6 character long & it should be numbers!!");
		}
		if(!(isValidAccountNumber(agentBean.getAccountNumber())))
		{
			validationErrors.add("\nAccount number should consists 10 digits");
		}
		if(!(isValidAgentName(agentBean.getAgentName())))
		{
			validationErrors.add("\nAgent Name does not exist!!");
		}
		if(!validationErrors.isEmpty())
		{
			try {
				throw new AgentException(validationErrors+"");
			} catch (AgentException e) {
				System.err.println(e);
			}
		}
	}

	private boolean isValidAccountNumber(long accountNumber) {
		
		Pattern accountNumberPattern=Pattern.compile("[1-9][0-9]{9}");
		Matcher accountNumberMatcher=accountNumberPattern.matcher(String.valueOf(accountNumber));
		return accountNumberMatcher.matches();
	}

	private boolean isValidAgentName(String agentName) throws AgentException, SQLException, IOException {
		
	agentDaoImpl=new AgentDaoIMPL();
		boolean find=agentDaoImpl.findAgentName(agentName);
		return find;
	}

	private boolean isValidInsuredZip(long insuredZip) {
		
		Pattern zipPattern=Pattern.compile("[1-9][0-9]{5}");
		Matcher zipMatcher=zipPattern.matcher(String.valueOf(insuredZip));
		return zipMatcher.matches();
		
	}

	private boolean isValidInsuredState(String insuredState) {
		
		Pattern statePattern=Pattern.compile("[A-Z][a-z]{3,15}");
		Matcher stateMatcher=statePattern.matcher(insuredState);
		return stateMatcher.matches();
	}

	private boolean isValidInsuredCity(String insuredCity) {
		
		Pattern cityPattern=Pattern.compile("[A-Z][a-z]{3,15}");
		Matcher cityMatcher=cityPattern.matcher(insuredCity);
		return cityMatcher.matches();
	}

	private boolean isValidInsuredStreet(String insuredStreet) {
		
		Pattern streetPattern=Pattern.compile("[a-zA-Z0-9]{3,40}");
		Matcher streetMatcher=streetPattern.matcher(insuredStreet);
		return streetMatcher.matches();
	}

	private boolean isValidInsuredName(String insuredName) {
		
		Pattern namePattern=Pattern.compile("[A-Z][a-z]{1,30}");
		Matcher nameMatcher=namePattern.matcher(insuredName);
		return nameMatcher.matches();
	}

	@Override
	public AgentViewPolicyBean getPolicyDetails(String agentName) throws AgentException, SQLException, IOException {
		
		agentDao= new AgentDaoIMPL();
		AgentViewPolicyBean agentViewPolicyBean=new AgentViewPolicyBean();
		agentViewPolicyBean=agentDao.getPolicyDetails(agentName);
		return agentViewPolicyBean;
	}

	@Override
	public ArrayList<QuestionBean> getQuestionAnswer(QuestionBean questionBean) throws AgentException, IOException {
		
		ArrayList<QuestionBean> al = new ArrayList<>();
		al = agentDao.getQuestionAnswer(questionBean);
		return al;
	}

	@Override
	public void policyCreation(AgentPolicyCreationBean agentPolicyCreationBean) throws SQLException, IOException, AgentException {

		agentDao=new AgentDaoIMPL();
		agentDao.policyCreation(agentPolicyCreationBean);
		
	}
	@Override
	public void addProfile(ProfileCreation profileCreation) throws AgentException, SQLException, IOException {

		agentDao=new AgentDaoIMPL();
		
		agentDao.addProfile(profileCreation);
		
	}
	@Override
	public List<AgentDetails> viewPolicy() throws SQLException, IOException {
		agentDao=new AgentDaoIMPL();
		List<AgentDetails> arrayValues=new ArrayList<>();
		arrayValues=agentDao.viewPolicy();
		return arrayValues;
	}
	@Override
	public List<CustomerDetails> viewCustomers() throws SQLException, IOException {
		agentDao=new AgentDaoIMPL();
		List<CustomerDetails> arrayOutput=new ArrayList<>();
		arrayOutput=agentDao.viewCustomers();
		return arrayOutput;
	}
	@Override
	public List<CustomerDetails> customerDetails() throws SQLException, IOException {
		agentDao=new AgentDaoIMPL();
		List<CustomerDetails> array=new ArrayList<>();
		array=agentDao.customerDetails();
		return array;
	}

	
}

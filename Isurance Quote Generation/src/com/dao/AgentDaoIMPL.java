package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.bean.AgentBean;
import com.bean.AgentDetails;
import com.bean.AgentPolicyCreationBean;
import com.bean.QuestionBean;
import com.bean.AgentViewPolicyBean;
import com.bean.CustomerDetails;

import com.bean.LoginBean;
import com.bean.ProfileCreation;
import com.exception.AgentException;
import com.util.DBConnection;

public class AgentDaoIMPL implements AgentDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultset = null;
	AgentBean agentBean = null;

	// Agent account creation

	@Override
	public void accountCreation(AgentBean agentBean) throws AgentException, SQLException, IOException {
		connection = DBConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.INSERT_QUERY);
			preparedStatement.setString(1, agentBean.getAgentName());
			preparedStatement.setString(2, agentBean.getUsername());
			preparedStatement.setString(3, agentBean.getInsuredName());
			preparedStatement.setString(4, agentBean.getInsuredStreet());
			preparedStatement.setString(5, agentBean.getInsuredCity());
			preparedStatement.setString(6, agentBean.getInsuredState());
			preparedStatement.setLong(7, agentBean.getInsuredZip());
			preparedStatement.setLong(8, agentBean.getAccountNumber());
			preparedStatement.setString(9, agentBean.getBusinessSegment());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			throw new AgentException("Tehnical problem occured. Refer log");
		}

	}

	public boolean findAgentName(String agentName) throws AgentException, SQLException, IOException {
		connection = DBConnection.getConnection();
		String agentName1;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.FIND_AGENT_NAME);
			preparedStatement.setString(1, agentName);
			resultset = preparedStatement.executeQuery();
			agentName1 = resultset.getString(1);
			if (agentName == agentName1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {

			throw new AgentException("Tehnical problem occured. Refer log");
		}

	}

	@Override
	public AgentViewPolicyBean getPolicyDetails(String agentName) throws AgentException, SQLException, IOException {

		connection = DBConnection.getConnection();
		AgentViewPolicyBean agentViewPolicyBean = null;

		try {
			agentViewPolicyBean = new AgentViewPolicyBean();
			preparedStatement = connection.prepareStatement(QueryMapper.VIEW_POLICY_DETAILS_QUERY);
			preparedStatement.setString(1, agentName);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {

				agentViewPolicyBean.setInsuredName(resultset.getString(1));
				agentViewPolicyBean.setPolicyNumber(resultset.getLong(2));
				agentViewPolicyBean.setPolicyPremium(resultset.getDouble(3));
				agentViewPolicyBean.setAccountNumber(resultset.getLong(4));

			}

			if (agentViewPolicyBean != null) {

				// logger.info("Record Found Successfully");

				return agentViewPolicyBean;
			} else {
				// logger.info("Record Not Found Successfully");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			// logger.error(e.getMessage());
			throw new AgentException(e.getMessage());
		} finally {
			try {
				// resultset.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// logger.error(e.getMessage());
				throw new AgentException("Error in closing db connection");

			}
		}

	}

	@Override
	public ArrayList<QuestionBean> getQuestionAnswer(QuestionBean questionBean) throws AgentException, IOException {

		AgentPolicyCreationBean agentPolicyCreationBean = new AgentPolicyCreationBean();
		ArrayList<QuestionBean> al = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(QueryMapper.SEARCH_POLICY);
			preparedStatement.setString(1, questionBean.getBusinessSegment());
			preparedStatement.executeQuery();

			while (resultset.next()) {

				questionBean.setQuestion(resultset.getString(1));
				questionBean.setAnswer1(resultset.getString(2));
				questionBean.setAnswer2(resultset.getString(3));
				questionBean.setAnswer3(resultset.getString(4));

			}
		} catch (SQLException e) {

			throw new AgentException("Error in Policy Creation");

		}

		return al;
	}

	@Override
	public void policyCreation(AgentPolicyCreationBean agentPolicyCreationBean)
			throws SQLException, IOException, AgentException {

		connection = DBConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.CREATE_POLICY);
			preparedStatement.setString(1, agentPolicyCreationBean.getBusinessSegment());
			preparedStatement.setString(2, agentPolicyCreationBean.getQuestion());
			preparedStatement.setString(3, agentPolicyCreationBean.getAnswer());
			preparedStatement.setInt(4, agentPolicyCreationBean.getWeightage());
			preparedStatement.setString(5, agentPolicyCreationBean.getUsername());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			throw new AgentException("Error in policy creation");
		}
	}

	public String checkUser(LoginBean loginBean) throws AgentException, SQLException, IOException {

		connection = DBConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.FIND_ROLE);
			preparedStatement.setString(1, loginBean.getUserName());
			resultset = preparedStatement.executeQuery();
			String role = resultset.getString(1);
			return role;
		} catch (SQLException e) {

			throw new AgentException("Error in check user");
		}
	}

	@Override
	public void addProfile(ProfileCreation profileCreation) throws AgentException, SQLException, IOException {
		
		connection = DBConnection.getConnection();	
		try
		{		
			preparedStatement=connection.prepareStatement(QueryMapper.CREATE_PROFILE);

			preparedStatement.setString(1,profileCreation.getUserName());			
			preparedStatement.setString(2,profileCreation.getPassword());
			preparedStatement.setString(3,profileCreation.getRoleCode());
			preparedStatement.executeUpdate();
		}
		catch(SQLException sqlException)
		{
			throw new AgentException("Error in policy creation");
				
		}
	}

	@Override
	public List<AgentDetails> viewPolicy() throws SQLException, IOException {
		Connection con=DBConnection.getConnection();
		int donorCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<AgentDetails> al=new ArrayList<AgentDetails>();
		try
		{
			ps=con.prepareStatement("select * from agent");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				AgentDetails agent=new AgentDetails();
				//agent.setAgentId(resultset.getString(1));
				agent.setAgentName(resultset.getString(1));
				agent.setNoOfCustomers(resultset.getInt(2));
				al.add(agent);
				
				
			
			}			
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return al;

	}

	@Override
	public List<CustomerDetails> viewCustomers() throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection con=DBConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<CustomerDetails> arrayList=new ArrayList<>();
		try
		{
			ps=con.prepareStatement("select c.insuredname,c.agentname,c.businesssegment from agent_create_account c,agent a where c.agentname=a.agentname and a.agentname=?");
		
			//ps.setString(1,agentId); 
				 

			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				CustomerDetails customer=new CustomerDetails();
				customer.setInsuredName(resultset.getString(1));
				customer.setAgentName(resultset.getString(2));
				customer.setBusinessSegment(resultset.getString(3));
				arrayList.add(customer);
			}		
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public List<CustomerDetails> customerDetails() throws SQLException, IOException {
		// TODO Auto-generated method stub
		Connection con=DBConnection.getConnection();
		//PreparedStatement ps=null;
		ResultSet resultset = null;
		Statement st=null;
		List<CustomerDetails> a3=new ArrayList<CustomerDetails>();
		try
		{
			st=con.createStatement();
			resultset=st.executeQuery("select agentname,insuredname,accountnumber,businesssegment,policynumber,premiumamount from agent_create_account");
		
			
			
			while(resultset.next())
			{	
				CustomerDetails customerdetails=new CustomerDetails();
				
				
				
				customerdetails.setAgentName(resultset.getString(1));
				
				customerdetails.setInsuredName(resultset.getString(2));
				customerdetails.setAccountNumber(resultset.getLong(3));
				customerdetails.setBusinessSegment(resultset.getString(4));
				customerdetails.setPolicyNumber(resultset.getLong(5));
				customerdetails.setPremiumAmount(resultset.getLong(6));
				
				
				a3.add(customerdetails);
				
			}		
			
		} catch (SQLException e) {
		System.out.println("enter");
		}
		return a3;
	}

}

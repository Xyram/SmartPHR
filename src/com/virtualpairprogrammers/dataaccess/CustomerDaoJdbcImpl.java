package com.virtualpairprogrammers.dataaccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class CustomerDaoJdbcImpl implements CustomerDao {

	private static final String CREATE_CALL_TABLE_SQL = "CREATE TABLE TBL_CALL(NOTES VARCHAR(255), TIME_AND_DATE DATE, CUSTOMER_ID VARCHAR(20))";
	private static final String CREATE_CUSTOMER_TABLE_SQL = "CREATE TABLE CUSTOMER(CUSTOMER_ID VARCHAR(20), COMPANY_NAME VARCHAR(50), EMAIL VARCHAR(50), TELEPHONE VARCHAR(20), NOTES VARCHAR(255)) ";
	private static final String UPDATE_CUSTOMER_SQL = "UPDATE CUSTOMER SET COMPANY_NAME=?, EMAIL=?, NOTES=?, TELEPHONE=? WHERE CUSTOMER_ID=?";
	private static final String SELECT_CALL_DETAILS = "SELECT * FROM TBL_CALL WHERE CUSTOMER_ID = ?";
	private static final String SELECT_BY_COMPANY_NAME_SQL = "SELECT * FROM CUSTOMER WHERE COMPANY_NAME = ?";
	private static final String SELECT_BY_ID_SQL = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
	private static final String INSERT_CALL_SQL = "INSERT INTO TBL_CALL (NOTES, TIME_AND_DATE, CUSTOMER_ID) VALUES (?,?,?)";
	private static final String DELETE_CUSTOMER_SQL = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
	private static final String SELECT_ALL_CUSTOMERS_SQL = "SELECT CUSTOMER_ID, COMPANY_NAME, EMAIL, NOTES, TELEPHONE FROM CUSTOMER";
	private static final String CUSTOMER_INSERT_SQL = "INSERT INTO CUSTOMER (CUSTOMER_ID, COMPANY_NAME, EMAIL, NOTES, TELEPHONE) VALUES (?,?,?,?,?)";
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	private void createTables()
	{
		try
		{
		   this.template.update(CREATE_CUSTOMER_TABLE_SQL);
		   this.template.update(CREATE_CALL_TABLE_SQL);
		}
		catch (org.springframework.jdbc.BadSqlGrammarException e)
		{
			System.out.println("Assuming the tables already exist");
		}
	}

	public void create(Customer customer) 
	{
		this.template.update(CUSTOMER_INSERT_SQL, 
				             new Object[] {customer.getCustomerId(),
										   customer.getCompanyName(),
										   customer.getEmail(),
										   customer.getNotes(),
										   customer.getTelephone()});
	}

	public void delete(Customer oldCustomer) throws RecordNotFoundException 
	{
		int rowsDeleted = this.template.update(DELETE_CUSTOMER_SQL, 
							 new Object[] {oldCustomer.getCustomerId()});
		
		if (rowsDeleted == 0)
		{
			throw new RecordNotFoundException();
		}
	}

	public List<Customer> getAllCustomers() 
	{
		List<Customer> results = template.query(SELECT_ALL_CUSTOMERS_SQL, new CustomerRowMapper());
		return results;
	}

	public Customer getById(String customerId) throws RecordNotFoundException 
	{
		return (Customer)template.queryForObject(SELECT_BY_ID_SQL,
							  new Object[] {customerId},
							  new CustomerRowMapper());
	}

	public List<Customer> getByName(String name) 
	{
		return template.query(SELECT_BY_COMPANY_NAME_SQL, 
							  new Object[] {name},
							  new CustomerRowMapper());
	}


	public void addCall (Call newCall, String customerId)
	{
		Object[] params = new Object[] {newCall.getNotes(), newCall.getTimeAndDate(), customerId };
		template.update(INSERT_CALL_SQL, params);
	}
	
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException 
	{
		Customer customerInfo = this.getById(customerId);
		
		List<Call> calls = template.query(SELECT_CALL_DETAILS, 
							              new Object[] {customerId},
							              new CallRowMapper());
		
		customerInfo.setCalls(calls);
		
		return customerInfo;
	}

	public void update(Customer customerToUpdate)
			throws RecordNotFoundException 
	{
		int rowsAffected = this.template.update(UPDATE_CUSTOMER_SQL,
							 new Object[] { 
									customerToUpdate.getCompanyName(),
									customerToUpdate.getEmail(),
									customerToUpdate.getNotes(),
									customerToUpdate.getTelephone(),
									customerToUpdate.getCustomerId()});
		
		if (rowsAffected == 0)
		{
			throw new RecordNotFoundException();
		}
	}
	
	// Inner class for the row mapping
	private static class CustomerRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int rowNumber) throws SQLException 
		{
			String customerId = rs.getString(1);
			String companyName = rs.getString(2);
			String email = rs.getString(3);
			String notes = rs.getString(4);
			String telephone = rs.getString(5);

			return new Customer(customerId, companyName, email, notes, telephone);
		}
		
	}
	
	private static class CallRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int rowNumber) throws SQLException 
		{
			String notes = rs.getString(1);
			Date timeAndDate = rs.getDate(2);
			
			return new Call(notes, timeAndDate);
		}
		
	}

}

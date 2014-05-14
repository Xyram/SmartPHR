package com.virtualpairprogrammers.customers;

import java.util.List;

import com.virtualpairprogrammers.dataaccess.CustomerDao;
import com.virtualpairprogrammers.dataaccess.RecordNotFoundException;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class CustomerServiceProductionImpl implements CustomerManagementService {

	private CustomerDao dao;
	
	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}

	public void deleteCustomer(Customer oldCustomer)
			throws CustomerNotFoundException 
	{
		try
		{
		   this.dao.delete(oldCustomer);
		}
		catch (RecordNotFoundException exception)
		{
			throw new CustomerNotFoundException();
		}
	}

	public Customer findCustomerById(String customerId)
			throws CustomerNotFoundException 
	{
		try
		{
		   return dao.getById(customerId);
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}

	public List<Customer> findCustomersByName(String name) 
	{
		return dao.getByName(name);
	}

	public List<Customer> getAllCustomers() 
	{
		return dao.getAllCustomers();
	}

	public Customer getFullCustomerDetail(String customerId)
			throws CustomerNotFoundException 
	{
		try
		{
		   return dao.getFullCustomerDetail(customerId);
		}
		catch (RecordNotFoundException e)
		{
			throw new CustomerNotFoundException();
		}
	}

	public void newCustomer(Customer newCustomer) 
	{
		dao.create(newCustomer);
	}

	public void recordCall(String customerId, Call callDetails)
			throws CustomerNotFoundException 
	{
		
		try 
		{
			dao.addCall(callDetails, customerId);
		} 
		catch (RecordNotFoundException e) 
		{
			throw new CustomerNotFoundException();
		}
	}

	public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException 
	{
		try 
		{
			dao.update(changedCustomer);
		} 
		catch (RecordNotFoundException e) 
		{
			throw new CustomerNotFoundException();
		}
	}
}

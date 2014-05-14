package com.virtualpairprogrammers.customers;

import java.util.List;

import com.virtualpairprogrammers.dataaccess.CustomerDao;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class CustomerManagementServiceProductionImpl implements CustomerManagementService 
{
	private CustomerDao dao;
	
	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}

	public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException
	{

	}

	@Override
	public Customer findCustomerById(String customerId)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getFullCustomerDetail(String customerId)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newCustomer(Customer newCustomer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recordCall(String customerId, Call callDetails)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		// TODO Auto-generated method stub

	}

}

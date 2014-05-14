package com.virtualpairprogrammers.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class CustomerServiceMockImpl implements CustomerManagementService 
{
	private Map<String, Customer> customerMap;
	
	public CustomerServiceMockImpl()
	{
		this.customerMap = new HashMap<String, Customer>();
		
		this.customerMap.put("C10292", new Customer("C10292", "Acme", "Some notes"));
		this.customerMap.put("C10322", new Customer("C10322", "Inception Training", "Some notes"));
		this.customerMap.put("C15432", new Customer("C15432", "VirtualPairProgrammers", "Some notes"));
		this.customerMap.put("C11256", new Customer("C11256", "Microsoft ", "Some notes"));
	}

	public void newCustomer(Customer newCustomer) 
	{
		this.customerMap.put(newCustomer.getCustomerId(), newCustomer);
	}
	
	public void deleteCustomer(Customer oldCustomer) 
	{
		this.customerMap.remove(oldCustomer.getCustomerId());
	}

	public Customer findCustomerById(String customerId)
			throws CustomerNotFoundException 
	{
		Customer foundCustomer = this.customerMap.get(customerId);
		if (foundCustomer == null)
		{
			throw new CustomerNotFoundException();
		}
		return foundCustomer;
	}

	public List<Customer> findCustomersByName(String name) 
	{
		List<Customer> returnList = new ArrayList<Customer>();
		
		for (Customer next : this.customerMap.values())
		{
			if (next.getCompanyName().equals(name))
			{
				returnList.add(next);
			}
		}
		return returnList;
	}

	public List<Customer> getAllCustomers() 
	{
		return new ArrayList<Customer>(this.customerMap.values());
	}

	public Customer getFullCustomerDetail(String customerId)
			throws CustomerNotFoundException 
	{
		return this.findCustomerById(customerId);
	}

	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException 
	{
		Customer required = this.customerMap.get(customerId);
		
		if (required == null)
		{
			throw new CustomerNotFoundException();
		}
		
		required.addCall(callDetails);
	}	

	public void updateCustomer(Customer changedCustomer) 
	{
		this.customerMap.put(changedCustomer.getCustomerId(), changedCustomer);
	}
}

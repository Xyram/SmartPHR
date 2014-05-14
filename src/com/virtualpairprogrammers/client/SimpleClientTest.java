package com.virtualpairprogrammers.client;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.calls.CallHandlingService;
import com.virtualpairprogrammers.customers.CustomerManagementService;
import com.virtualpairprogrammers.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.diary.DiaryManagementService;
import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class SimpleClientTest 
{
	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		CustomerManagementService customerService = (CustomerManagementService)container.getBean("customerService");
		CallHandlingService callService = (CallHandlingService)container.getBean("callService");
		DiaryManagementService diaryService = (DiaryManagementService)container.getBean("diaryService");
		
		Customer newCustomer = new Customer("D0202020", "Google", "Make a search engine.");
		
		customerService.newCustomer(newCustomer);
		
		Call larrysCall = new Call("Larry Wall from Google Called.");
		
		Collection<Action> actionList = new java.util.ArrayList<Action>();
		actionList.add(new Action("Call Larry back asap", new GregorianCalendar(2009, 0, 0), "rac"));
		actionList.add(new Action("Contact marketing to make sure we make Larry happy", new GregorianCalendar(2009, 2, 5), "rac"));
		
		try
		{
			callService.recordCall("D0202020", larrysCall, actionList);			
		}
		catch (CustomerNotFoundException e)
		{
			System.out.println("Sorry, that customer couldn't be found");
		}		
		
		List<Customer> customers = customerService.getAllCustomers();
		
		for (Customer nextCustomer : customers)
		{
			System.out.println(nextCustomer);
		}		
		
		System.out.println("\n---- All outstanding actions for RAC:\n");
		
		// list diary contents
		Collection<Action> actionsForRichardChesterwood = diaryService.getAllIncompleteActions("rac");
		for (Action nextAction : actionsForRichardChesterwood)
		{
			System.out.println(nextAction);
		}
		
		
		// get a single customer
		try 
		{
			Customer customer = customerService.getFullCustomerDetail("D0202020");
			System.out.println("All calls for this customer:");
			for (Call nextCall : customer.getCalls())
			{
				System.out.println(nextCall);
			}
			
			customerService.deleteCustomer(customer);
		} 
		catch (CustomerNotFoundException e) 
		{
			System.out.println("Customer not found");
		}

		
		container.close();
	}

}

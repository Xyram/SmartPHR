package com.virtualpairprogrammers.calls;

import java.util.Collection;

import com.virtualpairprogrammers.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;

public interface CallHandlingService 
{
	// DO NOT IMPLEMENT THIS INTERFACE UNTIL CHAPTER 4!
	
	/**
	 * Records a call with the customer management service, and also records
	 * any actions in the diary service
	 */
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}

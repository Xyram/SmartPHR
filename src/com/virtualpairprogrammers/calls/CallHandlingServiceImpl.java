package com.virtualpairprogrammers.calls;

import java.util.Collection;

import com.virtualpairprogrammers.customers.CustomerManagementService;
import com.virtualpairprogrammers.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.diary.DiaryManagementService;
import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;

public class CallHandlingServiceImpl implements CallHandlingService {

	private CustomerManagementService customerService;
	private DiaryManagementService diaryService;
	
	public void setCustomerService(CustomerManagementService customerService) {
		this.customerService = customerService;
	}

	public void setDiaryService(DiaryManagementService diaryService) {
		this.diaryService = diaryService;
	}

	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException 
	{
		// Step 1 : update customer service with the new call
		customerService.recordCall(customerId, newCall);
		
		// Step 2 : update diary with the new actions
		for (Action next : actions)
		{
			diaryService.recordAction(next);
		}

	}

}

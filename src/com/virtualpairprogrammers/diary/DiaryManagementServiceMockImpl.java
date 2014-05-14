package com.virtualpairprogrammers.diary;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.domain.Action;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {

	private List<Action> actionList;
	
	public DiaryManagementServiceMockImpl()
	{
		this.actionList = new ArrayList<Action>();
	}
	
	public List<Action> getAllIncompleteActions(String requiredUser) 
	{
		List<Action> returnList = new ArrayList<Action>();
		
		for (Action next : this.actionList)
		{
			if (next.getOwningUser().equals(requiredUser) && !next.isComplete())
			{
				returnList.add(next);
			}
		}

		return returnList;
	}

	public void recordAction(Action action) 
	{
		this.actionList.add(action);
	}

}

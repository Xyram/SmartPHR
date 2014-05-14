package com.virtualpairprogrammers.diary;

import java.util.List;

import com.virtualpairprogrammers.dataaccess.ActionDao;
import com.virtualpairprogrammers.domain.Action;

public class DiaryManagementServiceProductionImpl implements
		DiaryManagementService 
{
	private ActionDao dao;
	
	public void setDao(ActionDao dao) {
		this.dao = dao;
	}

	public List<Action> getAllIncompleteActions(String requiredUser) 
	{
		return dao.getIncompleteActions(requiredUser);
	}

	public void recordAction(Action action) 
	{
		this.dao.create(action);
	}

}

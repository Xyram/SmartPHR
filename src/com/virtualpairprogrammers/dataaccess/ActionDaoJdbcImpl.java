package com.virtualpairprogrammers.dataaccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.virtualpairprogrammers.domain.Action;

public class ActionDaoJdbcImpl implements ActionDao
{
	private static final String DELETE_SQL = "DELETE FROM ACTION WHERE ACTION_ID=?";
	private static final String UPDATE_SQL = "UPDATE ACTION SET DETAILS=?, COMPLETE=?, OWNING_USER=?, REQUIRED_BY=? WHERE ACTION_ID=?";
	private static final String INSERT_SQL = "INSERT INTO ACTION (DETAILS, COMPLETE, OWNING_USER, REQUIRED_BY) VALUES (?,?,?,?)";
	private static final String GET_INCOMPLETE_SQL = "SELECT ACTION_ID, DETAILS, COMPLETE, OWNING_USER, REQUIRED_BY FROM ACTION WHERE OWNING_USER=? AND COMPLETE=?";
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void create(Action newAction) 
	{
		Object[] params = new Object[] { newAction.getDetails(),
										 newAction.isComplete(),
										 newAction.getOwningUser(),
										 newAction.getRequiredBy() };
						
		template.update(INSERT_SQL,params);					
	}

	private void createTables()
	{
		try
		{
		   this.template.update("CREATE TABLE ACTION (ACTION_ID VARCHAR(20), DETAILS VARCHAR(255), COMPLETE BOOLEAN, OWNING_USER VARCHAR(20), REQUIRED_BY DATE)");
		}
		catch (org.springframework.jdbc.BadSqlGrammarException e)
		{
			System.out.println("Assuming the Action table exists");
		}
	}
	
	public List<Action> getIncompleteActions(String userId) 
	{		
		return this.template.query(GET_INCOMPLETE_SQL, new Object[]{userId, false}, new ActionRowMapper());
	}

	public void update(Action actionToUpdate) throws RecordNotFoundException 
	{
		Object[] params = new Object[] {actionToUpdate.getDetails(), actionToUpdate.isComplete(), actionToUpdate.getOwningUser(), actionToUpdate.getRequiredBy().getTime(), actionToUpdate.getActionId()};
		this.template.update(UPDATE_SQL,params);
	}

	public void delete(Action oldAction) throws RecordNotFoundException 
	{
		this.template.update(DELETE_SQL, new Object[] {oldAction.getActionId()});
	}

	private static class ActionRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			String actionId = rs.getString(1);
			String details = rs.getString(2);
			boolean complete = rs.getBoolean(3);
			String owningUser = rs.getString(4);
			Date requiredBy = rs.getDate(5);
			
			Calendar requiredByCal = new java.util.GregorianCalendar();
			requiredByCal.setTime(requiredBy);
			
			return new Action(actionId, details, requiredByCal, owningUser, complete);
		}
		
	}
}

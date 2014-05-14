package com.virtualpairprogrammers.domain;

import java.util.Calendar;

/**
 * Represents the details of an action to be carried out.
 *
 * @author Richard Chesterwood
 */
public class Action 
{
	/**
	 * Some kind of neutral key
	 */
	private String actionId;
	
	/**
	 * Details of the action - eg "Ask John in accounts if we can afford a new order"
	 */
	private String details;
	
	/**
	 * The "required by" date and time.
	 */
	private Calendar requiredBy;
	
	/**
	 * The user that owns this action.
	 */
	private String owningUser;
	
	/**
	 * Is this action complete?
	 */
	private boolean complete;
	
	/**
	 * Constructor of Action
	 * @param details eg "Ask John in accounts if we can afford a new order"
	 * @param requiredBy eg "10am, 5 October 2009"
	 * @param owningUser "Mike Jones"
	 */
	public Action(String details, Calendar requiredBy, String owningUser)
	{
		this.details = details;
		this.requiredBy = requiredBy;
		this.owningUser = owningUser;
		this.complete = false;
	}
	
	public Action(String actionId, String details, Calendar requiredBy, String owningUser, boolean complete)
	{
		this.details = details;
		this.requiredBy = requiredBy;
		this.owningUser = owningUser;
		this.complete = complete;
		this.actionId = actionId;
	}
	
	/**
	 * Determines if this Action is overdue
	 */
	public boolean isOverdue()
	{
		Calendar dateNow = new java.util.GregorianCalendar();
		
		return dateNow.after(this.requiredBy);
	}
	
	/**
	 * A simple toString - feel free to change it
	 */
	public String toString()
	{
		return "Action for " + this.owningUser + ": " + this.details + ", required by " + this.requiredBy.getTime();
	}

	/**
	 * Completes this action
	 */
	public void completeAction()
	{
		this.complete = true;
	}
	
	/**
	 * Returns whether or not this action has been completed
	 */
	public boolean isComplete() 
	{
		return this.complete;
	}

	public String getOwningUser() {
		return owningUser;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Calendar getRequiredBy() {
		return requiredBy;
	}

	public void setRequiredBy(Calendar requiredBy) {
		this.requiredBy = requiredBy;
	}

	public void setOwningUser(String owningUser) {
		this.owningUser = owningUser;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}

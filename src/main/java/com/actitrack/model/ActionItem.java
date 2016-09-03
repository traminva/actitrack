package com.actitrack.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionItem {
	private int id;
	private String summary;
	private String description;
	private String dueDate;
	private int assignedTo;
	private String assignedToStr;
	private String priority;
	private String status;
	private int createdBy;
	private String createdByStr;
	private int createdOn;
	private int modifiedBy;
	private String modifiedByStr;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedToStr() {
        return assignedToStr;
    }
    public void setAssignedToStr(String assignedToStr) {
        this.assignedToStr = assignedToStr;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByStr() {
        return createdByStr;
    }
    public void setCreatedByStr(String createdByStr) {
        this.createdByStr = createdByStr;
    }

    public int getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }
}

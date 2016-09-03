package com.actitrack.dao;

import java.util.List;

import com.actitrack.model.ActionItem;
import com.actitrack.model.User;

public interface ActionItemDao {
	List<ActionItem> getAssignedItems(User user);
	List<ActionItem> getSubmittedItems(User user);
	List<ActionItem> getUserTimelineActionItems(User user);
	List<ActionItem> getUserFullTimelineActionItems(User user);
	void insertActionItem(ActionItem a);
}

package com.actitrack.dao;

import java.util.List;

import com.actitrack.model.Message;
import com.actitrack.model.User;

public interface MessageDao {
	List<Message> getUserTimelineMessages(User user);
	
	List<Message> getUserFullTimelineMessages(User user);
	
	List<Message> getPublicTimelineMessages();
	
	void insertMessage(Message m);
}

package com.actitrack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actitrack.dao.MessageDao;
import com.actitrack.dao.ActionItemDao;
import com.actitrack.dao.UserDao;
import com.actitrack.model.LoginResult;
import com.actitrack.model.ActionItem;
import com.actitrack.model.Message;
import com.actitrack.model.User;
import com.actitrack.util.PasswordUtil;

@Service
public class ActiTrackService {
	
	@Autowired
	private UserDao userDao;
	
/*	@Autowired
	private MessageDao messageDao;
*/	
	@Autowired
	private ActionItemDao actionItemDao;

	public List<ActionItem> getAssignedItems(User user) {
		return actionItemDao.getAssignedItems(user);
	}

	public List<ActionItem> getSubmittedItems(User user) {
		return actionItemDao.getSubmittedItems(user);
	}

	public void addActionItem(ActionItem actionItem) {
		actionItemDao.insertActionItem(actionItem);
	}

/*	public List<Message> getUserFullTimelineMessages(User user) {
		return messageDao.getUserFullTimelineMessages(user);
	}
	
	public List<Message> getUserTimelineMessages(User user) {
		return messageDao.getUserTimelineMessages(user);
	}
	
	public List<Message> getPublicTimelineMessages() {
		return messageDao.getPublicTimelineMessages();
	}
*/	
	public User getUserbyUsername(String username) {
		return userDao.getUserbyUsername(username);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}
/*	
	public void followUser(User follower, User followee) {
		userDao.insertFollower(follower, followee);
	}
	
	public void unfollowUser(User follower, User followee) {
		userDao.deleteFollower(follower, followee);
	}
	
	public boolean isUserFollower(User follower, User followee) {
		return userDao.isUserFollower(follower, followee);
	}
	
	public void addMessage(Message message) {
		messageDao.insertMessage(message);
	}
*/	
	public LoginResult checkUser(User user) {
		LoginResult result = new LoginResult();
		User userFound = userDao.getUserbyUsername(user.getUsername());
		if(userFound == null) {
			result.setError("Invalid username");
		} else if(!PasswordUtil.verifyPassword(user.getPassword(), userFound.getPassword())) {
			result.setError("Invalid password");
		} else {
			result.setUser(userFound);
		}
		
		return result;
	}
	
	public void registerUser(User user) {
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		userDao.registerUser(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

/*	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
*/
	public void setActionItemDao(ActionItemDao actionItemDao) {
		this.actionItemDao = actionItemDao;
	}
}

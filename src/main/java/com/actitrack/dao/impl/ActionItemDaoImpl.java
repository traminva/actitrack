package com.actitrack.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.actitrack.dao.ActionItemDao;
import com.actitrack.model.ActionItem;
import com.actitrack.model.User;

@Repository
public class ActionItemDaoImpl implements ActionItemDao {
	
	//private static final String GRAVATAR_DEFAULT_IMAGE_TYPE = "monsterid";
	//private static final int GRAVATAR_SIZE = 48;
	private NamedParameterJdbcTemplate template;

	@Autowired
	public ActionItemDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public List<ActionItem> getUserTimelineActionItems(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select message.*, user.* from message, user where " +
				"user.user_id = message.author_id and user.user_id = :id " +
				"order by message.pub_date desc";
		List<ActionItem> result = template.query(sql, params, actionItemMapper);
		
		return result;
	}

	@Override
	public List<ActionItem> getAssignedItems(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select ai.*, u.username as assignedToStr, cb.username as createdByStr from actionitems as ai left join user as u on u.user_id=ai.assignedTo left join user as cb on cb.user_id=ai.createdBy where ai.assignedTo = :id order by ai.dueDate desc";
		List<ActionItem> result = template.query(sql, params, actionItemMapper);
		return result;
	}

	@Override
	public List<ActionItem> getSubmittedItems(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
		String sql = "select ai.*, u.username as assignedToStr, cb.username as createdByStr from actionitems as ai left join user as u on u.user_id=ai.assignedTo left join user as cb on cb.user_id=ai.createdBy where ai.createdBy = :id order by ai.dueDate desc";
		List<ActionItem> result = template.query(sql, params, actionItemMapper);
		return result;
	}

	@Override
	public List<ActionItem> getUserFullTimelineActionItems(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select message.*, user.* from message, user " +
				"where message.author_id = user.user_id and ( " +
				"user.user_id = :id or " +
				"user.user_id in (select followee_id from follower " +
                                    "where follower_id = :id))" +
                "order by message.pub_date desc";
		List<ActionItem> result = template.query(sql, params, actionItemMapper);
		
		return result;
	}
/*
	@Override
	public List<ActionItem> getPublicTimelineActionItems() {
		Map<String, Object> params = new HashMap<String, Object>();
        
		String sql = "select message.*, user.* from message, user " +
				"where message.author_id = user.user_id " +
				"order by message.pub_date desc";
		List<ActionItem> result = template.query(sql, params, actionItemMapper);
		
		return result;
	}
*/
	@Override
	public void insertActionItem(ActionItem m) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("summary", m.getSummary());
        params.put("description", m.getDescription());
        params.put("dueDate", m.getDueDate());
        params.put("assignedTo", m.getAssignedTo());
        params.put("priority", m.getPriority());
        params.put("status", m.getStatus());
        params.put("createdBy", m.getCreatedBy());
        params.put("createdOn", m.getCreatedOn());
        String sql = "insert into actionitems (summary, description, dueDate, assignedTo, priority, status, createdBy, createdOn) values (:summary, :description, :dueDate, :assignedTo, :priority, :status, :createdBy, :createdOn)";
        //String sql = "insert into actionitems (summary, description) values (:summary, :description)";
		template.update(sql, params);
	}
	
	private RowMapper<ActionItem> actionItemMapper = (rs, rowNum) -> {
		ActionItem m = new ActionItem();
		
		m.setId(rs.getInt("id"));
		m.setSummary(rs.getString("summary"));
		m.setDescription(rs.getString("description"));
		m.setAssignedTo(rs.getInt("assignedTo"));
		m.setAssignedToStr(rs.getString("assignedToStr"));
		m.setPriority(rs.getString("priority"));
		m.setStatus(rs.getString("status"));
		m.setDueDate(rs.getString("dueDate"));
		m.setCreatedBy(rs.getInt("createdBy"));
		m.setCreatedByStr(rs.getString("createdByStr"));
		
		return m;
	};

}

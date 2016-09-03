package com.actitrack.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.actitrack.model.LoginResult;
import com.actitrack.model.Message;
import com.actitrack.model.ActionItem;
import com.actitrack.model.User;
import com.actitrack.service.impl.ActiTrackService;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.StringUtils;

public class WebConfig {
	
	private static final String USER_SESSION_ID = "user";
	private ActiTrackService service;
	 

	public WebConfig(ActiTrackService service) {
		this.service = service;
		staticFileLocation("/public");
		setupRoutes();
	}
	
	private void setupRoutes() {
		/*
		 * Shows a users timeline or if no user is logged in,
		 *  it will redirect to the public timeline.
		 *  This timeline shows the user's messages as well
		 *  as all the messages of followed users.
		 */
	/*	get("/timeline", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Timeline");
			map.put("user", user);
			List<Message> messages = service.getUserFullTimelineMessages(user);
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
        }, new FreeMarkerEngine());
		before("/", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if(user == null) {
				res.redirect("/login");
				halt();
			}
		});
*/
        /* Create an action item */
		get("/create", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Create Action item");
			map.put("user", user);
			List<User> users = service.getUsers();
			map.put("users", users);
			return new ModelAndView(map, "create.ftl");
        }, new FreeMarkerEngine());

		before("/create", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if(user == null) {
				res.redirect("/login");
				halt();
			}
		});

        post("/create", (req, res) -> {
            User user = getAuthenticatedUser(req);
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
            ActionItem a = new ActionItem();
            a.setCreatedBy(user.getId());
            a.setCreatedOn(1);
            //a.setAssignedTo(user.getId());
            //m.setPubDate(new Date());
            BeanUtils.populate(a, params);
            service.addActionItem(a);
            res.redirect("/");
            return null;
        });

        /* Lists Action Items assigned to me */
		get("/", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Action Items Assigned to me");
			map.put("user", user);
			List<ActionItem> items = service.getAssignedItems(user);
			map.put("items", items);
			return new ModelAndView(map, "assigned.ftl");
        }, new FreeMarkerEngine());
		before("/", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if(user == null) {
				res.redirect("/login");
				halt();
			}
		});

     /* Lists Action Items submitted by me */
		get("/submitted", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Submitted Action Items");
			map.put("user", user);
			List<ActionItem> items = service.getSubmittedItems(user);
			map.put("items", items);
			return new ModelAndView(map, "assigned.ftl");
        }, new FreeMarkerEngine());
		before("/submitted", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if(user == null) {
				res.redirect("/login");
				halt();
			}
		});

		
		/*
		 * Displays the latest messages of all users.
		 */
/*		get("/public", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Public Timeline");
			map.put("user", user);
			List<Message> messages = service.getPublicTimelineMessages();
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
        }, new FreeMarkerEngine());
		
*/		
		/*
		 * Displays a user's tweets.
		 */
/*		get("/t/:username", (req, res) -> {
			String username = req.params(":username");
			User profileUser = service.getUserbyUsername(username);
			
			User authUser = getAuthenticatedUser(req);
			boolean followed = false;
			if(authUser != null) {
				followed = service.isUserFollower(authUser, profileUser);
			}
			List<Message> messages = service.getUserTimelineMessages(profileUser);
			
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", username + "'s Timeline");
			map.put("user", authUser);
			map.put("profileUser", profileUser);
			map.put("followed", followed);
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user exists
		 */
/*		before("/t/:username", (req, res) -> {
			String username = req.params(":username");
			User profileUser = service.getUserbyUsername(username);
			if(profileUser == null) {
				halt(404, "User not Found");
			}
		});
*/		
		
		/*
		 * Adds the current user as follower of the given user.
		 */
/*		get("/t/:username/follow", (req, res) -> {
			String username = req.params(":username");
			User profileUser = service.getUserbyUsername(username);
			User authUser = getAuthenticatedUser(req);
			
			service.followUser(authUser, profileUser);
			res.redirect("/t/" + username);
			return null;
        });
		/*
		 * Checks if the user is authenticated and the user to follow exists
		 */
/*		before("/t/:username/follow", (req, res) -> {
			String username = req.params(":username");
			User authUser = getAuthenticatedUser(req);
			User profileUser = service.getUserbyUsername(username);
			if(authUser == null) {
				res.redirect("/login");
				halt();
			} else if(profileUser == null) {
				halt(404, "User not Found");
			}
		});
*/		
		
		/*
		 * Removes the current user as follower of the given user.
		 */
/*		get("/t/:username/unfollow", (req, res) -> {
			String username = req.params(":username");
			User profileUser = service.getUserbyUsername(username);
			User authUser = getAuthenticatedUser(req);
			
			service.unfollowUser(authUser, profileUser);
			res.redirect("/t/" + username);
			return null;
        });
		/*
		 * Checks if the user is authenticated and the user to unfollow exists
		 */
/*		before("/t/:username/unfollow", (req, res) -> {
			String username = req.params(":username");
			User authUser = getAuthenticatedUser(req);
			User profileUser = service.getUserbyUsername(username);
			if(authUser == null) {
				res.redirect("/login");
				halt();
			} else if(profileUser == null) {
				halt(404, "User not Found");
			}
		});
		
		
		/*
		 * Presents the login form or redirect the user to
		 * her timeline if it's already logged in
		 */
		get("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			if(req.queryParams("r") != null) {
				map.put("message", "You were successfully registered and can login now");
			}
			return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Logs the user in.
		 */
		post("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			LoginResult result = service.checkUser(user);
			if(result.getUser() != null) {
				addAuthenticatedUser(req, result.getUser());
				res.redirect("/");
				halt();
			} else {
				map.put("error", result.getError());
			}
			map.put("username", user.getUsername());
			return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/login", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if(authUser != null) {
				res.redirect("/");
				halt();
			}
		});
		
		
		/*
		 * Presents the register form or redirect the user to
		 * her timeline if it's already logged in
		 */
		get("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Registers the user.
		 */
		post("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			String error = user.validate();
			if(StringUtils.isEmpty(error)) {
				User existingUser = service.getUserbyUsername(user.getUsername());
				if(existingUser == null) {
					service.registerUser(user);
					res.redirect("/login?r=1");
					halt();
				} else {
					error = "The username is already taken";
				}
			}
			map.put("error", error);
			map.put("username", user.getUsername());
			map.put("email", user.getEmail());
			return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/register", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if(authUser != null) {
				res.redirect("/");
				halt();
			}
		});
		
		
		/*
		 * Registers a new message for the user.
		 */
/*		post("/message", (req, res) -> {
			User user = getAuthenticatedUser(req);
			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
			Message m = new Message();
			m.setUserId(user.getId());
			m.setPubDate(new Date());
			BeanUtils.populate(m, params);
			service.addMessage(m);
			res.redirect("/");
			return null;
        });
		/*
		 * Checks if the user is authenticated
		 */
/*		before("/message", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if(authUser == null) {
				res.redirect("/login");
				halt();
			}
		});
		
		
		/*
		 * Logs the user out and redirects to the public timeline
		 */
		get("/logout", (req, res) -> {
			removeAuthenticatedUser(req);
			res.redirect("/login");
			return null;
        });
	}

	private void addAuthenticatedUser(Request request, User u) {
		request.session().attribute(USER_SESSION_ID, u);
		
	}

	private void removeAuthenticatedUser(Request request) {
		request.session().removeAttribute(USER_SESSION_ID);
		
	}

	private User getAuthenticatedUser(Request request) {
		return request.session().attribute(USER_SESSION_ID);
	}
}

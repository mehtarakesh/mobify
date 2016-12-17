package com.amoraesdev.mobify.api.v1.valueobjects;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Value Object to represent a new Notification posted by a client application
 * @author Alessandro Moraes (alessandro at amoraesdev.com)
 */
@JsonTypeName("Notification")
public class NotificationPostVO implements Serializable {

	private static final long serialVersionUID = 6451739421647418433L;
	
	@NotNull
	private String[] usernames;
	@NotNull
	private String type;
	@NotNull
	private String message;
	
	public String[] getUsernames() {
		return usernames;
	}
	public void setUsernames(String... usernames) {
		this.usernames = usernames;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

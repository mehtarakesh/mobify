package com.amoraesdev.mobify.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amoraesdev.mobify.entities.Notification;
import com.amoraesdev.mobify.entities.UserApplicationSettings;
import com.amoraesdev.mobify.exceptions.WebSocketNotConnectedException;
import com.amoraesdev.mobify.repositories.UserApplicationSettingsRepository;
import com.amoraesdev.mobify.utils.RestErrorsControllerAdvice;
import com.amoraesdev.mobify.web.WebsocketHandler;

/**
 * This class is responsible for sending notifications to the mobile devices
 * @author Alessandro Moraes (alessandro at amoraesdev.com)
 */
@Component
public class SenderService {
	private final Logger log = LoggerFactory.getLogger(RestErrorsControllerAdvice.class);
	
	@Autowired
	private UserApplicationSettingsRepository userApplicationSettingsRepository;
	
	@Autowired
	private WebsocketHandler websocketHandler;
	
	/**
	 * Send a notification in background
	 * Async processing (fire and forget)
	 * @param notification
	 */
	public void sendNotification(Notification notification){
		//retrieve the user settings for this application (maybe it was put in silent mode)
		UserApplicationSettings settings = userApplicationSettingsRepository.findByUsernameAndApplicationId(notification.getUsername(), notification.getApplicationId());
		//if this user does not have settings for this app yet, create one
		if(settings == null){
			settings = new UserApplicationSettings(notification.getUsername(), notification.getApplicationId(), false);
			userApplicationSettingsRepository.save(settings);
		}
		//TODO implement the logic to send to the mobile devices connected
		log.debug("Sending notification".concat(notification.toString()));
		try {
			websocketHandler.sendNotification(notification);
		} catch (WebSocketNotConnectedException e) {
			//TODO send using push notification
			e.printStackTrace();
		}
		
	}
}

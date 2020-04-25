package com.wooster.lab.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

@Service
public class AmiService {
	private Logger log = Logger.getLogger(AmiService.class.getName());
	
	@Value("{ami.host}")
	private String amiHost;
	
	@Value("{ami.username}")
	private String amiUsername;
	
	@Value("{ami.token}")
	private String amiToken;
	
	
	private ManagerConnection manager = null;
	
	public AmiService () {
		ManagerConnectionFactory factory = new ManagerConnectionFactory(amiHost, amiUsername, amiToken);
		manager = factory.createManagerConnection();
	}
	
	
	
	private void login() {
		if ((manager.getState() != ManagerConnectionState.CONNECTED) &&
				(manager.getState() != ManagerConnectionState.CONNECTING)) {
			log.info("log into ami");
			try {
				manager.login();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthenticationFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void logoff() {
		if ((manager.getState() != ManagerConnectionState.DISCONNECTED) &&
				(manager.getState() != ManagerConnectionState.DISCONNECTING)) {
			manager.logoff();
		}
	}
	
	public ManagerResponse sendCall(OriginateAction action) {
		ManagerResponse response = null;
		try {
			login();
			response = manager.sendAction(action, 30000);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
 
}

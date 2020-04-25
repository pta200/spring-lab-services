package com.wooster.lab.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.asteriskjava.manager.*;

import java.util.logging.Logger;
import javax.validation.Valid;
import java.util.logging.Level;

import com.wooster.lab.bean.CallBackAction;
import com.wooster.lab.service.AmiService;

import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

/**
 * Controller to perform call back via asterisk manager interface
 * @author pta200
 *
 */
@RestController
@RequestMapping("/asterisk")
public class AsteriskController {
	private Logger log = Logger.getLogger(AsteriskController.class.getName());
	@Autowired
	AmiService amiService; 
	
	@Value("{sip.sippeer}")
	private String sipPeer;
	
	@Value("{diaplan.context")
	private String dialplanContext;

	@PostMapping("callback")
	public ResponseEntity<?> callback(@Valid @RequestBody CallBackAction action) {
		
		if ((action.getFromNumber().matches("[0-9]+")) && (action.getToNumber().matches("[0-9]+"))) {
		
			OriginateAction originate = new OriginateAction();
			originate.setChannel("SIP/" + sipPeer + "/" + action.getFromNumber());
			originate.setContext(dialplanContext);
			originate.setExten(action.getToNumber());
			originate.setPriority(new Integer(1));
			originate.setTimeout(new Long(30000));
			
			ManagerResponse response = amiService.sendCall(originate);
			log.info(response.getMessage() + response.getEvents());
			
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("invalid number format",new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE); 
		}
	}

}

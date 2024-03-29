package com.wooster.lab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wooster.lab.bean.Person;

import java.util.Date;

/**
 * Simple Spring controller using echo response.
 * @author pta200
 *
 */
@RestController
@RequestMapping("/echo")
public class EchoController {
	
	/**
	 * Echo name parameter
	 * @param name user's name
	 * @return HTML marked up respnse
	 */
	@RequestMapping(value = "/echoName/{name}")
    public String echoName(@PathVariable(name = "name") String name) {
        return "hello  <strong style=\"color: red;\">" + name + " </strong> Responsed on : " 
        		+ new Date();
    }
 
	/**
	 * Create a person object from name parameter
	 * @param name user's name
	 * @return json response
	 */
    @RequestMapping(value = "/getDetails/{name}")
    public Person getNameDetails(@PathVariable(name = "name") String name) {
        return new Person(name, "Pune", "MCA");
    }

}

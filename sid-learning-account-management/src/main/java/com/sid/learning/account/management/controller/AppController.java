package com.sid.learning.account.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AppController {
	
	@Autowired
	private Environment env;

	@GetMapping("/status/check")
	public String statusCheck() {
		return "account ms working... on port " +env.getProperty("local.server.port");
	}
}

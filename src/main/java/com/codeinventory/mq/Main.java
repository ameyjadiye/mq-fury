package com.codeinventory.mq;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Main {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JmsMessageSender jmsMessageSender;

	public void start() throws InterruptedException {
		
		// send to default destination
		for (int i = 0; i < 1000; i++) {
			
			jmsMessageSender.send("JMS msg |" + i );
		}
	}
	
	
}
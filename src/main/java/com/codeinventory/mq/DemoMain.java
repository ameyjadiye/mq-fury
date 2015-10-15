package com.codeinventory.mq;

import java.sql.Types;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DemoMain {
	
	@Autowired
	private static JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws InterruptedException {
		// init spring context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

		// get bean from context
		JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx.getBean("jmsMessageSender");
		JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");

		// send to default destination
		for (int i = 0; i < 100; i++) {
			
			//jmsMessageSender.send("hello JMS " + i + " " + new Date());
		}
		Object[] params = new Object[] {RandomUtils.getRandomString()};
		int[] types = new int[] { Types.VARCHAR};
		
		jdbcTemplate.update("insert into tran(uuid) values(?)",params,types);
		
		// send to a code specified destination
		// Queue queue = new ActiveMQQueue("AnotherDest");
		// jmsMessageSender.send(queue, "hello Another Message");

		Thread.sleep(6000);

		// close spring application context
		((ClassPathXmlApplicationContext) ctx).close();
	}

}
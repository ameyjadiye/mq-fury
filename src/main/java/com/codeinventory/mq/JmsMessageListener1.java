package com.codeinventory.mq;

import java.sql.Types;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
/**
 * Listener Implement Spring SessionAwareMessageListener Interface
 *
 */
public class JmsMessageListener1 implements
		SessionAwareMessageListener<TextMessage> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private JmsMessageSender jmsMessageSender;
	
	@Autowired
	Queue realDestination;

	@Override
	public void onMessage(TextMessage message, Session session)
			throws JMSException {
		// This is the received message
		// System.out.println("Receive: "+message.getText());

		String randStr = insertInDb();

		jmsMessageSender.send(realDestination, message.getText() + "|" + randStr + "|"+ System.currentTimeMillis());
	}

	private String insertInDb() {
		String randStr = RandomUtils.getRandomString();
		Object[] params = new Object[] { randStr };
		int[] types = new int[] { Types.VARCHAR };

		jdbcTemplate.update("insert into tran(uuid) values(?)", params, types);
		return randStr;
	}

}
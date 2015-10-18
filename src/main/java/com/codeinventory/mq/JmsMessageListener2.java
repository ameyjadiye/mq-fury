package com.codeinventory.mq;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
/**
 * Listener Implement Spring SessionAwareMessageListener Interface
 *
 */
public class JmsMessageListener2 implements SessionAwareMessageListener<TextMessage> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JmsMessageSender jmsMessageSender;
	
	private static final Logger logger = Logger.getLogger(JmsMessageListener2.class);
 
	String sql = "SELECT uuid FROM tran WHERE uuid = ?";
	
  @Override
  public void onMessage(TextMessage message, Session session) throws JMSException {
    // This is the received message
	  
	String msg = message.getText();
	String [] arr = msg.split("\\|");
	
	//System.out.println(arr[0]+arr[1]+arr[2]);
	
	//String uuid=null;
	String uuid = (String)jdbcTemplate.queryForObject(sql, new Object[] { arr[2] }, String.class);
	
		
	logger.info(message.getText()+"|"+uuid);
     
    // Let's prepare a reply message - a "ACK" String
    //ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
    //textMessage.setText("ACK");
     
    // Message send back to the replyTo address of the income message.
    // Like replying an email somehow. 
    //MessageProducer producer = session.createProducer(message.getJMSReplyTo());
    //producer.send(textMessage);
  }


}
package com.codeinventory.mq;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
 
public class DemoMain {
 
  public static void main(String[] args) throws InterruptedException {
    // init spring context
    ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
         
    // get bean from context
    JmsMessageSender jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");
         
    // send to default destination 
    for (int i=0 ;i<1000;i++){
    jmsMessageSender.send("hello JMS "+i);
    }
    // send to a code specified destination
    //Queue queue = new ActiveMQQueue("AnotherDest");
    //jmsMessageSender.send(queue, "hello Another Message");
   
    Thread.sleep(6000);
    
    // close spring application context
    ((ClassPathXmlApplicationContext)ctx).close();
  }
 
}
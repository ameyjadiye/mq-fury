package com.codeinventory.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	public static void main(String[] args) {
		// init spring context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

		// get bean from context
		Main main = (Main) ctx.getBean("main");
		try {
			main.start();
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// close spring application context
		((ClassPathXmlApplicationContext) ctx).close();
	}

}

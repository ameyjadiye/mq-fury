package com.codeinventory.mq;

import java.util.UUID;

public class RandomUtils {

	public static void main(String[] args) {
		for (int i=0; i < 10; i++) {
			System.out.println("uuid = " + getRandomString());
		}
	}

	public static String getRandomString() {

		return UUID.randomUUID().toString().replace("-", "");

	}

}

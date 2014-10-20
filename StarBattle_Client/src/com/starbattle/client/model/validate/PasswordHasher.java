package com.starbattle.client.model.validate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

	public static String hashPassword(String password) {
		String hash;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(), 0, password.length());
			hash = new BigInteger(1, digest.digest()).toString(16);

			return hash;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("No MD5 hash...FATAL!");
			System.exit(1);
			return null;
		}
	}

	
	
}

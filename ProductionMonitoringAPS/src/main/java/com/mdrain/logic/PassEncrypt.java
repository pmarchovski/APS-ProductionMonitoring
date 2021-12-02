package com.mdrain.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassEncrypt {

	
	public static String encryptPassword(String pass) {
		
		String password = pass;
		String encryptPass = null;
		
		try {
			MessageDigest massegeDigest = MessageDigest.getInstance("MD5");
			
			massegeDigest.update(password.getBytes());
			byte[] bytes = massegeDigest.digest();
			
			StringBuilder stringBuilder = new StringBuilder();
			
			for (int i = 0; i < bytes.length; i++) {
				
				stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			
			encryptPass = stringBuilder.toString();
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return encryptPass;
		
	}
	
}

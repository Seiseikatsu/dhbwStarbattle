package com.starbattle.accounts.mail;

public class GeneratePassword {
	
	public static String generatePsw(){
		StringBuffer password = new StringBuffer();
		password.append((char) (Math.random()*25 + 65));
		
		// Generate new password
		for(int i = 0; i < 9; i++){
			// random char between 33 and 90 (ASCII-Code between ! and Z)
			char random = (char) (Math.random()*89 + 33);
			// append char to password
			password.append(random);
		}
		
		// return password converted into a String
		return password.toString();
	}

}

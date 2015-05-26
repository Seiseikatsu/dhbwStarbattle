package com.starbattle.client.views.register.validate;

public class AsciiChecker {
	
	private AsciiChecker(){
	}
	
	public  static boolean checkAscii32To47(char c){
		return c >= 32 && c <= 47;
	}
	
	public  static boolean checkAscii58To64(char c){
		return c >= 58 && c <= 64;
	}
	
	public  static boolean checkAscii91To96(char c){
		return c >= 91 && c <= 96;
	}
	
	public  static boolean checkAscii123To126(char c){
		return c >= 123 && c <= 126;
	}

}

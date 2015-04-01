package com.starbattle.server.console;

public class SyntaxString {

	private String code;

	public SyntaxString(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public boolean startsWith(String text)
	{
		return code.startsWith(text);
	}

	// skips to part
	public void skip(String sign) throws SyntaxError {
		int index = code.indexOf(sign);
		if (index == -1) {
			throw new SyntaxError();
		}
		code = code.substring(index + sign.length(), code.length());
	}

	public boolean hasMark(String mark) {
		int index = code.indexOf(mark);
		return index != -1;
	}

	public String cutAttribute(String to) throws SyntaxError {
		int index = code.indexOf(to);
		if (index == -1) {
			throw new SyntaxError();
		}
		String cut = code.substring(0, index);
		code = code.substring(index, code.length());
		return cut.trim();
	}

	public String cutAttribute() throws SyntaxError {
		int index = code.length();
		if (index == -1) {
			throw new SyntaxError();
		}
		String cut = code.substring(0, index);
		code = code.substring(index, code.length());
		return cut.trim();
	}

	@Override
	public String toString() {
		return code;
	}
}

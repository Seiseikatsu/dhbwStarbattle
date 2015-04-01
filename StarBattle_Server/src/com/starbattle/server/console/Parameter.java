package com.starbattle.server.console;

public class Parameter {

	private String textForm;
	private double doubleValue;
	private int intValue;

	public Parameter(String text) {
		this.textForm = text;
		if (isNumeric(textForm)) {
			doubleValue = Double.parseDouble(textForm);
			// is int?
			if (doubleValue == Math.floor(doubleValue)) {
				intValue = (int) doubleValue;
			}
		}
	}

	private static boolean isNumeric(String str) {
		// match a number with optional '-' and decimal.
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public String getStringValue() {
		return textForm;
	}
}

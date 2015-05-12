package com.starbattle.client.views.play;

public class ModeSelector {

	private String mode,name;
	
	public ModeSelector(String modeName, String mode) {
		this.mode=mode;
		this.name=modeName;
	}

	public String getMode() {
		return mode;
	}

	public String getName() {
		return name;
	}
	
}

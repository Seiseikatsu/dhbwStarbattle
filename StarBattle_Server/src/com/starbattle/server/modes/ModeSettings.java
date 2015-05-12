package com.starbattle.server.modes;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModeSettings {

	private List<ModeSettingsEntry> modes;
	
	
	public List<ModeSettingsEntry> getModes() {
		return modes;
	}
	
	public void setModes(List<ModeSettingsEntry> modes) {
		this.modes = modes;
	}
}

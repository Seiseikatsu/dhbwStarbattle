package com.starbattle.mapeditor.settings;

import javax.xml.bind.annotation.XmlAttribute;


public class AutotileField {

	
	private int xpos;
	private int ypos;
	
	@XmlAttribute
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	
	@XmlAttribute
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	public int getXpos() {
		return xpos;
	}
	
	public int getYpos() {
		return ypos;
	}
}

package com.starbattle.mapeditor.settings;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class MapresAutotileProperties {

	private List<AutotileField> autotileField;
	private String name;
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@XmlElement
	public void setAutotileField(List<AutotileField> autotileField) {
		this.autotileField = autotileField;
	}
	
	public List<AutotileField> getAutotileField() {
		return autotileField;
	}
}

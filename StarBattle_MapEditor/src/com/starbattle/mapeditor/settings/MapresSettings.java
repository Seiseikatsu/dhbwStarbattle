package com.starbattle.mapeditor.settings;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MapresSettings {


	private List<MapresAutotileProperties> mapresAutotileProperties;

	public void setMapresAutotileProperties(List<MapresAutotileProperties> mapresAutotileProperties) {
		this.mapresAutotileProperties = mapresAutotileProperties;
	}
	
	public List<MapresAutotileProperties> getMapresAutotileProperties() {
		return mapresAutotileProperties;
	}
	
}

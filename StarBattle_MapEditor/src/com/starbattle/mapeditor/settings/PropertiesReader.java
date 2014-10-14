package com.starbattle.mapeditor.settings;

import java.util.HashMap;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

import com.starbattle.mapeditor.main.StarbattleMapEditor;
import com.starbattle.mapeditor.resource.AutotileMarks;

public class PropertiesReader {

	public static HashMap<String,AutotileMarks> read() throws JAXBException {
		
		MapresSettings properties=JAXB.unmarshal(StarbattleMapEditor.mapresSettings, MapresSettings.class);
		
		HashMap<String,AutotileMarks> autotiles=new  HashMap<String,AutotileMarks>();
		for(MapresAutotileProperties autotilesProperties: properties.getMapresAutotileProperties())
		{
			AutotileMarks marks=createAutotileMarks(autotilesProperties);
			String resName=autotilesProperties.getName();
			autotiles.put(resName, marks);
		}
		return autotiles;
	}
	
	private static AutotileMarks createAutotileMarks(MapresAutotileProperties properties)
	{
		AutotileMarks marks=new AutotileMarks();	
		for(AutotileField field: properties.getAutotileField())
		{
			marks.addAutotile(field.getXpos(), field.getYpos());
		}	
		return marks;
	}
	
	
	

}

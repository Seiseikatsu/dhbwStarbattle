package com.starbattle.client.resource;

import java.awt.Font;
import java.awt.Image;

public class GUIDesign {

	public static Font buttonFont,labelFont;
	public static Image button,buttonSelected;
	
	public static void load()
	{
		buttonFont=ResourceLoader.loadFont("OratorStd.otf").deriveFont(20f);
		labelFont=ResourceLoader.loadFont("OratorStd.otf").deriveFont(20f);
		
		button=ResourceLoader.loadImage("button.png");
		buttonSelected=ResourceLoader.loadImage("buttonSelected.png");
		
	}
	
}

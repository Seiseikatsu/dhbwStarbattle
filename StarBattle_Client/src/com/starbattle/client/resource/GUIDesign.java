package com.starbattle.client.resource;

import java.awt.Font;
import java.awt.Image;

public class GUIDesign {

	public static Font buttonFont,labelFont;
	public static Image[] button,buttonSelected;
	
	public static void load()
	{
		buttonFont=ResourceLoader.loadFont("OratorStd.otf").deriveFont(20f);
		labelFont=ResourceLoader.loadFont("OratorStd.otf").deriveFont(20f);
		
		int buttons=2;
		button=new Image[buttons];
		buttonSelected=new Image[buttons];
		
		for(int i=0; i<buttons; i++)
		{
			button[i]=ResourceLoader.loadImage("button"+(i+1)+".png");
			buttonSelected[i]=ResourceLoader.loadImage("button"+(i+1)+"Selected.png");			
		}
		
	}
	
}

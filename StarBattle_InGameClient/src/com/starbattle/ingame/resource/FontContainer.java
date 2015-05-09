package com.starbattle.ingame.resource;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

import com.starbattle.ingame.resource.player.ResourceException;

public class FontContainer {

	
	private static boolean antiAliasFonts=false;
	public final static String PATH = ResourceContainer.PATH + "fonts/";
	
	private TrueTypeFont mediumText,tinyText,bigText; 
	private org.newdawn.slick.Font defaultFont;
	
	
	public FontContainer() {
		
	}
	
	public void loadFonts() throws ResourceException
	{
		mediumText=loadFont("TAHOMA.TTF", Font.BOLD, 20);
		bigText=loadFont("TAHOMA.TTF", Font.BOLD, 35);
		tinyText=loadFont("TAHOMA.TTF", Font.PLAIN, 14);
	}
	
	private TrueTypeFont loadFont(String name, int style, int size)
	{
		Font font=new Font(name,style,size);
		return new TrueTypeFont(font, antiAliasFonts);
	}
	
	public TrueTypeFont getTinyText() {
		return tinyText;
	}
	
	public TrueTypeFont getMediumText() {
		return mediumText;
	}
	
	public TrueTypeFont getBigText() {
		return bigText;
	}
	
	public org.newdawn.slick.Font getDefaultFont() {
		return defaultFont;
	}
	
	public void setSystemFont(org.newdawn.slick.Font font) {
		this.defaultFont=font;
	}
}

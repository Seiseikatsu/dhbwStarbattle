package com.starbattle.ingame.render;

import org.lwjgl.opengl.Display;

import com.starbattle.ingame.resource.ResourceContainer;

public abstract class RenderResource {

	protected ResourceContainer resourceContainer;
	protected static int screenWidth,screenHeight;
	
	public RenderResource(ResourceContainer resourceContainer) {
		this.resourceContainer=resourceContainer;
		
	}

	public static void readScreenSize()
	{
		screenWidth=Display.getWidth();
		screenHeight=Display.getHeight();
	}
}

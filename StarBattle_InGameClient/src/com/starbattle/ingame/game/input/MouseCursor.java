package com.starbattle.ingame.game.input;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

public class MouseCursor  {

	private float mouseX, mouseY;
	private Input input;
	
	
	public MouseCursor(Input input) {
		this.input=input;
	}

	public float getWeaponAnlge() {
		float cx = Display.getWidth() / 2;
		float cy = Display.getHeight() / 2;
		return (float) Math.atan2(mouseY - cy, mouseX - cx);
	}


	public void poll()
	{
		mouseX=input.getMouseX();
		mouseY=input.getMouseY();
	}
}

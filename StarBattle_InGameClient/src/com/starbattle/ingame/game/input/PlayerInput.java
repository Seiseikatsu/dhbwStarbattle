package com.starbattle.ingame.game.input;

import org.newdawn.slick.Input;


public class PlayerInput {

	private Input input;
	private ActionSet actionSet;
	private MouseCursor mouseCursor;
	
	public PlayerInput(Input input2) {
		this.input=input2;
	}
	
	public void init()
	{
		actionSet=new ActionSet();
		input.addKeyListener(new KeyboardInput(actionSet));
		mouseCursor=new MouseCursor(input);
	}
	
	public void refresh()
	{
		actionSet.reset();
	}
	
	public ActionSet getActionSet() {
		return actionSet;
	}
	
	public MouseCursor getMouseCursor() {
		return mouseCursor;
	}

	public void poll() {
		mouseCursor.poll();
	}
	
	
}

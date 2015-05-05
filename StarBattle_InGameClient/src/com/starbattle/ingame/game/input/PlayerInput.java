package com.starbattle.ingame.game.input;

import org.newdawn.slick.Input;


public class PlayerInput {

	private Input input;
	private ActionSet actionSet;
	
	public PlayerInput(Input input2) {
		this.input=input2;
	}
	
	public void init()
	{
		actionSet=new ActionSet();
		input.addKeyListener(new KeyboardInput(actionSet));
	}
	
	public void refresh()
	{
		actionSet.reset();
	}
	
	public ActionSet getActionSet() {
		return actionSet;
	}
	
	
}

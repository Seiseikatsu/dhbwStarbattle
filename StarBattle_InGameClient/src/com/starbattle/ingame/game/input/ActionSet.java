package com.starbattle.ingame.game.input;

public class ActionSet {

	private boolean move_right,move_left,move_up,move_down;
	private boolean use_jetpack;
	
	public ActionSet() {

	}
	
	public void reset()
	{
		move_down=false;
		move_left=false;
		move_up=false;
		move_right=false;
		use_jetpack=false;
	}
	
	public void moveLeft()
	{
		move_left=true;
	}
	
	public void moveRight()
	{
		move_right=true;
	}
	
	public void moveUp()
	{
		move_up=true;
	}
	
	public void moveDown()
	{
		move_down=true;
	}
	
	public void useJetPack()
	{
		use_jetpack=true;
	}

	public boolean isMove_right() {
		return move_right;
	}

	public boolean isMove_left() {
		return move_left;
	}

	public boolean isMove_up() {
		return move_up;
	}

	public boolean isMove_down() {
		return move_down;
	}

	public boolean isUse_jetpack() {
		return use_jetpack;
	}
	
	
	
	
	
	
}

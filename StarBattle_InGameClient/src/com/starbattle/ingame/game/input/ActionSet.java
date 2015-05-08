package com.starbattle.ingame.game.input;

public class ActionSet {

	private boolean move_right,move_left,move_up,move_down;
	private boolean use_jetpack;
	private boolean jump;
	
	public ActionSet() {

	}
	
	public void reset()
	{
		use_jetpack=false;
		jump=false;
	}
	
	public void jump(){
		this.jump = true;
	}
	
	public boolean isJump() {
		return jump;
	}
	
	public void setMove_right(boolean move_right) {
		this.move_right = move_right;
	}

	public void setMove_left(boolean move_left) {
		this.move_left = move_left;
	}

	public void setMove_up(boolean move_up) {
		this.move_up = move_up;
	}

	public void setMove_down(boolean move_down) {
		this.move_down = move_down;
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

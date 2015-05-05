package com.starbattle.ingame.game.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * WASD Steuerung
 * 
 * @author RoO
 *
 */
public class KeyboardInput implements KeyListener {

	private ActionSet actionSet;
	

	public KeyboardInput(ActionSet actionSet) {
		this.actionSet = actionSet;
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char keyChar) {

		switch (key) {
		case Input.KEY_W:
			actionSet.moveUp();
			break;
		case Input.KEY_A:
			actionSet.moveLeft();
			break;
		case Input.KEY_S:
			actionSet.moveDown();
			break;
		case Input.KEY_D:
			actionSet.moveRight();
			break;
		}
	}

	@Override
	public void keyReleased(int arg0, char arg1) {

	}

}

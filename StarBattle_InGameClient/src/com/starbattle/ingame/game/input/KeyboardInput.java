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

	private void movement(int key, boolean move) {
		switch (key) {
		case Input.KEY_W:
			actionSet.setMove_up(move);
			break;
		case Input.KEY_A:
			actionSet.setMove_left(move);
			break;
		case Input.KEY_S:
			actionSet.setMove_down(move);
			break;
		case Input.KEY_D:
			actionSet.setMove_right(move);
			break;
		}
	}

	@Override
	public void keyPressed(int key, char keyChar) {
		movement(key, true);
	}

	@Override
	public void keyReleased(int key, char keyChar) {
		movement(key, false);
		// movement(keyChar, true);

	}

}

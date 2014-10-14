package com.starbattle.mapeditor.gui.control;

import com.starbattle.mapeditor.resource.SpriteSheet;

public class TileSelection {

	private int x, y, w, h;
	private int maxW, maxH;
	private boolean isAutotile = false;

	public TileSelection() {

	}

	public void setBorders(int x, int y) {
		maxW = x;
		maxH = y;
	}

	public void checkForAutotiles(SpriteSheet sprites) {

		if (w == 1 && h == 1) {
			isAutotile = sprites.isAutotile(x, y);
			return;
		}
		isAutotile = false;
	}

	public void set(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		// prevent wrong selections (out of bounds)
		if (x < 0) {
			setSafePoint(0, y);
		} else if (x + w > maxW) {
			setSafePoint(maxW - 1, y);
		}
		if (y < 0) {
			setSafePoint(x, 0);
		} else if (y + h > maxH) {
			setSafePoint(x, maxH - 1);
		}
	}

	private void setSafePoint(int x, int y) {
		this.x = x;
		this.y = y;
		w = 1;
		h = 1;
	}

	public boolean isAutotile() {
		return isAutotile;
	}

	public boolean isSet() {
		if (w != 0 && h != 0) {
			return true;
		}
		return false;
	}

	public void reset() {
		w = 0;
		x = 0;
		y = 0;
		h = 0;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}

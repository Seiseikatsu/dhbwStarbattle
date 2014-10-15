package com.starbattle.mapeditor.resource;

import java.util.ArrayList;

public class AutotileMarks {

	private ArrayList<Mark> autotile = new ArrayList<Mark>();

	public AutotileMarks() {

	}

	public int[] getAutotileID(int xp, int yp) {

		if(xp!=0&&yp!=0)
		{
		for (Mark mark : autotile) {
			int x = mark.xpos;
			int y = mark.ypos - 1;
			int w = x + 3;
			int h = y + 5;
			if (xp >= x && xp < w && yp < h) {
				return new int[] { mark.xpos, mark.ypos };
			}
		}
		}
		return null;
	}

	public boolean isAutotile(int x, int y) {
		for (Mark mark : autotile) {
			if (mark.xpos == x && mark.ypos == y) {
				return true;
			}
		}
		return false;
	}

	public void addAutotile(int x, int y) {
		Mark mark = new Mark();
		mark.xpos = x;
		mark.ypos = y;
		autotile.add(mark);
	}

	private class Mark {
		public int xpos;
		public int ypos;
	}

}

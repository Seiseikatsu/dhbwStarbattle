package com.starbattle.mapeditor.gui.listener;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.starbattle.mapeditor.gui.control.TileSelection;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class MouseSelectionListener implements MouseListener, MouseMotionListener {

	private TileSelection selection;

	private ActionListener repaintSelection;
	private boolean isDragging = false;
	private int startX, startY;

	public MouseSelectionListener(TileSelection selection, ActionListener repaint) {

		this.repaintSelection = repaint;
		this.selection = selection;
	}

	private void selectClick(int x, int y) {
		int xp = x / SpriteSheet.TILE_SIZE;
		int yp = y / SpriteSheet.TILE_SIZE;

		selection.set(xp, yp, 1, 1);
		repaintSelection.actionPerformed(null);

	}

	private void selectDragRect(int x, int y) {
		int xd = x - startX;
		int yd = y - startY;
		if (xd == 0 && yd == 0) { // rect size = 1
			selection.set(x, y, 1, 1);
		} else {
			// calc rect w and h
			int rw;
			int rh;
			int rx;
			int ry;
			if (xd < 0) {
				rx = x;
				rw = (xd * -1) + 1;
				if (yd < 0) {
					ry = y;
					rh = (yd * -1) + 1;
				} else {
					ry = startY;
					rh = yd + 1;
				}
			} else {
				rx = startX;
				rw = xd + 1;
				if (yd < 0) {
					ry = y;
					rh = (yd * -1) + 1;
				} else {
					ry = startY;
					rh = yd + 1;
				}
			}
			selection.set(rx, ry, rw, rh);
		}
		repaintSelection.actionPerformed(null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX() / SpriteSheet.TILE_SIZE;
		int y = e.getY() / SpriteSheet.TILE_SIZE;

		if (isDragging == false) {
			// set start point
			startX = x;
			startY = y;
			isDragging = true;
		} else {
			// set drag rect
			selectDragRect(x, y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		isDragging = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		selectClick(x, y);
		isDragging = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

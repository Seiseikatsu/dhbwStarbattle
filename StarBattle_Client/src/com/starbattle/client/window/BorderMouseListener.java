package com.starbattle.client.window;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BorderMouseListener implements MouseListener,MouseMotionListener{

	private DesignWindowBorder border;
	private WindowBorderListener windowBorderListener;
	
	public  BorderMouseListener(DesignWindowBorder border, WindowBorderListener windowBorderListener) {
		this.border=border;
		this.windowBorderListener=windowBorderListener;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		Point mouse=new Point(e.getX(),e.getY());
		if(border.isMouseOnCloseButton(mouse))
		{
			windowBorderListener.closeWindow();
		}
		dragging=false;
		drag=false;
	}

	private boolean drag=false;
	private boolean dragging=false;
	private int oldx,oldy;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point mouse=new Point(e.getX(),e.getY());
		if(dragging)
		{
			if(drag)
			{
			int newx=e.getXOnScreen()-oldx;
			int newy=e.getYOnScreen()-oldy;
			windowBorderListener.setLocation(newx, newy);
			}
			else
			{
				oldx=mouse.x;
				oldy=mouse.y;
			}
			drag=!drag;
		}
		else
		{
			if(border.isMouseOnDragArea(mouse))
			{
				dragging=true;
				
			}
		}	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}

package com.starbattle.mapeditor.gui.listener;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.starbattle.mapeditor.gui.components.MapComponent;
import com.starbattle.mapeditor.gui.control.ToolSelection;

public class MapMouseListener implements MouseListener,MouseMotionListener{

	
	private TilePlacementListener listener;
	private ToolSelection toolSelection;
	private boolean pressed=false;
	private boolean dragging=false;
	private int startx,starty;
	private int dragx,dragy;
	
	public  MapMouseListener( TilePlacementListener listener, ToolSelection toolSelection) {
		
		this.toolSelection=toolSelection;
		this.listener=listener;
	}
	
	public Rectangle getDragRect()
	{
		if(dragging)
		{
		return new Rectangle(startx, starty, dragx-startx, dragy-starty);
		}
		return null;
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
		if(pressed==false)
		{
		int mx=e.getX()-MapComponent.MAP_BORDER;
		int my=e.getY()-MapComponent.MAP_BORDER;	
		if(toolSelection.isPaintTool())
		{
			listener.placeTile(mx, my);
		}
		else if(toolSelection.isEraseTool())
		{
			listener.removeTile(mx, my);
		}
		else if(toolSelection.isFillTool())
		{
			listener.fillTile(mx, my);
		}
		else if(toolSelection.isMoveBehindTool())
		{
			listener.moveBehind(mx, my);
		}
		pressed=true;
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pressed=false;	
		if(toolSelection.isSelectTool())
		{
			listener.moveSelectedTileReleased();
		}
		else if(toolSelection.isRectangleTool())
		{
			Rectangle rect=getDragRect();
			if(rect!=null)
			{
			listener.fillRectangle(rect.x,rect.y,rect.width,rect.height);
			}
		}
		dragging=false;
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX()-MapComponent.MAP_BORDER;
		int my=e.getY()-MapComponent.MAP_BORDER;	
		if(toolSelection.isPaintTool())
		{
		listener.placeTile(mx, my);
		}
		else if(toolSelection.isEraseTool())
		{
		listener.removeTile(mx, my);
		}
		else if(toolSelection.isSelectTool())
		{
			//move
			if(dragging)
			{
				int movex=mx-startx;
				int movey=my-starty;
				listener.moveSelectedTile(startx, starty, movex, movey);
			}
			else
			{
				startx=mx;
				starty=my;
			}
			dragging=!dragging;
		}
		else if(toolSelection.isRectangleTool())
		{
			if(dragging)
			{
				dragx=mx;
				dragy=my;
			}
			else
			{
				startx=mx;
				starty=my;
				dragging=true;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}

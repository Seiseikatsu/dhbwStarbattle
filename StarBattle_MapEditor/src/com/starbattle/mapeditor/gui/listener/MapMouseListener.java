package com.starbattle.mapeditor.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.starbattle.mapeditor.gui.components.MapComponent;
import com.starbattle.mapeditor.gui.control.ToolSelection;

public class MapMouseListener implements MouseListener,MouseMotionListener{

	
	private TilePlacementListener listener;
	private ToolSelection toolSelection;
	private boolean pressed=false;
	
	public  MapMouseListener( TilePlacementListener listener, ToolSelection toolSelection) {
		
		this.toolSelection=toolSelection;
		this.listener=listener;
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
		pressed=true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pressed=false;
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
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}

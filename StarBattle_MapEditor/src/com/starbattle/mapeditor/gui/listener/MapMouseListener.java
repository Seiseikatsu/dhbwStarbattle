package com.starbattle.mapeditor.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.starbattle.mapeditor.gui.components.MapComponent;

public class MapMouseListener implements MouseListener,MouseMotionListener{

	
	private TilePlacementListener listener;

	public  MapMouseListener( TilePlacementListener listener) {
		
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
		int mx=e.getX()-MapComponent.MAP_BORDER;
		int my=e.getY()-MapComponent.MAP_BORDER;	
		listener.placeTile(mx, my);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX()-MapComponent.MAP_BORDER;
		int my=e.getY()-MapComponent.MAP_BORDER;	
		listener.placeTile(mx, my);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}

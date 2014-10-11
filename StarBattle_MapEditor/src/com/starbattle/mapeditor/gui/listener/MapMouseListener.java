package com.starbattle.mapeditor.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.starbattle.mapeditor.gui.components.MapComponent;

public class MapMouseListener implements MouseListener{

	
	private int width, height;
	private TilePlacementListener listener;
	
	public  MapMouseListener(int mapw, int maph, TilePlacementListener listener) {
		width=mapw;
		height=maph;
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
		
	
		int xmax=MapComponent.MAP_BORDER+width;
		int ymax=MapComponent.MAP_BORDER+height;
		
		if(mx>=0&&my>=0&&mx<xmax&&my<ymax)
		{
			//in map
			listener.placeTile(mx, my);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}

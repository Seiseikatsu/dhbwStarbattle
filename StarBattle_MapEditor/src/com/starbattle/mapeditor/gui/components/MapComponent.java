package com.starbattle.mapeditor.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.starbattle.mapeditor.gui.listener.MapMouseListener;
import com.starbattle.mapeditor.gui.listener.TilePlacementListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class MapComponent extends JPanel{

	private MapLayerRender mapRender=new MapLayerRender();
	private Map map;
	public final static int MAP_BORDER=SpriteSheet.TILE_SIZE*3;
	private TilePlacementPreview preview;
	private int mx,my;
	private boolean showPreview=false;
	
	public MapComponent(Map map, TilePlacementListener listener, TilePlacementPreview preview)
	{
		this.map=map;
		this.addMouseMotionListener(new PreviewUpdater());
		this.addMouseListener(new PreviewUpdater());
		
		this.preview=preview;
		int w=map.getWidth()*SpriteSheet.TILE_SIZE;
		int h=map.getHeight()*SpriteSheet.TILE_SIZE;
		this.addMouseListener(new MapMouseListener(w, h,listener));
		w+=MAP_BORDER*2;
		h+=MAP_BORDER*2;
		mapRender.setMapSize(map.getSize());
		this.setPreferredSize(new Dimension(w,h));
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, this.getWidth(),this.getHeight());
		int x=MAP_BORDER;
		int y=MAP_BORDER;
		
		for(MapLayer layer: map.getLayers())
		{
			mapRender.renderLayer(g, layer, x, y);
		}	
		paintRaster(g, x, y);
		
		if(showPreview)
		{
		preview.paint(g, mx, my);
		}
		
		int w=map.getWidth()*SpriteSheet.TILE_SIZE;
		int h=map.getHeight()*SpriteSheet.TILE_SIZE;
		g.setColor(new Color(50,50,50));
		g.drawRect(x-1,y-1,w+1,h+1);
		
	}
	
	private void paintRaster(Graphics g, int sx, int sy)
	{
		g.setColor(new Color(150,150,150));
		for(int x=0; x<map.getWidth(); x++)
		{
			for(int y=0; y<map.getHeight(); y++)
			{
				int xp=sx+x*SpriteSheet.TILE_SIZE;
				int yp=sy+y*SpriteSheet.TILE_SIZE;
				
				g.drawRect(xp,yp,SpriteSheet.TILE_SIZE,SpriteSheet.TILE_SIZE);
			}
		}
	}
	
	private class PreviewUpdater implements MouseMotionListener, MouseListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			mx=e.getX();
			my=e.getY();
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			showPreview=true;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			showPreview=false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}

package com.starbattle.mapeditor.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class TilesetRender extends JPanel {

	private SpriteSheet sprites;
	private Color back1 = new Color(0, 1, 119);
	private Color back2 = new Color(0, 1, 66);
	
	public TilesetRender()
	{
		
	}
	
	public void open(MapLayer layer)
	{
	    sprites=MapTextureLoader.getSpriteSheets().get(layer.getResource());	
		int w=sprites.getWidth()*SpriteSheet.TILE_SIZE;
		int h=sprites.getHeight()*SpriteSheet.TILE_SIZE;
		this.setPreferredSize(new Dimension(w,h));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
	    if(sprites!=null)
	    {
	    	for(int x=0; x<sprites.getWidth(); x++)
	    	{
	    		for(int y=0; y<sprites.getHeight(); y++)
	    		{
	    			int xp=x*SpriteSheet.TILE_SIZE;
	    			int yp=y*SpriteSheet.TILE_SIZE;
	    			Image tile=sprites.getTileGraphic(x, y);
	    			
	    			//fill background
	    			if(x%2==0)
	    			{
	    				if(y%2==0)
	    				{
	    					g.setColor(back1);
	    				}
	    				else
	    				{
	    					g.setColor(back2);
	    				}
	    			}
	    			else
	    			{
	    				if(y%2==0)
	    				{
	    					g.setColor(back2);
	    				}
	    				else
	    				{
	    					g.setColor(back1);
	    				}
	    			}
	    			g.fillRect(xp,yp,SpriteSheet.TILE_SIZE,SpriteSheet.TILE_SIZE);
	    			g.drawImage(tile,xp,yp,null);
	    		}
	    	}
	    }
	}
	
}

package com.starbattle.mapeditor.gui.components;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import com.starbattle.mapeditor.resource.SpriteSheet;

public class TilePlacementPreview {

	private Image preview;
	private boolean isDecoration;

	public TilePlacementPreview() {

	}

	public void setPreview(Image preview, boolean decoration) {
		this.isDecoration=decoration;
		this.preview = preview;
	}

	public void paint(Graphics g,  int mx, int my) {
		if (preview != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			int x=0;
			int y=0;
			
			 x=mx;
			 y=my;
			if(!isDecoration)
			{
				x=(x/SpriteSheet.TILE_SIZE)*SpriteSheet.TILE_SIZE;
				y=(y/SpriteSheet.TILE_SIZE)*SpriteSheet.TILE_SIZE;			
			}
			
			g2d.drawImage(preview, x,y, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
		}
	}

}

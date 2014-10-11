package com.starbattle.mapeditor.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.starbattle.mapeditor.gui.control.TileSelection;
import com.starbattle.mapeditor.gui.listener.MouseSelectionListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class TilesetRender extends JPanel {

	private SpriteSheet sprites;
	private Color back1 = new Color(0, 1, 119);
	private Color back2 = new Color(0, 1, 66);
	private TileSelection selection;
	private TilePlacementPreview preview;

	public TilesetRender(TilePlacementPreview preview) {
		this.preview = preview;
	}

	public void open(MapLayer layer, TileSelection select) {
		selection = select;
		sprites = MapTextureLoader.getSpriteSheets().get(layer.getResource());
		int w = sprites.getWidth();
		int h = sprites.getHeight();
		select.setBorders(w, h);
		this.setPreferredSize(new Dimension(w * SpriteSheet.TILE_SIZE, h * SpriteSheet.TILE_SIZE));
		MouseSelectionListener mouseSelectionListener = new MouseSelectionListener(select, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preview.setPreview(getTilePreview());
				repaint();
			}
		});
		this.addMouseListener(mouseSelectionListener);
		this.addMouseMotionListener(mouseSelectionListener);
	}

	private Image getTilePreview() {
		// TODO Auto-generated method stub
		if (selection.isSet()) {
			int w = selection.getW();
			int h = selection.getH();
			BufferedImage bf = new BufferedImage(w * SpriteSheet.TILE_SIZE, h * SpriteSheet.TILE_SIZE,
					BufferedImage.TYPE_INT_ARGB);
			Graphics g = bf.getGraphics();
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					int xp = x * SpriteSheet.TILE_SIZE;
					int yp = y * SpriteSheet.TILE_SIZE;
					int tix = selection.getX() + x;
					int tiy = selection.getY() + y;
					g.drawImage(sprites.getTileGraphic(tix, tiy), xp, yp, null);
				}
			}
			return bf;
		}
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(50, 50, 50));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (sprites != null) {
			for (int x = 0; x < sprites.getWidth(); x++) {
				for (int y = 0; y < sprites.getHeight(); y++) {
					int xp = x * SpriteSheet.TILE_SIZE;
					int yp = y * SpriteSheet.TILE_SIZE;
					Image tile = sprites.getTileGraphic(x, y);

					// fill background
					g.setColor(getBackgroundColor(x, y));
					g.fillRect(xp, yp, SpriteSheet.TILE_SIZE, SpriteSheet.TILE_SIZE);
					g.drawImage(tile, xp, yp, null);
				}
			}
			paintSelection(g);
		}
	}

	private void paintSelection(Graphics g) {
		int x = selection.getX() * SpriteSheet.TILE_SIZE;
		int y = selection.getY() * SpriteSheet.TILE_SIZE;
		int w = selection.getW() * SpriteSheet.TILE_SIZE;
		int h = selection.getH() * SpriteSheet.TILE_SIZE;
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w - 1, h - 1);
		g.setColor(Color.WHITE);
		g.drawRect(x + 1, y + 1, w - 3, h - 3);
	}

	private Color getBackgroundColor(int x, int y) {
		if (x % 2 == 0) {
			if (y % 2 == 0) {
				return back1;
			} else {
				return back2;
			}
		} else {
			if (y % 2 == 0) {
				return back2;
			} else {
				return back1;
			}
		}
	}

}

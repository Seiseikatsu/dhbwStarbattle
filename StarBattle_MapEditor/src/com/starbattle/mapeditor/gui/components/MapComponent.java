package com.starbattle.mapeditor.gui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.starbattle.mapeditor.gui.control.ToolSelection;
import com.starbattle.mapeditor.gui.listener.MapMouseListener;
import com.starbattle.mapeditor.gui.listener.TilePlacementListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.resource.ResourceLoader;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class MapComponent extends JPanel {

	private MapLayerRender mapRender;
	private Map map;
	public final static int MAP_BORDER = SpriteSheet.TILE_SIZE * 3;
	private TilePlacementPreview preview;
	private int mx, my;
	private MapMouseListener mapMouseListener;
	private ToolSelection toolSelection;
	private boolean showPreview = false;
	private MapInfoBar layerInfoBar;

	private boolean showMapGrid = true;

	public MapComponent(TilePlacementListener listener, ToolSelection toolSelection, TilePlacementPreview preview,
			MapInfoBar layerInfoBar) {

		mapRender = new MapLayerRender();
		mapMouseListener = new MapMouseListener(listener, toolSelection);
		this.toolSelection = toolSelection;
		this.layerInfoBar = layerInfoBar;
		this.addMouseMotionListener(new PreviewUpdater());
		this.addMouseListener(new PreviewUpdater());
		this.addMouseListener(mapMouseListener);
		this.addMouseMotionListener(mapMouseListener);
		this.preview = preview;
	}

	public void setMap(Map map) {
		this.map = map;
		int w = map.getWidth() * SpriteSheet.TILE_SIZE;
		int h = map.getHeight() * SpriteSheet.TILE_SIZE;
		w += MAP_BORDER * 2;
		h += MAP_BORDER * 2;
		mapRender.setMapSize(map.getSize());
		this.setPreferredSize(new Dimension(w, h));
		this.revalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {

		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int x = MAP_BORDER;
		int y = MAP_BORDER;

		int tiles = 0;
		for (MapLayer layer : map.getLayers()) {
			if (layer.isVisible()) {
				tiles += mapRender.renderLayer(g, layer, x, y);
			}
		}
		layerInfoBar.setNumberOfTiles(tiles);
		if (showMapGrid) {
			paintGrid(g, x, y);
		}

		if (showPreview) {
			// standard
			if (toolSelection.isPaintTool()) {
				preview.paint(g, mx, my);
			}
			else if(toolSelection.isRectangleTool())
			{
				Rectangle rect=mapMouseListener.getDragRect();
				if(rect!=null)
				{
					g.setColor(new Color(150,150,250,200));
					g.fillRect(rect.x+MAP_BORDER,rect.y+MAP_BORDER,rect.width,rect.height);
				}
			}
		}

		int w = map.getWidth() * SpriteSheet.TILE_SIZE;
		int h = map.getHeight() * SpriteSheet.TILE_SIZE;
		g.setColor(new Color(50, 50, 50));
		g.drawRect(x - 1, y - 1, w + 1, h + 1);

	}

	private void paintGrid(Graphics g, int sx, int sy) {
		g.setColor(new Color(150, 150, 150));
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int xp = sx + x * SpriteSheet.TILE_SIZE;
				int yp = sy + y * SpriteSheet.TILE_SIZE;

				g.drawRect(xp, yp, SpriteSheet.TILE_SIZE, SpriteSheet.TILE_SIZE);
			}
		}
	}

	private void createCursor() {
		Image image = null;
		if (toolSelection.isEraseTool()) {
			image = ResourceLoader.loadImage("draw_eraser.png");
		} else if (toolSelection.isFillTool()) {
			image = ResourceLoader.loadImage("paintcan.png");
		} else if (toolSelection.isSelectTool()) {
			image = ResourceLoader.loadImage("select.png");
		}
		if(image!=null)
		{
		Cursor c=Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0,0), "");
		setCursor(c);
		}
		else
		{
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		}
	}

	private class PreviewUpdater implements MouseMotionListener, MouseListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			mx = e.getX();
			my = e.getY();
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			mx = e.getX();
			my = e.getY();
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			showPreview = true;
			createCursor();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			showPreview = false;
			repaint();
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

	public void setMapGridVisible(boolean state) {
		// TODO Auto-generated method stub
		this.showMapGrid = state;
		repaint();
	}

}

package com.starbattle.client.views.play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.GUIDesign;

public class GameModeEntry extends ViewModel{

	private String mode,map;
	private boolean mouseHover=false;
	private boolean isSelected=false;
	private ModeSelectionListener selectionListener;
	
	public GameModeEntry(String mode, String map)
	{
		this.map=map;
		this.mode=mode;
		initLayout();
	}
	
	public void setSelectionListener(ModeSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}
	
	private void initLayout()
	{
		view.setPreferredSize(new Dimension(900,25));
		view.setOpaque(false);
		view.setCustomPaintPanelInterface(new CustomPaintPanelInterface() {
			
			@Override
			public void paint(Graphics g, Dimension componentSize) {
				
				int y=18;
				if(isSelected)
				{
					g.setColor(new Color(90,200,255));
				}
				else if(mouseHover)
				{
					g.setColor(new Color(250,250,250,150));
				}
				else
				{
					g.setColor(new Color(200,200,200,100));
				}
				g.fillRect(0,0,componentSize.width,componentSize.height);
				
				g.setFont(GUIDesign.labelFont);
				g.setColor(Color.WHITE);
				g.drawString(mode,50,y);
				g.drawString(map,250,y);				
			}
		});
		view.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			 if(!isSelected)
			 {
				 selectionListener.seletMode(GameModeEntry.this);
			 }
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				mouseHover=false;
				view.repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mouseHover=true;
				view.repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		view.repaint();
	}
	
	public String getMap() {
		return map;
	}
	
	public String getMode() {
		return mode;
	}
	
	
}

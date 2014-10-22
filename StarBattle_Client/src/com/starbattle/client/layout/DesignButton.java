package com.starbattle.client.layout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

import com.starbattle.client.resource.GUIDesign;

public class DesignButton extends JButton{

	private boolean isMouseOver=false;
	private static Color fontColor=new Color(255,255,255);
	private static Color selectedFontColor=new Color(0,0,0);
	
	
	public DesignButton(String text) {
		super(text);
		init();
	
	}
	
	public DesignButton(String text, Icon icon)
	{
		super(text,icon);
		init();
	}
	
	private void init()
	{
		setContentAreaFilled(false);
		setBorderPainted(false);
		this.setFont(GUIDesign.buttonFont);
		this.setForeground(fontColor);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(0,10,5,10));
		this.setFocusable(false);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				isMouseOver=false;
				setForeground(fontColor);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				isMouseOver=true;
				setForeground(selectedFontColor);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		int w=this.getWidth();
		int h=this.getHeight();

		
		Image img=null;
		if(isMouseOver)
		{
			img=GUIDesign.buttonSelected.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		}
		else
		{
			img=GUIDesign.button.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		}
		g.drawImage(img,0,0,null);
		super.paintComponent(g);
		
	}
	
}

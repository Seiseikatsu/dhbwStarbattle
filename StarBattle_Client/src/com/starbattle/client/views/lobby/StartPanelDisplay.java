package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.views.lobby.control.StartPanelListener;

public class StartPanelDisplay extends ViewModel{

	private DesignButton playButton = new DesignButton("Play");
	private DesignButton shopButton = new DesignButton("Shop");
	private DesignButton logoutButton =new DesignButton("Exit");
	private DesignButton settingsButton =new DesignButton("Settings");
	private StartPanelListener startPanelListener;
	
	public StartPanelDisplay(StartPanelListener startPanelListener)
	{
		this.startPanelListener=startPanelListener;
		view.setPreferredSize(new Dimension(0, 200));
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout());
		view.setBackground(new Color(100,100,100));
		
		JPanel topLine=new JPanel();
		topLine.setOpaque(false);
		topLine.setPreferredSize(new Dimension(0,40));
		JPanel botLine=new JPanel(new FlowLayout(FlowLayout.LEFT));
		botLine.setOpaque(false);
		botLine.setPreferredSize(new Dimension(0,40));
		
		botLine.add(logoutButton);
		botLine.add(settingsButton);
		
		
		ControlView control=new ControlView();
		
		view.add(topLine,BorderLayout.NORTH);
		view.add(botLine,BorderLayout.SOUTH);	
		view.add(control.getView(),BorderLayout.CENTER);
			
		addListeners();
	}
	
	private void addListeners()
	{
		logoutButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				startPanelListener.disconnect();
			}
		});
		
		settingsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startPanelListener.openSettings();
			}
		});		
		
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startPanelListener.startGame();
			}
		});
		
		shopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startPanelListener.openShop();
			}
		});
	}
	
	private class ControlView extends ViewModel{
		
		public ControlView()
		{
			this.view.setLayout(new GridBagLayout());

			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0,20,0,20);
			playButton.setButtonStyle(2);
			shopButton.setButtonStyle(2);
			playButton.setFontSize(30f,Font.BOLD);
			shopButton.setFontSize(30f,Font.BOLD);
			shopButton.setPreferredSize(new Dimension(140, 100));
			playButton.setPreferredSize(new Dimension(140, 100));
			
			this.view.add(shopButton,c);
			this.view.add(playButton,c);
			
			
			this.setCustomPaintPanel(new CustomPaintPanelInterface() {
				
				@Override
				public void paint(Graphics g, Dimension size) {
					
					int w=size.width;
					int h=size.height;
					g.setColor(new Color(50,50,50));
					g.fillRect(0,0,w,h);
					
					g.setColor(new Color(70,70,70));
					for(int y=0; y<h/5; y++)
					{
						g.fillRect(0,y*5,w,2);
					}
					
					int b=5;
					g.setColor(new Color(150,150,150));
					g.fillRect(0,0,w,b);
					g.fillRect(0,h-b,w,b);
					
					
				}
			});
		}
		
	}
	
	
	
}

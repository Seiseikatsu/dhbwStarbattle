package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.player.PlayerValues;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.lobby.control.PlayerBarListener;

public class PlayerBarDisplay extends ViewModel {

	private LevelBarDisplay levelBarDisplay = new LevelBarDisplay();
	private MoneyDisplay moneyDisplay = new MoneyDisplay();
	private PlayerButton playerButton;
	private PlayerBarListener playerBarListener;
	
	public PlayerBarDisplay(PlayerBarListener playerBarListener)
	{
		this.playerBarListener=playerBarListener;
		initLayout();
	}
	
	private void initLayout()
	{
		
		view.setLayout(new BorderLayout());
		view.setBackground(new Color(30,30,30));
		
		
		playerButton=new PlayerButton(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				playerBarListener.showPlayerProfile();
			}
		});
		
		JPanel right=new JPanel();
		right.setOpaque(false);
		JPanel left=new JPanel();
		left.setOpaque(false);
		
		right.add(levelBarDisplay.getView());
		right.add(moneyDisplay.getView());
		left.add(playerButton.getView());
		
		view.add(left,BorderLayout.WEST);
		view.add(right,BorderLayout.EAST);
		
	}
	
	public void update()
	{
		playerButton.updateName(PlayerValues.getPlayerDisplayName());
	}
	
	private class PlayerButton extends ViewModel{
		
		private DesignLabel name=new DesignLabel("");
		
		public PlayerButton(ActionListener listener)
		{
			this.view.setPreferredSize(new Dimension(400,34));
			this.view.setBackground(new Color(180,130,110));
			this.view.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));			
			this.view.setBorder(BorderFactory.createLineBorder(new Color(200,200,200),1));
			DesignButton button=new DesignButton(ResourceLoader.loadIcon("user_astronaut.png"));
			button.setBackground(new Color(100,100,100));
			button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
			button.setBorderPainted(true);
			button.setOpaque(true);
			this.view.add(button);
			name.setFont(name.getFont().deriveFont(Font.BOLD));
			this.view.add(name);
			this.setCustomPaintPanel(new CustomPaintPanelInterface() {
				
				@Override
				public void paint(Graphics g, Dimension componentSize) {
					int w=componentSize.width;
					int h=componentSize.height;
					//g.setColor(new Color(50,200,250));
					g.setColor(new Color(100,100,100));
					int s=24;
					int y=h/2-s/2;				
					g.fillRect(0,y,w,s);
					
					g.setColor(new Color(70,70,70));
					s=18;
				    y=h/2-s/2;				
					g.fillRect(0,y,w,s);
					
				}
			});
		}
		
		public void updateName(String name)
		{
			String text=name;
			if(text==null)
			{
				text="Debug Test User";
			}
			this.name.setText(text);
		}
	}
	
}

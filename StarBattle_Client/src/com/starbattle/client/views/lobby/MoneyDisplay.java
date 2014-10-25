package com.starbattle.client.views.lobby;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class MoneyDisplay extends ViewModel{

	private DesignLabel money=new DesignLabel("0", ResourceLoader.loadIcon("coins.png",22,22), Color.WHITE, 20);
	private DesignLabel points=new DesignLabel("0", ResourceLoader.loadIcon("pointsIcon.png",22,22), Color.WHITE, 20);
	
	public MoneyDisplay()
	{
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new FlowLayout());
		view.setBackground(new Color(90,90,90));
		view.setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
		money.setBorder(BorderFactory.createEmptyBorder());
		
		view.add(money);
		view.add(Box.createHorizontalStrut(10));
		view.add(points);
		
		setGold(1500);
		setPoints(300);
	}
	
	public void setGold(int gold)
	{
		money.setText(Integer.toString(gold));
	}
	
	public void setPoints(int p)
	{
		points.setText(Integer.toString(p));
	}
	
}

package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.starbattle.client.layout.StandardViewModel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.resource.ResourceLoader;

public class FriendList extends StandardViewModel{

	private JButton showHide=new JButton();
	private JPanel content=new JPanel();
	private String name;
	
	public FriendList(String title, String icon)
	{
		this.name=title;
		showHide.setText(title);
		showHide.setIcon(ResourceLoader.loadIcon(icon));
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout(0,0));	
		showHide.setPreferredSize(new Dimension(280,25));
		showHide.setHorizontalAlignment(SwingConstants.LEFT);
		showHide.setBackground(new Color(20,140,220));
		showHide.setForeground(new Color(200,250,200));
		view.add(showHide,BorderLayout.NORTH);
		view.add(content,BorderLayout.CENTER);
		content.setLayout(new VerticalLayout());
		content.setVisible(false);
		
		//change visibility of content on click
		showHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean visible=content.isVisible();
				content.setVisible(!visible);
			}
		});
	}
	
	public void initList()
	{
		content.removeAll();
	}
	
	public void addRelation(FriendRelation relation)
	{
		FriendRelationView view=new FriendRelationView(relation);
		content.add(view.getView());
		showHide.setText(name+" ("+content.getComponentCount()+")");
	}
	
	
}

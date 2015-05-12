package com.starbattle.client.views.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.network.connection.objects.NP_GameModesList;

public class GameSettingsDisplay extends ViewModel{

	
	private ArrayList<GameModeEntry> modes=new ArrayList<GameModeEntry>();
	private ViewModel content;
	private ModeSelectionListener selectionListener=new SelectionListener();
	
	public GameSettingsDisplay()
	{
		initLayout();
	}
	
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout());
		JPanel header=new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(70,70,70));
		header.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 2));
		header.add(Box.createHorizontalStrut(50));
		header.add(new DesignLabel("Playmode", ResourceLoader.loadIcon("group.png")));
		header.add(Box.createHorizontalStrut(100));
		header.add(new DesignLabel("Map", ResourceLoader.loadIcon("tree.png")));
		
	    content=new ListContainer();
		//content.setBackground(new Color(150,150,150));
	  
		
		view.add(content.getView(),BorderLayout.CENTER);
		view.add(header,BorderLayout.NORTH);
	}
	
	public void initGameModes(NP_GameModesList list)
	{
		modes.clear();
		content.getView().removeAll();
		/* TODO
		for (int i=0; i<list.modeNames.length; i++)
		{
			String name=list.modeNames[i];
			addGameMode(name, "testmap");
		}*/
		
		//select first one so we dont have to manage a state where no mode is selected
		modes.get(0).setSelected(true);
	}
	
	private void addGameMode(String mode, String map)
	{
		GameModeEntry entry=new GameModeEntry(mode, map);
		entry.setSelectionListener(selectionListener);
		modes.add(entry);
		content.getView().add(entry.getView());
	}
	
	
	private class ListContainer extends ViewModel{
		
		public ListContainer()
		{
			this.view.setLayout(new VerticalLayout());
			this.view.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
			this.view.setBackgroundImage(ResourceLoader.loadImage("space_background.jpg"),0,0);
		}
		
	}
	
	private class SelectionListener implements ModeSelectionListener{

		@Override
		public void seletMode(GameModeEntry entry) {
			
			//set all on false
			for(GameModeEntry mode: modes)
			{
				mode.setSelected(false);
			}
			//set selected on true
			entry.setSelected(true);
		}		
	}
	
}

package com.starbattle.mapeditor.main;

import java.awt.Dimension;

import javax.swing.UIManager;

import com.starbattle.mapeditor.gui.MainPanel;
import com.starbattle.mapeditor.window.WindowContainer;

public class StarbattleMapEditor {

	
	public static void main(String[] args) {
		new StarbattleMapEditor();
	}
	
	public StarbattleMapEditor()
	{
		 try {
	         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }

		WindowContainer window=new WindowContainer(new Dimension(1000,600), "Map Editor");
		window.setView(new MainPanel());
		window.open();
	}
	
	
}

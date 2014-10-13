package com.starbattle.mapeditor.main;

import java.awt.Dimension;
import java.io.File;

import javax.swing.UIManager;

import com.starbattle.mapeditor.gui.MainPanel;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.window.LoadingWindow;
import com.starbattle.mapeditor.window.WindowContainer;

public class StarbattleMapEditor {

	public static String fileExtension=".sbmap";
	public static File mapsFolder=new File("maps");
	
	public static void main(String[] args) {
		new StarbattleMapEditor();
	}
	
	public StarbattleMapEditor()
	{
		LoadingWindow loadingWindow=new LoadingWindow();
		loadingWindow.open();
		WindowContainer window=new WindowContainer(new Dimension(1000,600), "Map Editor");
		window.setView(new MainPanel());
		
		//load map textures
		MapTextureLoader.loadTextures();
		
		 try {
	         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }

		loadingWindow.close();
		window.open();
	}
	
	
}

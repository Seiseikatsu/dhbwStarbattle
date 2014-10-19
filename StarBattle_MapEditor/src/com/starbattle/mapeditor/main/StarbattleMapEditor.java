package com.starbattle.mapeditor.main;

import java.awt.Dimension;
import java.io.File;

import javax.swing.UIManager;
import javax.xml.bind.JAXBException;

import com.starbattle.mapeditor.gui.MainPanel;
import com.starbattle.mapeditor.gui.dialogs.DialogViewer;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.window.LoadingWindow;
import com.starbattle.mapeditor.window.WindowContainer;

public class StarbattleMapEditor {

	public static String fileExtension = ".sbmap";
	public static File mapsFolder = new File("maps");
	public static File mapresSettings = new File("mapres/settings.xml");

	public static void main(String[] args) {
		new StarbattleMapEditor();
	}

	public StarbattleMapEditor() {
		System.out.println("Start MapEditor...");
		LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.open();
		WindowContainer window = new WindowContainer(new Dimension(1000, 600), "Map Editor");
		window.setView(new MainPanel());

		// load map textures
		try {
			MapTextureLoader.loadTextures();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			DialogViewer.showErrorMessage("Mapres Loader", "Failed to load textures: \n" + e1.getMessage());
			System.exit(0);
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Create window");
		loadingWindow.close();
		window.open();
	}

}

package com.starbattle.mapeditor.gui.dialogs;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.starbattle.mapeditor.main.StarbattleMapEditor;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.map.file.MapFile;
import com.starbattle.mapeditor.map.file.compiler.MapFileCompiler;
import com.starbattle.mapeditor.resource.ResourceLoader;

public class MapSaveDialog {

	private File path;

	public MapSaveDialog(File path) {
		this.path = path;
	}

	public void show(Map map) {
		JFileChooser jfc = new JFileChooser(path);
		
		jfc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {

				return "Startbattle Mapfile";
			}

			@Override
			public boolean accept(File f) {

				if (f.getName().toLowerCase().endsWith(StarbattleMapEditor.fileExtension)) {
					return true;
				}
				return false;
			}
		});
		int aktion=jfc.showSaveDialog(null);
		if(aktion==JFileChooser.APPROVE_OPTION)
		{
		File file = jfc.getSelectedFile();
	    if (!file.getName().toLowerCase().endsWith(StarbattleMapEditor.fileExtension))
		{
			//add file extension
			file=new File(file.getAbsolutePath()+StarbattleMapEditor.fileExtension);
		}
		
		//save map
		try {
			MapFile mapFile=MapFileCompiler.compileToFile(map);
			ResourceLoader.writeObjectFile(mapFile, file);
			DialogViewer.showMessage("Save Map", "Map saved successful!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DialogViewer.showErrorMessage("Save Map Error", "Failed to save map: \n"+e.getMessage());
		}
		}
	}

}

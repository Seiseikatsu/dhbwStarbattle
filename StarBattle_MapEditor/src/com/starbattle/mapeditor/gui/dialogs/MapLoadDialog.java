package com.starbattle.mapeditor.gui.dialogs;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.starbattle.mapeditor.main.StarbattleMapEditor;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.map.file.MapFile;
import com.starbattle.mapeditor.map.file.compiler.MapFileCompiler;
import com.starbattle.mapeditor.resource.ResourceLoader;

public class MapLoadDialog {

	private File path;

	public MapLoadDialog(File path) {
		this.path = path;
	}

	public Map show() {
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
		int aktion=jfc.showOpenDialog(null);
		if(aktion==JFileChooser.APPROVE_OPTION)
		{
		File file = jfc.getSelectedFile();
		
		Map map=null;
		try {
			 MapFile mapFile = (MapFile) ResourceLoader.readObjectFile(file);
			 map=MapFileCompiler.decompileToMap(mapFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DialogViewer.showErrorMessage("Map Loading Error", "Failed to read mapfile: \n"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DialogViewer.showErrorMessage("Map Loading Error", "Failed to decompile mapfile: \n"+e.getMessage());			
		}
		return map;
		}
		return null;
	}

}

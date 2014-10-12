package com.starbattle.mapeditor.gui.dialogs;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;
import com.starbattle.mapeditor.map.Map;

public class DialogViewer {

	
	
	public static boolean showLayerSettingsDialog(MapLayer layer)
	{
		EditLayerDialog dialog=new EditLayerDialog(layer);
		JOptionPane.showOptionDialog(null, dialog.getView(),"Edit Layer", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
		return dialog.isDeleteLayer();		
	}
	
	public static Map showNewMapDialog()
	{
		NewMapDialog dialog=new NewMapDialog();
		int answer=JOptionPane.showConfirmDialog(null, dialog.getView(), "New Map", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(answer==JOptionPane.OK_OPTION)
		{
			try {
				return new Map(new Dimension(dialog.getMapWdith(),dialog.getMapHeight()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return null;
	}
	
	public static boolean showNewLayerDialog(Map map)
	{
		NewLayerDialog dialog=new NewLayerDialog();
		int answer=JOptionPane.showConfirmDialog(null, dialog.getView(), "New Layer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(answer==JOptionPane.OK_OPTION)
		{
			int type=dialog.getLayerType();
			String res=dialog.getMapresName();
			MapLayer layer=null;
			if(type==0)
			{
				layer=new TiledLayer(res); 
			}else
			{
				layer=new DecorationLayer(res);
			}
			map.addLayer(layer);
			return true;
		}
		return false;
	}
	
}

package com.starbattle.mapeditor.gui.dialogs;

import javax.swing.JOptionPane;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;
import com.starbattle.mapeditor.map.Map;

public class DialogViewer {

	
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

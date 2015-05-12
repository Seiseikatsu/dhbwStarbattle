package com.starbattle.client.views.play;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.starbattle.client.layout.DesignLabel;

public class MapEntryRenderer implements ListCellRenderer<String> {

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {

		Color fontColor;
		if (isSelected) {
			fontColor = new Color(200, 200, 200);
		} else {
			fontColor = new Color(50, 50, 50);
		}
		if(value==null)
		{
			if (isSelected) {
				fontColor = new Color(150, 150, 250);
			} else {
				fontColor = new Color(50, 50, 150);
			}
			value="Random Map";
		}
		
		DesignLabel label = new DesignLabel(value, fontColor, 24);

		if (isSelected) {
			label.setBackground(new Color(100, 100, 100));
			label.setOpaque(true);
		}
		return label;
	}

}

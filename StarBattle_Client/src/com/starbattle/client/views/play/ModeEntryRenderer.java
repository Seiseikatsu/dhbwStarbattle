package com.starbattle.client.views.play;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.starbattle.client.layout.DesignLabel;

public class ModeEntryRenderer implements ListCellRenderer<ModeSelector> {

	@Override
	public Component getListCellRendererComponent(JList<? extends ModeSelector> list, ModeSelector value, int index,
			boolean isSelected, boolean cellHasFocus) {

		String modeName = value.getName();
		Color fontColor;
		if (isSelected) {
			fontColor = new Color(200, 200, 200);
		} else {
			fontColor = new Color(50, 50, 50);
		}
		DesignLabel label = new DesignLabel(modeName, fontColor, 20);

		if (isSelected) {
			label.setBackground(new Color(100, 100, 100));
			label.setOpaque(true);
		}
		return label;
	}

}

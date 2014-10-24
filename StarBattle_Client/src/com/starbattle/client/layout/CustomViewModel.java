package com.starbattle.client.layout;

import javax.swing.JPanel;

public abstract class CustomViewModel {

	protected JPanel view;

	public void initCustomModelView(CustomPaintPanelInterface customPaintPanelInterface) {
		view = new CustomPanel(customPaintPanelInterface);
	}

	public JPanel getView() {
		return view;
	}
}

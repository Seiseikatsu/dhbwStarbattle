package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.starbattle.mapeditor.gui.components.PlaceToolComponent;
import com.starbattle.mapeditor.gui.control.ToolSelection;
import com.starbattle.mapeditor.gui.dialogs.DialogViewer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.resource.ResourceLoader;
import com.starbattle.mapeditor.window.ContentPanel;

public class ToolbarPanel extends ContentPanel {

	private JToolBar toolbar = new JToolBar();
	private ButtonListener buttonListener = new ButtonListener();
	private Map map;
	private MainPanel mainPanel;

	private PlaceToolComponent tilePlace;
	private PlaceToolComponent decoPlace;
	private ToolSelection toolSelection = new ToolSelection();

	public ToolbarPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		tilePlace = new PlaceToolComponent(toolSelection, false);
		decoPlace = new PlaceToolComponent(toolSelection, true);
		initLayout();
	}

	public void setMap(Map map) {
		this.map = map;
	}

	int toolComponentID = 6;

	private void initLayout() {
		toolbar.setFloatable(false);
		view.setBorder(BorderFactory.createEtchedBorder());
		view.setLayout(new BorderLayout());
		view.add(toolbar, BorderLayout.CENTER);
		addButton("New Map", "map_add.png");
		addButton("Load Map", "folder.png");
		addButton("Save Map", "save_as.png");
		toolbar.addSeparator();
		addButton("New Layer", "layer_add.png");
		toolbar.addSeparator();
		toolbar.add(tilePlace.getView());
	}

	public ToolSelection getToolSelection() {
		return toolSelection;
	}

	public void selectLayer(boolean decoLayer) {
		if (decoLayer) {
			if (toolbar.getComponent(toolComponentID) == tilePlace.getView()) {
				toolbar.remove(toolComponentID);
				decoPlace.updateSelection();
				toolbar.add(decoPlace.getView());

			}
		} else {
			if (toolbar.getComponent(toolComponentID) == decoPlace.getView()) {
				toolbar.remove(toolComponentID);
				tilePlace.updateSelection();
				toolbar.add(tilePlace.getView());
			}
		}
		toolbar.revalidate();
		toolbar.repaint();
	}

	private void pressedButton(int id) {
		switch (id) {
		case 0: // new map dialog
			Map newMap = DialogViewer.showNewMapDialog();
			if (newMap != null) {
				// set new map
				mainPanel.updateMap(newMap);
			}
			break;
		case 1: // load map dialog
			Map loadMap = DialogViewer.showLoadMapDialog();
			if (loadMap != null) {
				mainPanel.updateMap(loadMap);
			}
			break;
		case 2: // save map dialog
			DialogViewer.showSaveMapDialog(mainPanel.getMap());
			break;
		case 3: // New layer dialog
			if (DialogViewer.showNewLayerDialog(map)) {
				mainPanel.updateLayer();
			}
			break;
		}

	}

	private int buttonID = 0;

	private void addButton(String name, String iconname) {
		JButton button = new JButton();
		button.setToolTipText(name);
		button.setName(buttonID + "");
		buttonID += 1;
		button.setPreferredSize(new Dimension(32, 32));
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setIcon(ResourceLoader.loadIcon(iconname));
		button.addActionListener(buttonListener);
		toolbar.add(button);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String name = button.getName();
			pressedButton(Integer.parseInt(name));
		}

	}
}

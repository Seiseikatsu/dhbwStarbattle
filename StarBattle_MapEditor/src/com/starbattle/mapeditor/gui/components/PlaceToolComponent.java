package com.starbattle.mapeditor.gui.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JToggleButton;

import com.starbattle.mapeditor.gui.control.ToolSelection;
import com.starbattle.mapeditor.resource.ResourceLoader;
import com.starbattle.mapeditor.window.ContentPanel;

public class PlaceToolComponent extends ContentPanel {

	private ArrayList<JToggleButton> buttons = new ArrayList<JToggleButton>();

	public final static int TOOL_PENCIL = 0;
	public final static int TOOL_ERASER = 1;
	public final static int TOOL_SELECT = 2;
	public final static int TOOL_RECTANGLE = 2;
	public final static int TOOL_FILL = 3;

	private ToolSelection toolSelection;
	private boolean decorationPanel;

	public PlaceToolComponent(ToolSelection toolSelection, boolean decorationPanel) {
		this.toolSelection = toolSelection;
		this.decorationPanel = decorationPanel;
		initLayout();
	}

	private void initLayout() {
		view.setLayout(new FlowLayout(FlowLayout.LEFT));
		view.setBorder(BorderFactory.createLoweredBevelBorder());
		createToolButton("pencil.png");
		createToolButton("draw_eraser.png");

		if (decorationPanel) {
			// decoration panel tools
			createToolButton("select.png");

		} else {
			// standard panel
			createToolButton("rectangle.png");
			createToolButton("paintcan.png");
		}
		view.setMaximumSize(new Dimension(buttons.size() * 40, 45));
		buttons.get(0).setSelected(true);
	}

	public void updateSelection() {
		for (JToggleButton b : buttons) {
			if (b.isSelected()) {
				toolSelection.setSeletcedTool(buttons.indexOf(b), decorationPanel);
			}
		}

	}

	private void createToolButton(String icon) {
		final JToggleButton b = new JToggleButton(ResourceLoader.loadIcon(icon));
		b.setPreferredSize(new Dimension(32, 32));
		b.setBorderPainted(false);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.setSelected(true);
				toolSelection.setSeletcedTool(buttons.indexOf(b), decorationPanel);

				for (JToggleButton button : buttons) {
					if (button != b) {
						button.setSelected(false);
					}
				}
			}
		});
		view.add(b);
		buttons.add(b);
	}

}

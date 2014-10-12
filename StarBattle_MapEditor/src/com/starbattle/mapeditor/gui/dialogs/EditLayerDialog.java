package com.starbattle.mapeditor.gui.dialogs;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.starbattle.mapeditor.layer.GameLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.window.ContentPanel;
import com.starbattle.mapeditor.window.VerticalLayout;

public class EditLayerDialog extends ContentPanel {

	private JComboBox<String> mapresName;
	private boolean deleteLayer = false;

	public EditLayerDialog(final MapLayer layer) {
		mapresName = new JComboBox<String>(MapTextureLoader.getMapresNames());
		view.setLayout(new VerticalLayout());

		JLabel title = new JLabel("Edit Layer");
		title.setFont(title.getFont().deriveFont(20f));
		JButton clear = new JButton("Clear Layer");
		JButton delete = new JButton("Delete Layer");
		JButton setres = new JButton("Set");

		view.add(title);
		view.add(Box.createVerticalStrut(10));
		view.add(new JLabel("Layer Type: " + layer.getName()));
		if (layer instanceof GameLayer) {
			delete.setEnabled(false);
			setres.setEnabled(false);
			mapresName.setEnabled(false);
		} else {
			view.add(Box.createVerticalStrut(10));
			view.add(new JLabel("Resource: " + layer.getResource()));
		}
		view.add(Box.createVerticalStrut(15));

		JPanel p = new JPanel();
		p.add(mapresName);
		p.add(setres);

		view.add(p);
		view.add(Box.createVerticalStrut(10));
		view.add(clear);
		view.add(Box.createVerticalStrut(10));
		view.add(delete);
		view.add(new JLabel(""));

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layer.clear();
				close();
			}
		});

		setres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layer.setResource((String) mapresName.getSelectedItem());
				close();
			}
		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLayer = true;
				close();
			}
		});
	}

	public boolean isDeleteLayer() {
		return deleteLayer;
	}

	private void close() {
		Window[] windows = Window.getWindows();
		for (Window window : windows) {
			if (window instanceof JDialog) {
				JDialog dialog = (JDialog) window;
				if (dialog.getContentPane().getComponentCount() == 1
						&& dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
					dialog.dispose();
				}
			}
		}
	}

}

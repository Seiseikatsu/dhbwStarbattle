package com.starbattle.client.main.error;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.window.BorderMouseListener;
import com.starbattle.client.window.DesignWindowBorder;
import com.starbattle.client.window.WindowBorderListener;

public abstract class BugSplashDialog {

	private static JDialog dialog;
	private static JTextArea text = new JTextArea(5, 20);
	private static DesignLabel info;

	public static void init() {

		info = new DesignLabel("", new Color(50, 50, 50), 20);
		dialog = new JDialog();
		dialog.setUndecorated(true);
		dialog.setModal(true);
		dialog.setMinimumSize(new Dimension(400, 200));
		dialog.setLocationByPlatform(true);
		JPanel content = new JPanel(new BorderLayout());
		DesignWindowBorder border = new DesignWindowBorder("StarBattle - Information");
		content.setBorder(border);
		BorderMouseListener mouseListener = new BorderMouseListener(border, new WindowBorderListener() {

			@Override
			public void setLocation(int x, int y) {
				dialog.setLocation(x, y);
			}

			@Override
			public void closeWindow() {
				dialog.dispose();
			}
		});

		content.addMouseListener(mouseListener);
		content.addMouseMotionListener(mouseListener);

		content.add(info, BorderLayout.NORTH);
		content.add(new JScrollPane(text), BorderLayout.CENTER);
		text.setBackground(new Color(200, 200, 200));
		text.setEditable(false);
		text.setFont(text.getFont().deriveFont(15f));

		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		DesignButton close = new DesignButton("Close");
		south.add(close);
		south.add(Box.createHorizontalStrut(50));

		content.add(south, BorderLayout.SOUTH);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		dialog.setContentPane(content);
		dialog.pack();
	}

	public static void showMessage(String message) {
		info.setIcon(ResourceLoader.loadIcon("error.png"));
		info.setText("Message");
		text.setText(message);
		dialog.setVisible(true);
	}

	public static void showError(String message) {
		info.setIcon(ResourceLoader.loadIcon("exclamation.png"));
		info.setText("Error");
		text.setText(message);
		dialog.setVisible(true);
	}

}

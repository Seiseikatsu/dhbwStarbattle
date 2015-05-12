package com.starbattle.client.layout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class SelectButtonGroup extends ViewModel {

	private List<JButton> buttons = new ArrayList<JButton>();
	private int selected;

	public SelectButtonGroup(String[] items) {
		view.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (String text : items) {
			final DesignButton button = new DesignButton(text);
			button.setButtonStyle(2);
			button.setFontSize(20);
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					selected = buttons.indexOf(button);
					button.setSelected(true);
					for (JButton b : buttons) {
						if (b != button) {
							b.setSelected(false);
						}
					}

				}
			});
			buttons.add(button);
			view.add(button);
		}
		selected = 0;
		buttons.get(0).setSelected(true);
	}

	public int getSelected() {
		return selected;
	}
}

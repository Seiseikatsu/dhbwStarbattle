package com.starbattle.client.views.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.starbattle.client.layout.SelectButtonGroup;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.GUIDesign;
import com.starbattle.network.connection.objects.NP_GameModeEntry;
import com.starbattle.network.connection.objects.NP_GameModesList;

public class GameModeDisplay extends ViewModel {

	private DefaultListModel<ModeSelector> modeList = new DefaultListModel<ModeSelector>();
	private DefaultListModel<String> mapList = new DefaultListModel<String>();
	private JList<ModeSelector> modeView;
	private JList<String> mapView;
	private SelectButtonGroup searchMode;

	public final static String[] SEARCH_MODE = { "Standard", "Fast", "Long" };

	private NP_GameModesList modes;

	public GameModeDisplay() {

		view.setLayout(new BorderLayout());

		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		modeView = new JList<ModeSelector>(modeList);
		TitledBorder border = BorderFactory.createTitledBorder("Gamemode");
		border.setTitleFont(GUIDesign.labelFont.deriveFont(Font.BOLD));
		border.setTitleColor(new Color(250, 250, 250));
		modeView.setBorder(border);
		modeView.setBackground(new Color(150, 150, 150));
		modeView.setCellRenderer(new ModeEntryRenderer());
		modeView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(modeView, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(300, 400));
		center.add(scroll);

		mapView = new JList<>(mapList);
		border = BorderFactory.createTitledBorder("Maps");
		border.setTitleFont(GUIDesign.labelFont.deriveFont(Font.BOLD));
		border.setTitleColor(new Color(250, 250, 250));
		mapView.setBorder(border);
		mapView.setBackground(new Color(150, 150, 150));
		mapView.setCellRenderer(new MapEntryRenderer());
		mapView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll = new JScrollPane(mapView, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(300, 400));
		center.add(scroll);

		view.add(center, BorderLayout.CENTER);

		modeView.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					fillMapList();
				}
			}
		});

		searchMode = new SelectButtonGroup(SEARCH_MODE);
		border = BorderFactory.createTitledBorder("Game search option");
		border.setTitleFont(GUIDesign.labelFont.deriveFont(Font.BOLD));
		border.setTitleColor(new Color(50, 50, 50));
		searchMode.getView().setBorder(border);
		view.add(searchMode.getView(), BorderLayout.SOUTH);

	}

	private void fillModeList() {
		modeList.clear();
		for (NP_GameModeEntry entry : modes.modes) {
			modeList.addElement(new ModeSelector(entry.modeDisplayName, entry.mode));
		}
		modeView.setSelectedIndex(0);
	}

	private void fillMapList() {
		mapList.clear();
		int selectedMode = modeView.getSelectedIndex();
		if(selectedMode!=-1)
		{
		NP_GameModeEntry entry = modes.modes[selectedMode];
		mapList.addElement(null); // add random map entry
		for (String map : entry.maps) {
			mapList.addElement(map);
		}
		mapView.setSelectedIndex(0);
		}
	}

	public void initGameModes(NP_GameModesList modes) {
		this.modes = modes;

		fillModeList();
		fillMapList();

		view.revalidate();
		view.repaint();
	}

	public String getSelectedMode() {
		return modeView.getSelectedValue().getMode();
	}

	public String getSelectedMap() {
		return mapView.getSelectedValue();
	}
	
	public int getSearchMode()
	{
		return searchMode.getSelected();
	}
}

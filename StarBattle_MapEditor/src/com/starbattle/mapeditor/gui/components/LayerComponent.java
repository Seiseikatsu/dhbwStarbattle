package com.starbattle.mapeditor.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.Resource;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.mapeditor.gui.listener.LayerListener;
import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.GameLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;
import com.starbattle.mapeditor.resource.ResourceLoader;
import com.starbattle.mapeditor.window.VerticalLayout;

public class LayerComponent {

	private MapLayer layer;
	private JButton view = new JButton();
	private JButton showAndHide=createButton("eye.png");
	private ImageIcon visible,hidden;
	
	public LayerComponent(MapLayer layer, LayerListener listener) {
		this.layer = layer;
		initLayout(listener);
		visible=ResourceLoader.loadIcon("eye.png");
		hidden=ResourceLoader.loadIcon("eye_close.png");
	}

	private void initLayout(final LayerListener listener) {
		view.setBackground(new Color(200, 250, 200));
		// view.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		view.setLayout(new BorderLayout());
		// view.setContentAreaFilled(false);
		JPanel block = new JPanel();
		block.setLayout(new BorderLayout());

		JLabel name = new JLabel(layer.getName());

		block.add(name, BorderLayout.NORTH);
		JLabel resource = new JLabel(layer.getResource());
		resource.setForeground(new Color(0, 150, 0));
		block.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		view.add(block, BorderLayout.CENTER);

		JLabel icon = new JLabel();
		icon.setIcon(getLayerIcon());
		icon.setOpaque(false);
		view.add(icon, BorderLayout.WEST);

		JPanel control = new JPanel();
		control.setLayout(new BorderLayout());
		JButton up = createButton("arrow_up.png");
		JButton down = createButton("arrow_down.png");
		
		JButton edit = createButton("cog.png");
		

		JPanel settings=new JPanel();
		settings.setLayout(new VerticalLayout());
	
		settings.add(showAndHide);
		if(layer instanceof GameLayer )
		{
		//cant edit game layer	
		}
		else
		{
		settings.add(edit);
		block.add(resource, BorderLayout.SOUTH);
		}
		JPanel move = new JPanel();
		move.setLayout(new VerticalLayout());
		move.add(up);
		move.add(down);
		control.add(settings, BorderLayout.WEST);
		control.add(move, BorderLayout.EAST);
		
		view.add(control, BorderLayout.EAST);

		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.moveLayer(layer, false);
			}
		});

		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.moveLayer(layer, true);
			}
		});
		
		showAndHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layer.toggleVisibility();
				if(layer.isVisible())
				{
					showAndHide.setIcon(visible);
				}
				else
				{
					showAndHide.setIcon(hidden);
				}
			}
		});

		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.selectLayer(layer);
			}
		});
	}

	private JButton createButton(String icon) {
		JButton j = new JButton();
		int size = 16;
		j.setIcon(ResourceLoader.loadIcon(icon));
		j.setPreferredSize(new Dimension(size, size));
		j.setContentAreaFilled(false);
		return j;
	}

	private ImageIcon getLayerIcon() {
		if (layer instanceof GameLayer) {
			return ResourceLoader.loadIcon("layerGame.png");
		} else if (layer instanceof DecorationLayer) {
			return ResourceLoader.loadIcon("layerDecoration.png");
		} else if (layer instanceof TiledLayer) {
			return ResourceLoader.loadIcon("layerTile.png");
		}
		return null;
	}

	public JButton getView() {
		return view;
	}

}

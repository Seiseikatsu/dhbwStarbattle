package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class FriendRelationView extends ViewModel {

	private static int iconSize = 16;
	private static Icon deleteIcon = ResourceLoader.loadIcon("cross.png", iconSize, iconSize);
	private static Icon acceptIcon = ResourceLoader.loadIcon("accept.png", iconSize, iconSize);
	private static Icon mailIcon = ResourceLoader.loadIcon("email.png", iconSize, iconSize);
	private static Icon profilIcon = ResourceLoader.loadIcon("user.png", iconSize, iconSize);

	private JLabel name = new JLabel();
	private JButton decline = new DesignButton(deleteIcon);
	private JButton viewProfil = new DesignButton(profilIcon);
	private JPanel actions = new JPanel();
	private boolean isMouseOver = false;
	private FriendActionListener friendActionListener;
	private String friendName;

	public FriendRelationView(FriendRelation friend, FriendActionListener friendActionListener) {

		this.friendName = friend.getName();
		this.friendActionListener = friendActionListener;
		setCustomPaintPanel(new BackgroundPainter());
		name.setText("  " + friend.getName());
		initLayout();
		switch (friend.getState()) {
		case FriendRelation.RELATION_FRIENDS:
			initFriendLayout(friend.isOnline());
			break;
		case FriendRelation.RELATION_REQUEST:
			initRequestLayout();
			break;
		case FriendRelation.RELATION_PENDING:
			initPendingLayout();
			break;
		}
		actions.add(viewProfil);
	}

	private void initLayout() {
		view.addMouseListener(new SelectionListener());
		view.setOpaque(false);
		actions.setOpaque(false);
		name.setOpaque(false);
		view.setPreferredSize(new Dimension(280, 25));
		decline.setIcon(deleteIcon);
		view.setLayout(new BorderLayout());
		view.add(name, BorderLayout.WEST);
		view.add(actions, BorderLayout.EAST);
		name.setFont(name.getFont().deriveFont(12f));
		name.setForeground(new Color(50, 50, 50));
		// view.setBorder(BorderFactory.createEtchedBorder());
		actions.setLayout(new FlowLayout(FlowLayout.TRAILING, 2, 2));
		actions.setBackground(new Color(120, 190, 230));

		decline.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				friendActionListener.delete(friendName);
			}
		});
	}

	private void initFriendLayout(boolean online) {
		if (online) {
			JButton write = new DesignButton(mailIcon);
			actions.add(write);
			write.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					friendActionListener.openChat(friendName);
				}
			});
		}
	}

	private void initRequestLayout() {
		JButton accept = new DesignButton(acceptIcon);
		actions.add(accept);
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				friendActionListener.accept(friendName);
			}
		});
		actions.add(decline);
	}

	private void initPendingLayout() {

		actions.add(decline);
	}

	private class BackgroundPainter implements CustomPaintPanelInterface {

		@Override
		public void paint(Graphics g, Dimension size) {

			int w = size.width;
			int h = size.height;

			Color c = null;
			if (isMouseOver) {
				c = new Color(255, 200, 120, 200);
			} else {
				c = new Color(255, 200, 120, 150);
			}
			g.setColor(c);

			g.fillRect(0, 0, w, h);

			g.setColor(new Color(0, 0, 0, 200));
			g.fillRect(0, 0, w, 1);
		}

	}

	private class SelectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			isMouseOver = true;
			view.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			isMouseOver = false;
			view.repaint();

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}

	public FriendActionListener getFriendActionListener() {
		return friendActionListener;
	}
}

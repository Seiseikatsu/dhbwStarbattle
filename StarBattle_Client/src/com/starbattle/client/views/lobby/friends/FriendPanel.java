package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.chat.ChatManager;
import com.starbattle.network.connection.objects.NP_Constants;
import com.starbattle.network.connection.objects.NP_FriendUpdate;
import com.starbattle.network.connection.objects.NP_LobbyFriends;

public class FriendPanel extends ViewModel {

	private String[] friendPanelNames = { "Online", "Offline", "Friend Requests", "Requests sent" };
	private String[] friendPanelIcons = { "user.png", "user_offline.png", "user_add.png", "user_go.png" };
	private FriendList[] friendPanels = new FriendList[4];
	private DesignButton addNew = new DesignButton("Add Friend", ResourceLoader.loadIcon("add.png"));
	private DesignButton openList = new DesignButton(ResourceLoader.loadIcon("comments.png"));
	private FriendActionListener friendActionListener;
	private JScrollPane friendPanelContent;
	private JPanel chatPanel;
	public final static int FRIEND_LIST_ONLINE = 0, FRIEND_LIST_OFFLINE = 1, FRIEND_LIST_REQUESTS = 2,
			FRIEND_LIST_PENDING = 3;

	public FriendPanel(final LobbyView lobbyView, final ChatManager chatManager,
			FriendActionListener friendActionListener) {
		this.friendActionListener = friendActionListener;
		initLayout();
		chatPanel = chatManager.getChatListPanel().getView();
		addNew.setFontSize(12f);
		addNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lobbyView.openWindowView(AddFriendView.VIEW_ID);
			}
		});

		openList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showHideChats();
			}
		});
	}

	private void showHideChats() {
		if (friendPanelContent.isVisible()) {
			friendPanelContent.setVisible(false);
			chatPanel.setVisible(true);
			view.remove(friendPanelContent);
			view.add(chatPanel, BorderLayout.CENTER);
		} else {
			friendPanelContent.setVisible(true);
			chatPanel.setVisible(false);
			view.remove(chatPanel);
			view.add(friendPanelContent, BorderLayout.CENTER);
		}
		view.revalidate();
		view.repaint();
	}

	private int getListNumberForType(int relationType, boolean online) {
		int listNumber = relationType;
		if (listNumber > 0) {
			listNumber++;
		} else {
			if (!online) {
				listNumber = 1;
			}
		}
		return listNumber;
	}

	public void updateFriendList(NP_LobbyFriends friends) {
		for (int i = 0; i < 4; i++) {
			friendPanels[i].initList();
		}
		for (int i = 0; i < friends.friendNames.size(); i++) {
			String friendName = friends.friendNames.get(i);
			int relationState = friends.relationStates.get(i);
			boolean online = friends.friendOnline.get(i);
			int listNR = getListNumberForType(relationState, online);
			FriendRelation relation = new FriendRelation(friendName, relationState, online);
			friendPanels[listNR].addRelation(relation);
		}
	}

	private void deleteFriendFromLists(String name) {
		for (int i = 0; i < 4; i++) {
			friendPanels[i].deleteRelation(name);
		}
	}

	public void friendUpdate(NP_FriendUpdate update) {
		String name = update.name;
		int updateType = update.updateType;
		boolean online = update.online;
		deleteFriendFromLists(name);
		// Add Friend or online update is the same in this context
		if (updateType != NP_Constants.FRIEND_UPDATE_TYPE_DELTEFRIEND) {
			FriendRelation newRelation = new FriendRelation(name, FriendRelation.RELATION_FRIENDS, online);
			if (online) {
				friendPanels[FRIEND_LIST_ONLINE].addRelation(newRelation);
			} else {
				friendPanels[FRIEND_LIST_OFFLINE].addRelation(newRelation);
			}
		}
	}

	private void initLayout() {
		setBackgroundImage(ResourceLoader.loadImage("friendlistBackground.jpg"));
		view.setPreferredSize(new Dimension(300, 0));
		view.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 3));

		view.setLayout(new BorderLayout());
		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setLayout(new VerticalLayout());

		for (int i = 0; i < friendPanels.length; i++) {
			friendPanels[i] = new FriendList(friendPanelNames[i], friendPanelIcons[i], friendActionListener);
			content.add(friendPanels[i].getView());
		}

		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		view.add(header, BorderLayout.NORTH);
		friendPanelContent = new JScrollPane(content);
		friendPanelContent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		friendPanelContent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		JScrollBar bar = friendPanelContent.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(12, 0));
		bar.setBackground(new Color(50, 50, 50));

		friendPanelContent.setOpaque(false);
		friendPanelContent.getViewport().setOpaque(false);
		view.add(friendPanelContent, BorderLayout.CENTER);

		header.setBackground(new Color(100, 100, 100));
		JLabel title = new DesignLabel("Friends", "user.png");
		header.add(title);
		header.add(addNew);
		header.add(openList);

		// ADD test friends
		friendPanels[0].addRelation(new FriendRelation("Hans", 0, true));
		friendPanels[0].addRelation(new FriendRelation("Peter", 0, true));
		friendPanels[0].addRelation(new FriendRelation("Htetef", 0, true));
		friendPanels[0].addRelation(new FriendRelation("342sdsa", 0, true));
		friendPanels[0].addRelation(new FriendRelation("ogdioa2", 0, true));
		friendPanels[0].addRelation(new FriendRelation("scuion23##", 0, true));

		friendPanels[1].addRelation(new FriendRelation("Dieter", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Olaf heinz", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Max der Coole", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Hossa", 0, false));
		friendPanels[2].addRelation(new FriendRelation("Jürgen Jürgens", 1, false));
		for (int i = 0; i < 30; i++) {
			friendPanels[3].addRelation(new FriendRelation("Peter Peternsens " + i, 2, false));
		}
	}

	public FriendRelation getFriendRelationTo(String name) {
		for (FriendList list : friendPanels) {
			ArrayList<FriendRelation> relations = list.getRelations();
			for (FriendRelation fr : relations) {
				if (fr.getName().equals(name)) {
					return fr;
				}
			}
		}
		return null;
	}

	public FriendList[] getFriendPanels() {
		return friendPanels;
	}
}

package com.starbattle.client.views.play.results;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkBattleResultsListener;
import com.starbattle.client.game.InGameClientControl;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_BattleResults;
import com.starbattle.network.connection.objects.NP_ExitGame;

public class BattleResultsView extends ContentView {

	public final static int VIEW_ID = 11;

	private DesignButton exit = new DesignButton("Back");

	private final static String youWin = "You won!";
	private final static String youLose = "You lose...";
	private final static String teamWin = "Your Team wins!";
	private final static String teamLose = "Your Team lost...";
	private final static String noContest = "No Contest";

	private DesignLabel winnerText = new DesignLabel("", 30);
	private JPanel center;

	public BattleResultsView(final NetworkConnection connection) {

		view.setLayout(new BorderLayout());
		view.setBackground(new Color(150, 150, 150));

		view.add(winnerText, BorderLayout.NORTH);
		center = new JPanel(new VerticalLayout());
		center.setOpaque(false);

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom.setOpaque(false);
		bottom.add(exit);

		view.add(center, BorderLayout.CENTER);
		view.add(bottom, BorderLayout.SOUTH);

		connection.setBattleResultsListener(new NetworkBattleResultsListener() {
			@Override
			public void receivedBattleResults(NP_BattleResults results) {
				System.out.println("-------------> Results");

				// init view data
				writeResults(results);

				// open this view
				openView(VIEW_ID);
			}
		});
		// back to lobby
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openView(LobbyView.VIEW_ID);
			}
		});
	}

	private void writeResults(NP_BattleResults results) {
		center.removeAll();

		int myID = InGameClientControl.PLAYER_ID;
		int myTeam = results.teams[myID];

		// set winner text
		setWinnerText(results.winnerID, myID, myTeam, results.teamGame);

		// show all players results

		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.setOpaque(false);
		p.add(new DesignLabel("Player", "user_astronaut.png"));
		p.add(new DesignLabel("Score", "pointsIcon.png"));
		if (results.teamGame) {
			p.add(new DesignLabel("Team", "group.png"));
		}
		center.add(p);

		for (int i = 0; i < results.names.length; i++) {
			String name = results.names[i];
			int points = results.points[i];
			int team = results.teams[i];

			p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			p.setOpaque(false);
			p.add(new DesignLabel(name, Color.BLUE));
			p.add(new DesignLabel("" + points, Color.ORANGE));

			if (results.teamGame) {
				p.add(new DesignLabel("Team " + (team + 1)));
			}
			center.add(p);
		}

		view.revalidate();
		view.repaint();
	}

	private void setWinnerText(int winner, int myid, int myteam, boolean teamgame) {
		String text;

		if (teamgame) {
			if (winner == myteam) {

				text = teamWin;
			} else {
				text = teamLose;
			}
		} else {
			if (winner == myid) {
				text = youWin;
			} else {
				text = youLose;
			}
		}

		if (winner == -1) {
			text = noContest;
		}
		winnerText.setText(text);
	}

	@Override
	protected void initView() {
	}

	@Override
	protected void onClosing() {
	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}

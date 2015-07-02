package com.starbattle.client.views;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.game.InGameClientControl;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.friends.AddFriendView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.views.play.ingame.InGameView;
import com.starbattle.client.views.play.queue.WaitingView;
import com.starbattle.client.views.play.results.BattleResultsView;
import com.starbattle.client.views.profile.PlayerProfileView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.client.views.reset.ResetPasswordView;
import com.starbattle.client.views.settings.SettingsView;
import com.starbattle.client.views.shop.ShopView;
import com.starbattle.client.window.GameWindow;
import com.starbattle.client.window.LoadingWindow;

public abstract class ClientViewFactory {

	public static void initViews(GameWindow window, NetworkConnection connection, LoadingWindow loadingWindow, InGameClientControl inGameClientControl) {
		WaitingView waitingView = null;
		for (int i = 0; i < 12; i++) {
			switch (i) {
			case 0:

				window.addView(new LoginView(connection));
				break;
			case 1:

				window.addView(new RegisterView(connection));
				break;
			case 2:

				window.addView(new ResetPasswordView(connection));
				break;
			case 3:

				window.addView(new LobbyView(connection));
				break;
			case 4:

				waitingView = new WaitingView(connection);
				window.addView(waitingView);
				break;
			case 5:

				window.addView(new AddFriendView(connection));
				break;
			case 6:

				window.addView(new SettingsView(connection));
				break;
			case 7:

				window.addView(new ShopView(connection));
				break;
			case 8:

				window.addView(new PlayerProfileView(connection));
				break;
			case 9:
				window.addView(new PlayView(connection, waitingView));
				break;
			case 10:
				window.addView(new InGameView(connection,inGameClientControl));
				break;
			case 11:
				window.addView(new BattleResultsView(connection));
				break;
			}

			if (loadingWindow != null) {
				loadingWindow.loadProgress();
			}
		}
	}

}

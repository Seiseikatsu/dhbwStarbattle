package com.starbattle.server.manager;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.impl.AccountManagerImpl;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;
import com.starbattle.network.connection.objects.NP_ChatMessage;
import com.starbattle.network.connection.objects.NP_FriendRequest;
import com.starbattle.network.connection.objects.NP_HandleFriendRequest;
import com.starbattle.network.connection.objects.NP_Login;
import com.starbattle.network.connection.objects.NP_Register;
import com.starbattle.network.connection.objects.NP_ResetEmail;
import com.starbattle.network.connection.objects.NP_StartAnswer;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.player.PlayerContainer;

public class PlayerManager {

	private AccountManager accountManager;
	private FriendsManager friendsManager;
	private PlayerContainer playerContainer;
	public final static String playerAlreadyLoginMessage="Player is already logged in!";
	
	public PlayerManager(PlayerContainer playerContainer) {
		this.playerContainer = playerContainer;
		accountManager = new AccountManagerImpl();
		friendsManager = new FriendsManager(playerContainer, accountManager);
	}


	public void tryResetEmail(NP_ResetEmail reset) {
		String user = reset.name;
		String email = reset.email;
		try {
			accountManager.tryResetPassword(user, email);
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	public void tryLogin(PlayerConnection player, NP_Login login) {
		String name = login.playerName;
		String password = login.password;
		try {
			LoginState loginState = accountManager.canLogin(name, password);
			String errorText = loginState.getText();
			if (loginState == LoginState.Login_Ok) {
				// check if player is not already connected
				if (!playerContainer.playerConnected(name)) {
					// login player
					loginPlayer(player, name);
					return;
				}
				errorText = PlayerManager.playerAlreadyLoginMessage;
			}
			// error case
			sendAnswer(player, false, errorText);

		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sendAnswer(player, false, "Internal server error!");
		}
	}

	public void tryRegister(PlayerConnection player, NP_Register register) {
		PlayerAccount account = PlayerAccountGenerator.generateAccount(register);
		RegisterState registerState = accountManager.canRegisterAccount(account);
		if (registerState == RegisterState.Register_Ok) {
			// account is valid
			try {
				// create account
				accountManager.registerAccount(account);
				// login player
				loginPlayer(player, register.accountName);
				return;
			} catch (AccountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// error case
		sendAnswer(player, false, registerState.getText());
	}

	private void loginPlayer(PlayerConnection player, String name) {
		player.setAccountName(name);
		String displayName=null;
		try {
			displayName = accountManager.getDisplayName(name);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerContainer.loginPlayer(player,displayName);
		// send player displayname as answer string, just open-game-command to true
		System.out.println("Player login: " + name);
		sendAnswer(player, true, displayName);
		friendsManager.sendCurrentFriends(player); // send friends to player
		// update other player my online status
		friendsManager.updateFriendsMyOnlineStatus(name, true);
	}

	public void logoutPlayer(PlayerConnection player) {
		if (player.isPlayerRegistered()) {// if hes registered, logout
									// from accountlist
			// update other player my online status (online=false)
			friendsManager.updateFriendsMyOnlineStatus(player.getAccountName(), false);
			playerContainer.logoutPlayer(player.getAccountName());
			System.out.println("Player logout: " + player.getAccountName());
		}
	}

	private void sendAnswer(PlayerConnection player, boolean login, String text) {
		NP_StartAnswer answer = new NP_StartAnswer();
		answer.openGame = login;
		answer.answerMessage = text;
		player.sendTCP(answer);
	}


	public void trySendFriendRequest(PlayerConnection player, NP_FriendRequest friendRequest) {
		friendsManager.trySendFriendRequest(player, friendRequest);
	}

	public void handleFriendRequest(PlayerConnection player, NP_HandleFriendRequest friendRequest) {
		friendsManager.handleFriendRequest(player, friendRequest);
	}
	
	public void sendChat(PlayerConnection player, NP_ChatMessage message) {
		friendsManager.sendChat(player, message);
	}

}

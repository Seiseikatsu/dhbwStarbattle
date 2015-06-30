package com.starbattle.debug.server;

import com.starbattle.gameserver.game.timer.GameLoop;
import com.starbattle.gameserver.game.timer.UpdateListener;
import com.starbattle.gameserver.main.BattleEndListener;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleResults;
import com.starbattle.gameserver.main.StarbattleGame;
import com.starbattle.gameserver.main.StarbattleGameControl;
import com.starbattle.network.connection.objects.game.NP_ClientReady;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class ServerGameDebug {

	public final static int GAME_FPS = 60;
	public final static int UPDATE_FPS = 30;

	private StarbattleGame game = new StarbattleGameControl(BattleInitDebug.GAME_ID);
	private GameLoop gameLoop, updateLoop;
	private SendClientsConnection sendClientsConnection;

	public ServerGameDebug() {
	}

	public void setSendClientsConnection(SendClientsConnection sendClientsConnection) {
		this.sendClientsConnection = sendClientsConnection;
	}

	public void receive(Object object, String playerName) {

		if(object instanceof NP_ClientReady)
		{
			sendClientsConnection.sendToClients(new NP_GameStart());
		}
		else if(object instanceof NP_PlayerUpdate)
		{
			game.updatePlayer((NP_PlayerUpdate)object, playerName);
		}
	}

	public void startGame(BattleInitialization init) {
		game.startBattle(init, new BattleEndListener() {
			@Override
			public void battleError() {
				// TODO Auto-generated method stub

			}

			@Override
			public void battleEnd(BattleResults arg0) {
				// TODO Auto-generated method stub

			}
		});
		initGameLoop(GAME_FPS);
		initUpdateLoop(UPDATE_FPS);

	}

	private void initGameLoop(int fps) {
		gameLoop = new GameLoop(new UpdateListener() {
			public void update(double delta) {
				// update all games
				game.updateGame(delta);
			}
		});
		gameLoop.setFPS(fps);// Logical Update Units
		gameLoop.start();
	}

	private void initUpdateLoop(int fps) {
		updateLoop = new GameLoop(new UpdateListener() {
			public void update(double delta) {

				NP_GameUpdate update = game.getGameUpdate();
				sendClientsConnection.sendToClients(update);
			}
		});
		updateLoop.setFPS(fps);
		updateLoop.start();
	}

	public void close() {
		gameLoop.stop();
		updateLoop.stop();
	}

}

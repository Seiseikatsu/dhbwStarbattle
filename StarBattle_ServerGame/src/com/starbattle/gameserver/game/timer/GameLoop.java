package com.starbattle.gameserver.game.timer;

public class GameLoop {

	private UpdateListener update;
	private int game_FPS;
	private boolean gameRunning = true;
	private int currentFPS;

	public GameLoop(UpdateListener updateListener) {
        this.update=updateListener;
    }


	public void setFPS(int fps) {
		this.game_FPS = fps;
		TARGET_FPS = game_FPS;
		OPTIMAL_TIME = 1000000000 / TARGET_FPS;

	}

	public void start() {
		Thread thread = new Thread(new GameThread());
		thread.start();
	}

	private class GameThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			lastLoopTime = System.nanoTime();
			do {
				runGame();
			} while (gameRunning);
		}
	}

	public void stop() {
		gameRunning = false;
	}

	private long lastLoopTime = System.nanoTime();
	private int TARGET_FPS = 60;
	private long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	private int fps = 0;
	private long lastFpsTime = 0;

	public void runGame() {

		long now = System.nanoTime();
		long updateLength = now - lastLoopTime;
		lastLoopTime = now;
		double delta = updateLength / ((double) OPTIMAL_TIME);

		// update the frame counter
		lastFpsTime += updateLength;

		fps++;

		if (lastFpsTime >= 1000000000l) {
			currentFPS = fps;
			lastFpsTime = 0;
			fps = 0;
		}

		update.update(delta);

		long l = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
		if (l < 0) {
			l = 0;
		}
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public int getCurrentFPS() {
		return currentFPS;
	}
}

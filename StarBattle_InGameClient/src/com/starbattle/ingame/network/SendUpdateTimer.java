package com.starbattle.ingame.network;

import java.util.Timer;
import java.util.TimerTask;

public class SendUpdateTimer {

	private final static int SEND_PER_SECOND = 20;
	private Timer timer;
	private SendUpdateListener sendUpdateListener;

	public SendUpdateTimer(SendUpdateListener sendUpdateListener) {
		timer = new Timer();
		this.sendUpdateListener = sendUpdateListener;
	}

	public void start() {
		long period = 1000 / SEND_PER_SECOND;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				sendUpdateListener.sendUdpUpdates();
			}
		}, 0, period);
	}

	public void close() {
		timer.cancel();
	}
}

package com.starbattle.client.testinterface.tester;

import com.starbattle.client.testinterface.main.ClientTestInterface;

public class NetworkCheck {

	private boolean checkOk = false;
	private long lastMilli;
	
	public NetworkCheck(ToleranceCheckTask task) {
		check(task);
	}
	

	private void check(ToleranceCheckTask task) {
		lastMilli = System.currentTimeMillis();

		do {
			checkOk = task.check();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		} while (checkOk == false && isToleranceRunning());

	}

	public boolean isCheckOk() {
		return checkOk;
	}

	public boolean isToleranceRunning() {
		long milli = System.currentTimeMillis();
		int diff = (int) (milli - lastMilli);

		float seconds = diff / 1000f;
		if (seconds <= ClientTestInterface.networkTimeout) {
			return true;
		}
		return false;
	}

}

package com.starbattle.client.testinterface.tester;

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
		} while (checkOk == false && isToleranceRunning());

	}

	public boolean isCheckOk() {
		return checkOk;
	}

	public boolean isToleranceRunning() {
		long milli = System.currentTimeMillis();
		int diff = (int) (milli - lastMilli);

		float seconds = diff / 1000f;
		if (seconds <= ClientAutomate.networkTimeout) {
			return true;
		}
		return false;
	}

}

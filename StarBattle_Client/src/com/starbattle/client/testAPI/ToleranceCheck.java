package com.starbattle.client.testAPI;

public class ToleranceCheck {

	private boolean checkOk = false;
	private long lastMilli;

	public ToleranceCheck(ToleranceCheckTask task) {
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
		if (seconds <= ClientAutomate.toleranceSeconds) {
			return true;
		}
		return false;
	}

}

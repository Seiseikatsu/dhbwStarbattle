package com.starbattle.client.testinterface.tester;

public class ToleranceCheck {

	private boolean checkOk = false;
	private long lastMilli;
	
	public ToleranceCheck(ToleranceCheckTask task) {
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
		if (seconds <= ClientAutomate.toleranceSeconds) {
			return true;
		}
		return false;
	}

}

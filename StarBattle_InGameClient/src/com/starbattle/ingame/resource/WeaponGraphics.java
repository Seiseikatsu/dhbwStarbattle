package com.starbattle.ingame.resource;

public enum WeaponGraphics {

	PLASMA_GUN("plasmaGun.png",22,22);
	
	
	private String file;
	private int rotateX,rotateY;
	
	
	private WeaponGraphics(String file, int x, int y) {
	
		this.file=file;
		this.rotateX=x;
		this.rotateY=y;
	}
	
	
	public int getRotateX() {
		return rotateX;
	}
	
	public int getRotateY() {
		return rotateY;
	}
	
	public String getFile() {
		return file;
	}
	

	
	
}

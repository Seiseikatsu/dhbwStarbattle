package com.starbattle.ingame.resource;

public enum WeaponGraphics {

	
	PLASMA_GUN("plasmaGun.png",31,22,22);
	
	
	private String file;
	private int spriteHeight;
	private int rotateX,rotateY;
	
	
	private WeaponGraphics(String file,int h, int x, int y) {
	
		this.spriteHeight=h;
		this.file=file;
		this.rotateX=x;
		this.rotateY=y;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
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

package com.starbattle.ingame.resource;

public enum PlayerGraphics {

	ASTRONAUT("Astronaut","astronaut.png",2),
	ALIEN("Alien","alien.png",2),
	ASTRONAUT_DARK("Astronaut","astronautDunkel.png",2);
	
	private String name;
	private String file;
	private int deltaY;
	
	private PlayerGraphics(String name, String file, int y) {
		this.name=name;
		this.file=file;
		this.deltaY=y;
	}
	
	public int getDeltaY() {
		return deltaY;
	}
	
	public String getFile() {
		return file;
	}
	
	public String getName() {
		return name;
	}
	
	
}

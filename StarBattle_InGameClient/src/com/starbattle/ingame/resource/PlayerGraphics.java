package com.starbattle.ingame.resource;

public enum PlayerGraphics {

	ASTRONAUT("Astronaut","astronaut.png"),
	ALIEN("Alien","alien.png"),
	ASTRONAUT_DARK("Astronaut","astronautDunkel.png");
	
	private String name;
	private String file;
	
	private PlayerGraphics(String name, String file) {
		this.name=name;
		this.file=file;
	}
	
	public String getFile() {
		return file;
	}
	
	public String getName() {
		return name;
	}
	
	
}

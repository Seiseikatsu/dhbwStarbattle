package com.starbattle.mapeditor.map;

public class Tile {

	private int x,y;
	private boolean autotile;
	private int autox,autoy;
	
	public Tile()
	{
		x=0;
		y=0;
	}
	
	public Tile(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public Tile(int x, int y, int ax, int ay)
	{
		this.autotile=true;
		this.autox=ax;
		this.autoy=ay;
		this.x=x;
		this.y=y;
	}
	
	
	public boolean isAutotile() {
		return autotile;
	}
	
	
	public boolean isSameTile(Tile tile)
	{
		if(tile.getX()==x&&tile.getY()==y)
		{
			return true;
		}
		return false;
	}
	
	public boolean isSameAutoTile(Tile tile)
	{
		if(!tile.isAutotile())
		{
			return false;
		}
		if(autox==tile.getAutox()&&autoy==tile.getAutoy())
		{
			return true;
		}
		return false;
	}
	
	public int getAutox() {
		return autox;
	}
	
	public int getAutoy() {
		return autoy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isEmpty()
	{
		if(x==0&&y==0)
		{
			return true;
		}
		return false;
	}

	
}

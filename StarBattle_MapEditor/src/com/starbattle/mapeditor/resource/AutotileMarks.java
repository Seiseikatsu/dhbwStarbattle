package com.starbattle.mapeditor.resource;

import java.util.ArrayList;

public class AutotileMarks {

	private ArrayList<Mark> autotile=new ArrayList<Mark>();
	
	public AutotileMarks()
	{
		
	}
	
	public boolean isAutotile(int x, int y)
	{
		for(Mark mark: autotile)
		{
			if(mark.xpos==x&&mark.ypos==y)
			{
				return true;
			}
		}
		return false;
	}
	
	public void addAutotile(int x, int y)
	{
		Mark mark=new Mark();
		mark.xpos=x;
		mark.ypos=y;
		autotile.add(mark);
	}
	
	private class Mark {
		public int xpos;
		public int ypos;
	}
	
}

package com.starbattle.mapeditor.gui.control;

import com.starbattle.mapeditor.gui.components.PlaceToolComponent;

public class ToolSelection {

	
	private int seletcedTool;
	private boolean decorationTools;
	
	public ToolSelection()
	{
		
	}
	
	public void setSeletcedTool(int seletcedTool, boolean deco) {
		this.seletcedTool = seletcedTool;
		this.decorationTools=deco;
	}

	public boolean isDecorationTools() {
		return decorationTools;
	}
	
	public boolean isPaintTool()
	{
		return seletcedTool==PlaceToolComponent.TOOL_PENCIL;
	}
	
	public boolean isEraseTool()
	{
		return seletcedTool==PlaceToolComponent.TOOL_ERASER;
	}
	
	public boolean isSelectTool()
	{
		return seletcedTool==PlaceToolComponent.TOOL_SELECT&&decorationTools;
	}
	
	public boolean isMoveBehindTool()
	{
		return seletcedTool==PlaceToolComponent.TOOL_MOVE_BEHIND&&decorationTools;
	}
	
	public boolean isRectangleTool()
	{
		return seletcedTool==PlaceToolComponent.TOOL_RECTANGLE&&decorationTools==false;
	}
	
	public boolean isFillTool()
	{
		return seletcedTool==PlaceToolComponent.TOOL_FILL&&decorationTools==false;
	}
	
	
	
	
}


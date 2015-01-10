package com.starbattle.ingame.settings;

public enum GraphicSettings
{
    Minimum(0),Standard(1),High(2); 
    
    private int settingsID;

    GraphicSettings(int id)
    {
        this.settingsID=id;
    }
    
    public int getSettingsID()
    {
        return settingsID;
    }
    
}

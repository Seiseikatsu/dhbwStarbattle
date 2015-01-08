package com.starbattle.ingame.settings;

public enum WindowResolution
{

    Res800x600(800,600),Res1024x768(1024,768),Res1280x800(1280,800),Res1280x960(1280,960),Res1440x900(1440,900),Res1680x1050(1680,1050);
    
    
    private int screenWidth,screenHeight;
    private String displayName;
    
    WindowResolution(int w, int h)
    {
        this.screenWidth=w;
        this.screenHeight=h;
        this.displayName=w+" x "+h;
    }
    
    public int getScreenHeight()
    {
        return screenHeight;
    }
    
    public int getScreenWidth()
    {
        return screenWidth;
    }
    
    public String getDisplayName()
    {
        return displayName;
    }
}

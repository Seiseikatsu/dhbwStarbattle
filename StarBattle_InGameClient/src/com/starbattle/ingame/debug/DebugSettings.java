package com.starbattle.ingame.debug;

import com.starbattle.ingame.settings.GameclientSettings;
import com.starbattle.ingame.settings.GraphicSettings;
import com.starbattle.ingame.settings.WindowResolution;

public class DebugSettings extends GameclientSettings
{

    
    public DebugSettings()
    {
        setFullscreenMode(true);
        setGraphicSettings(GraphicSettings.High);
        setMusicVolume(0.5f);
        setSoundOff(false);
        setSoundVolume(0.7f);
        setWindowResolution(WindowResolution.Res1024x768);
    }
    
}

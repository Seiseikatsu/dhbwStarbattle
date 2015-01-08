package com.starbattle.ingame.settings;


public class GameclientSettings
{
    
    
    private WindowResolution windowResolution;
    private GraphicSettings graphicSettings;
    private float musicVolume,soundVolume;
    private boolean soundOff,musicOff;
    private boolean fullscreenMode;
    
    public GameclientSettings()
    {
       
    }
   

    /**
     * Returns the {@link #musicOff}.
     * @return The musicOff.
     */
    public boolean isMusicOff()
    {
        return musicOff;
    }


    /**
     * Sets the {@link #musicOff}.
     * @param musicOff The new musicOff to set.
     */
    public void setMusicOff(boolean musicOff)
    {
        this.musicOff = musicOff;
    }


    /**
     * Returns the {@link #windowResolution}.
     * @return The windowResolution.
     */
    public WindowResolution getWindowResolution()
    {
        return windowResolution;
    }


    /**
     * Sets the {@link #windowResolution}.
     * @param windowResolution The new windowResolution to set.
     */
    public void setWindowResolution(WindowResolution windowResolution)
    {
        this.windowResolution = windowResolution;
    }


    /**
     * Returns the {@link #graphicSettings}.
     * @return The graphicSettings.
     */
    public GraphicSettings getGraphicSettings()
    {
        return graphicSettings;
    }

    /**
     * Sets the {@link #graphicSettings}.
     * @param graphicSettings The new graphicSettings to set.
     */
    public void setGraphicSettings(GraphicSettings graphicSettings)
    {
        this.graphicSettings = graphicSettings;
    }

    /**
     * Returns the {@link #musicVolume}.
     * @return The musicVolume.
     */
    public float getMusicVolume()
    {
        return musicVolume;
    }

    /**
     * Sets the {@link #musicVolume}.
     * @param musicVolume The new musicVolume to set.
     */
    public void setMusicVolume(float musicVolume)
    {
        this.musicVolume = musicVolume;
    }

    /**
     * Returns the {@link #soundVolume}.
     * @return The soundVolume.
     */
    public float getSoundVolume()
    {
        return soundVolume;
    }

    /**
     * Sets the {@link #soundVolume}.
     * @param soundVolume The new soundVolume to set.
     */
    public void setSoundVolume(float soundVolume)
    {
        this.soundVolume = soundVolume;
    }

    /**
     * Returns the {@link #soundOff}.
     * @return The soundOff.
     */
    public boolean isSoundOff()
    {
        return soundOff;
    }

    /**
     * Sets the {@link #soundOff}.
     * @param soundOff The new soundOff to set.
     */
    public void setSoundOff(boolean soundOff)
    {
        this.soundOff = soundOff;
    }

    /**
     * Returns the {@link #fullscreenMode}.
     * @return The fullscreenMode.
     */
    public boolean isFullscreenMode()
    {
        return fullscreenMode;
    }

    /**
     * Sets the {@link #fullscreenMode}.
     * @param fullscreenMode The new fullscreenMode to set.
     */
    public void setFullscreenMode(boolean fullscreenMode)
    {
        this.fullscreenMode = fullscreenMode;
    }
    
    
    
    

}

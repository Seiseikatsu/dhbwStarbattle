package com.starbattle.ingame.render;

import com.starbattle.ingame.settings.GraphicSettings;

public class RenderSettings
{

    private boolean renderParticles;
    private boolean vSync;
    private boolean smoothDeltas;
    private int maxParticles;
    private int targetFPS;

    public RenderSettings(GraphicSettings graphicSettings)
    {
        switch (graphicSettings)
        {
            case Minimum:
                //Minimum Settings
                targetFPS = 30;
                renderParticles=false;
                vSync=false;
                smoothDeltas=false;
                break;
            case Standard:
                //Standard Settings    
                targetFPS = 60;
                maxParticles=100;
                renderParticles=true;     
                vSync=false;
                smoothDeltas=false;
                break;
            case High:
                //High Settings   
                targetFPS = 0;
                maxParticles=Integer.MAX_VALUE;
                renderParticles=true;
                vSync=true;
                smoothDeltas=true;
                break;
        }
    }



    public int getTargetFPS()
    {
        return targetFPS;
    }

    public int getMaxParticles()
    {
        return maxParticles;
    }
    
    public boolean isRenderParticles()
    {
        return renderParticles;
    }
    
    public boolean isVSync()
    {
        return vSync;
    }
    
    public boolean isSmoothDeltas()
    {
        return smoothDeltas;
    }

}

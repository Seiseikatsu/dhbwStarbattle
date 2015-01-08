package com.starbattle.ingame.game.particles;

import java.io.IOException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ParticleEffect
{
    private static final String ipath="com/starbattle/ingame/game/particles/effects/images/";
    private static final String epath="com/starbattle/ingame/game/particles/effects/";
    private ParticleSystem psystem;
    private ParticleEmitter emitter;
    
    public ParticleEffect(String image, String xml)
    {
        psystem=new ParticleSystem(ipath+image);
        try
        {
            emitter=ParticleIO.loadEmitter(epath+xml+".xml");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void spawnEffect(float x, float y)
    {
        emitter.setEnabled(true);
        ParticleSystem system;
        try
        {
            system = psystem.duplicate();
            system.addEmitter(emitter);
            system.setPosition(x, y);
        }
        catch (SlickException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }           
    }

    public void update(int delta)
    {
        psystem.update(delta);
    }
    
    public void render()
    {
        psystem.render();
    }
}

package com.starbattle.ingame.game.particles;

import java.util.HashMap;

public class ParticleContainer
{
   
  
    private HashMap<String,ParticleEffect> effects=new  HashMap<String,ParticleEffect>();
    
    public ParticleContainer()
    {
        effects.put("Splash", new ParticleEffect("particle-cyan.png", "TestExplosion"));
    
    }
    
   
    
  
    public void render()
    {
        for(ParticleEffect effect: effects.values())
        {
            effect.render();
        }
    }
    
    public void update(int delta)
    {
        for(ParticleEffect effect: effects.values())
        {
            effect.update(delta);
        }
    }
}

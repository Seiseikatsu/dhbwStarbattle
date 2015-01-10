package com.starbattle.ingame.game.particles;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ParticleContainer
{
    
    private HashMap<String,ParticleEffect> effects=new  HashMap<String,ParticleEffect>();
    
    public ParticleContainer()
    {
    	
        effects.put("Splash",new ParticleEffect( "test"));
        effects.put("Splash2",new ParticleEffect( "test2"));
        
    }
    
   
    public void spawnEffect(String name, float x, float y)
    {
    	ParticleEffect effect=effects.get(name);
    	effect.spawnEffect(x, y);
    }
  
    public void render(Graphics g)
    {
    	int count=0;
        for(ParticleEffect effect: effects.values())
        {
            count+=effect.render();
        }
        g.setColor(new Color(255,255,255));
        g.drawString("PEC: "+count,10,30);
    }
    
    public void update(int delta)
    {
        for(ParticleEffect effect: effects.values())
        {
            effect.update(delta);
        }
        
    }
}

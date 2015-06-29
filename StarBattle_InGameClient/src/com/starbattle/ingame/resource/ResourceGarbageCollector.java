package com.starbattle.ingame.resource;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;

import com.starbattle.ingame.resource.player.PlayerGraphicPart;
import com.starbattle.ingame.resource.player.PlayerGraphicResource;

public class ResourceGarbageCollector
{
    private List<Object> resources = new ArrayList<Object>();
    private OpenAlClear openAlClear;

    public ResourceGarbageCollector()
    {
        openAlClear = new OpenAlClear();
    }

    public void collect(Object object)
    {
        resources.add(object);
    }

    public void tidyUp() throws SlickException
    {
        for (Object object : resources)
        {
            if (object instanceof Sound)
            {
                clear((Sound)object);
            }
            else if (object instanceof Image)
            {
                clear((Image)object);
            }
            else if (object instanceof SpriteSheet)
            {
                clear((SpriteSheet)object);
            }
            else if (object instanceof PlayerGraphicResource)
            {
                clear((PlayerGraphicResource)object);
            }
        }
    }

    private void clear(PlayerGraphicResource resource) throws SlickException
    {
        clear(resource.getBodyPart(PlayerGraphicPart.BODY));
        clear(resource.getBodyPart(PlayerGraphicPart.LEFT_ARM));
        clear(resource.getBodyPart(PlayerGraphicPart.LEFT_FOOT));
        clear(resource.getBodyPart(PlayerGraphicPart.RIGHT_ARM));
        clear(resource.getBodyPart(PlayerGraphicPart.RIGHT_FOOT));
    }

    private void clear(Image image) throws SlickException
    {
        image.destroy();
    }

    private void clear(SpriteSheet sprite) throws SlickException
    {
        sprite.destroy();
    }

    private void clear(Sound sound)
    {
        sound.stop();
    }

    private void clear(Audio audio) throws SlickException
    {
        audio.stop();
        openAlClear.destroyOpenAL(audio);
    }
}

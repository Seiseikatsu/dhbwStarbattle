package com.starbattle.ingame.resource.player;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.resource.ResourceContainer;

public class PlayerGraphicResource {

	public final static String PATH = ResourceContainer.PATH+"chars/";

	private HashMap<PlayerGraphicPart, Image> parts = new HashMap<PlayerGraphicPart, Image>();

	public PlayerGraphicResource(String name) throws ResourceException {

		try {
			System.out.println("Loading Player Graphic: "+name);
			Image srcImage = new Image(PATH + name);
			createParts(srcImage);

		} catch (SlickException e) {
			e.printStackTrace();
			throw new ResourceException("Could not load PlayerGraphic \"" + name + "\"");
		}
	}

	private void createParts(Image srcImage) {

		Image body = srcImage.getSubImage(0, 0, 64, 96);
		parts.put(PlayerGraphicPart.BODY, body);

		PlayerGraphicPart[] prts = { PlayerGraphicPart.LEFT_ARM, PlayerGraphicPart.RIGHT_ARM,
				PlayerGraphicPart.LEFT_FOOT, PlayerGraphicPart.RIGHT_FOOT };
		for (int i = 0; i < 4; i++) {
			PlayerGraphicPart part = prts[i];
			int x = (i % 2) * 32;
			int y = (i / 2) * 48;
			Image partImage = srcImage.getSubImage(64 + x, y, 32, 48);
			parts.put(part, partImage);
		}
	}

	public Image getBodyPart(PlayerGraphicPart part) {
		return parts.get(part);
	}

}

package com.starbattle.ingame.render;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.ingame.resource.WeaponGraphics;

public class WeaponRender extends RenderResource{


	public WeaponRender(ResourceContainer resourceContainer) {
		super(resourceContainer);
	}
	


	/**
	 * Renders the given weapon on the hand location.
	 * Calcs angle correction and optimal rotation 
	 * for the weapon sprite.
	 * 
	 * 
	 * 
	 * @param g
	 * @param weaponGraphics
	 * @param angle
	 * @param handX
	 * @param handY
	 * @param mirrored
	 * @param firing 
	 */
	public void renderWeapon(Graphics g, WeaponGraphics weaponGraphics, float angle, float handX, float handY,
			boolean mirrored, boolean firing) {
		
		int sy=0;
		if(firing)
		{
			sy=1;
		}
		Image weapon = resourceContainer.getWeaponGraphics(weaponGraphics).getSprite(0, sy);

		float rx = weaponGraphics.getRotateX();
		float ry = weaponGraphics.getRotateY();

		float a = (float) Math.toDegrees(angle);

		if (mirrored) {
			a += 180;

		}

		if (a < 0) {
			a += 360;
		} else if (a > 360) {
			a -= 360;
		}

		if (mirrored) {
			if (a < 270 && a > 90) {
				mirrored = false;
				a -= 180;
			}
		} else {
			if (a < 270 && a > 90) {
				mirrored = true;
				a += 180;
			}
		}

		if (mirrored) {
			weapon = weapon.getFlippedCopy(true, false);
			handX -= weapon.getWidth() / 2;
			rx = weapon.getWidth() - rx;
		}

		handY -= weapon.getHeight() / 2;
		weapon.setCenterOfRotation(rx, ry);
		weapon.setRotation(a);
		weapon.draw(handX, handY);

	}
}

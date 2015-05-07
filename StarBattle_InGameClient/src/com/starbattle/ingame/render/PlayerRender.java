package com.starbattle.ingame.render;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.player.PlayerDisplay;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.resource.PlayerGraphics;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.ingame.resource.WeaponGraphics;
import com.starbattle.ingame.resource.player.PlayerGraphicPart;
import com.starbattle.ingame.resource.player.PlayerGraphicResource;

public class PlayerRender {

	private final static float ARMPIT_Y = -6;
	private final static float FOOTPIT_Y = 20;

	private final static float ARM_X_DIFFERENCE = 3;
	private final static float FOOT_X_DIFFERENCE = 4;

	private final static float ARMROTATION_X = 16;
	private final static float ARMROTATION_Y = 14;
	private final static float FOOTOTATION_X = 16;
	private final static float FOOTROTATION_Y = 14;

	private final static float BODY_WIDTH = 64;
	private final static float BODY_HEIGHT = 96;
	/*
	 * private final static float ARM_WIDTH = 32; private final static float
	 * ARM_HEIGHT = 48; private final static float FOOT_WIDTH = 32; private
	 * final static float FOOT_HEIGHT = 48;
	 */
	private final static float MAX_LEG_ANGLE = 120;
	private final static float MAX_ARM_ANGLE = 180;
	
	private final static float WEAPON_HAND_DIFF=20;

	private ResourceContainer resourceContainer;
	private WeaponRender weaponRender;

	public PlayerRender(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
		weaponRender = new WeaponRender(resourceContainer);
	}

	private float bodyAngle;
	private boolean mirrored;
	private PlayerGraphicResource resource;
	private float handX, handY;

	public void render(Graphics g, PlayerObject player, Viewport viewport) {
		Location location = viewport.getScreenLocation(player.getLocation());
		PlayerDisplay display = player.getDisplay();
		if(!display.isVisible())
		{
			return;
		}
		PlayerGraphics graphics = display.getGraphic();
		float weaponAngle=player.getWeaponAngle();
		render(g, location, graphics, WeaponGraphics.PLASMA_GUN, display,weaponAngle);
	}
	

	public void render(Graphics g, Location location, PlayerGraphics graphics, WeaponGraphics weaponGraphics,
			PlayerDisplay display, float weaponAngle) {

		float[] angles = display.getRotation();
		boolean mirrored = display.isLookingLeft();
	
		float xpos = location.getXpos();		
		float ypos = location.getYpos()-graphics.getDeltaY();

		resource = resourceContainer.getPlayerGraphics(graphics);

		// get body image
		Image body = resource.getBodyPart(PlayerGraphicPart.BODY);
		if (mirrored) {
			body = body.getFlippedCopy(true, false);
		}

		this.mirrored = mirrored;
		// draw centered

		// get player rotation angle
		float lFootAnlge = angles[0];
		float lArmAngle = angles[1];
		float rFootAnlge = angles[2];
		float rArmAngle = angles[3];
		bodyAngle = angles[4];

		// calc body
		body.setCenterOfRotation(BODY_WIDTH / 2, BODY_HEIGHT / 2);
		body.setRotation(bodyAngle);

		// draw right arm and foot (behind body)
		drawBodyPart(g, PlayerGraphicPart.RIGHT_FOOT, xpos, ypos, rFootAnlge);
		drawBodyPart(g, PlayerGraphicPart.RIGHT_ARM, xpos, ypos, rArmAngle);

		// draw body
		body.drawCentered(xpos, ypos);

		// draw left arm and foot (infront body)
		drawBodyPart(g, PlayerGraphicPart.LEFT_FOOT, xpos, ypos, lFootAnlge);
		drawBodyPart(g, PlayerGraphicPart.LEFT_ARM, xpos, ypos, lArmAngle);

		// draw weapon
		boolean firing=display.isWeaponIsFiring();
		weaponRender.renderWeapon(g, weaponGraphics, weaponAngle, handX, handY, mirrored,firing);
		
	}

	private float getNormalizedAngle(float angle, float max) {
		if (angle > max) {
			angle = max;
		} else if (angle < -max) {
			angle = -max;
		}
		return angle + bodyAngle;
	}

	private void drawBodyPart(Graphics g, PlayerGraphicPart part, float x, float y, float partAnlge) {
		float pitPos = 0;
		float rotationPosX = 0;
		float rotationPosY = 0;
		float xDiff = 0;
		float anlgeMax = 0;

		boolean isArm=false;
		// get image
		Image partImage = resource.getBodyPart(part);
		if (mirrored) {
			partImage = partImage.getFlippedCopy(true, false);
		}

		// calc pit and rotation offset for pivot rotation
		if (part == PlayerGraphicPart.LEFT_ARM || part == PlayerGraphicPart.RIGHT_ARM) {
			// arm
			pitPos = ARMPIT_Y;
			rotationPosX = ARMROTATION_X;
			rotationPosY = ARMROTATION_Y;
			xDiff = ARM_X_DIFFERENCE;
			anlgeMax = MAX_ARM_ANGLE;
			if (part == PlayerGraphicPart.LEFT_ARM) {
				xDiff *= -1;
			}
			isArm=true;

		} else {
			// foot
			pitPos = FOOTPIT_Y;
			rotationPosX = FOOTOTATION_X;
			rotationPosY = FOOTROTATION_Y;
			xDiff = FOOT_X_DIFFERENCE;
			anlgeMax = MAX_LEG_ANGLE;
			if (part == PlayerGraphicPart.LEFT_FOOT) {
				xDiff *= -1;
			}
		}
		partAnlge = getNormalizedAngle(partAnlge, anlgeMax);

		float a = (float) Math.toRadians(bodyAngle + 90);
		// get part pit angle difference in position to body
		float xpos = x + (float) (Math.cos(a) * pitPos);
		float ypos = y + (float) (Math.sin(a) * pitPos);

		// change pivotpos by horizontal difference

		float normalAngle = (float) (a + Math.PI / 2);
		float xDiffX = (float) (Math.cos(normalAngle) * xDiff);
		float xDiffY = (float) (Math.sin(normalAngle) * xDiff);
		xpos -= xDiffX;
		ypos -= xDiffY;

		partImage.setCenterOfRotation(rotationPosX, rotationPosY);
		partImage.setRotation(partAnlge);
		float px = xpos - rotationPosX;
		float py = ypos - rotationPosY;
		
		partImage.draw(px, py);

		if(isArm)
		{
			//calc hand position
			a=(float) Math.toRadians(partAnlge+90);
			handX=(float) (px+Math.cos(a)*WEAPON_HAND_DIFF);
			handY=(float) (py+Math.sin(a)*WEAPON_HAND_DIFF);
			
		}
	}

}

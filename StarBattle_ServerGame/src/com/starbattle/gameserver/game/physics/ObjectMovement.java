package com.starbattle.gameserver.game.physics;

import com.starbattle.gameserver.map.collision.CollisionDetection;

public class ObjectMovement {

	private Location location;
	private ObjectGravity gravity;
	private boolean gravityActive = true;
	private CollisionDetection collisionDetection;
	private boolean isFacingLeft;

	// movement speed changes for client info (to run everything smooth)
	private float movementX, movementY;
	private MovementListener movementListener;

	public ObjectMovement(CollisionDetection collisionDetection) {
		this.collisionDetection = collisionDetection;
		location = new Location();
		gravity = new ObjectGravity(this);
		gravity.startFalling();
	}

	public void setMovementListener(MovementListener movementListener) {
		this.movementListener = movementListener;
	}

	public void setGravityMode(boolean active) {
		gravityActive = active;
		// reset gravity
		gravity.cancelMovement();

	}

	public void update(float delta) {
		// update input movement

		// TODO: check ground collision for gravity starting
		if (gravityActive) {
			// check when not falling
			if (!gravity.isInAir()) {
				// object can stand on ground?
				Location newLocation = location.copy();
				newLocation.moveY(0.3f);

				if (!collisionDetection.canStand(newLocation, objectHeight)) {
					gravity.startFalling();
				}
			}
		}

		// update gravity
		gravity.update(delta);
	}

	private float objectHeight = 0.55f;
	private float objectWidth = 0.3f;

	public void verticalMovement(float yDelta) {
		// TODO: Check map collision
		Location newLocation = location.copy();
		newLocation.moveY(yDelta);
		movementY = yDelta;
		if (yDelta > 0) {
			// TODO: on falling down, stop gravity on ground hit

			// => ground hit , stop falling and set to an even y value (example:
			// 0.987889 to 1)
			if (collisionDetection.canLand(location, newLocation, objectHeight)) {
				newLocation.flatY();
				newLocation.moveY(objectHeight / 2);
				movementY = 0;
				gravity.cancelMovement();
				if (movementListener != null) {
					movementListener.landedFromJumping();
				}
			}

		} else {
			// TODO: on flying/jumping up check ceiling hits

			// => hit ceiling, cancel jump when gravity mode is on, and dont
			// change to new y value

			if (collisionDetection.cantMoveUp(newLocation, objectHeight)) {
				gravity.cancelMovement();
				return;
			}

		}
		// update y locationwwwwwwwwww
		location.moveTo(newLocation);
	}

	public void horizontalMovement(float xDelta) {
		movementX = xDelta;
		if (xDelta > 0) {
			isFacingLeft = false;
		} else {
			isFacingLeft = true;
		}
		Location newLocation = location.copy();
		newLocation.moveX(xDelta);

		if (!collisionDetection.cantMove(location, newLocation, objectWidth, objectHeight)) {
			location.moveTo(newLocation);
		} else {
			movementX = 0;
		}
	}

	public ObjectGravity getGravity() {
		return gravity;
	}

	public Location getLocation() {
		return location;
	}

	public void teleport(Location l) {
		l.moveY(-objectHeight);
		this.location.moveTo(l);
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public void resetMovementInfo() {
		movementX = 0;
		movementY = 0;
	}

	public float getMovementX() {
		return movementX;
	}

	public float getMovementY() {
		return movementY;
	}

	public boolean objectJumped() {
		return gravity.isObjectJumped();
	}

	public void setGravity(float gravity2) {
		this.gravity.setG_factor(gravity2);
	}

}

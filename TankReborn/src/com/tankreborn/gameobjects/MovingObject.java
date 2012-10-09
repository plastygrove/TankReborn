package com.tankreborn.gameobjects;

public interface MovingObject extends GameObject {
	public static enum DIRECTION {
		UP, DOWN, LEFT, RIGHT
	}

	public float getSpeedMultiplier();

	public void setSpeedMultiplier(float speedMultipler);

	/**
	 * Give move instruction to object
	 * 
	 * @param dir
	 *            Direction to move. Ignored for bullet
	 * @param delta
	 */
	public void move(DIRECTION dir, float delta);
}

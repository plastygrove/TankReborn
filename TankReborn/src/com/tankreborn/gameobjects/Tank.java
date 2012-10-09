package com.tankreborn.gameobjects;

import java.util.UUID;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Tank implements MovingObject {

	private Image image;
	private Vector2f position;
	private float speed = 0.2f;
	private float speedMultiplier = 1.0f;
	private int maxBullets = 20;
	private int currentBullets = 0;
	private DIRECTION currentDirection = DIRECTION.UP;
	public UUID id;

	public Tank(Image image, Vector2f position, float speedMultiplier) {
		this.image = image;
		this.position = position;
		this.speedMultiplier = speedMultiplier;
		id = UUID.randomUUID();
	}

	public Tank() throws SlickException {
		image = new Image("data/images/tank.png");
		position = new Vector2f(340, 540);
	}

	public float getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(float speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	@Override
	public void render(Graphics g) {
		image.draw(position.x, position.y);
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	/**
	 * Specify the direction to move
	 * 
	 * @param dir
	 *            Tank.DIRECTION - the direction to move
	 * @param distance
	 *            Number of paces to move, will move default pace if 0 or negative
	 */
	public void move(DIRECTION dir, float delta) {
		float distance = speed * speedMultiplier * delta;
		currentDirection = dir;
		switch (currentDirection) {
		case UP:
			position.y -= distance;
			break;
		case DOWN:
			position.y += distance;
			break;
		case LEFT:
			position.x -= distance;
			break;
		case RIGHT:
			position.x += distance;
			break;
		}

	}

	public Bullet fire() throws SlickException {
		if (currentBullets >= maxBullets)
			return null;

		Bullet bullet = new Bullet(this);
		currentBullets++;
		return bullet;

	}

	public int getMaxBullets() {
		return maxBullets;
	}

	public void setMaxBullets(int maxBullets) {
		this.maxBullets = maxBullets;
	}

	public DIRECTION getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(DIRECTION currentDirection) {
		this.currentDirection = currentDirection;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(!(obj instanceof Tank))
			return false;
		
		Tank that = (Tank) obj;
		return this.id.equals(that.id);
	}

}

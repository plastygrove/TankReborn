package com.tankreborn.gameobjects;

import java.util.UUID;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Bullet implements MovingObject{

	private Image image;
	private Vector2f position;
	private DIRECTION direction;
	private float speed = 0.5f;
	private float speedMultiplier = 1.0f;
	private Tank parent;

	public UUID id;
	
	public Bullet(Tank tank) throws SlickException{
		parent = tank;
		image = new Image("data/images/bullet.png");
		position = new Vector2f(tank.getPosition().x, tank.getPosition().y);
		direction = tank.getCurrentDirection();
		id = UUID.randomUUID();
	}
	
	@Override
	public void render(Graphics g) {
		image.draw(position.x, position.y, 1.5f);
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	@Override
	public float getSpeedMultiplier() {
		return speedMultiplier;
	}

	@Override
	public void setSpeedMultiplier(float speedMultipler) {
		this.speedMultiplier = speedMultipler;
	}

	@Override
	public void move(DIRECTION dir, float delta) {
		//dir is not used for bullet
		float distance = speed * speedMultiplier * delta;
		switch (direction) {
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

	public void fire(DIRECTION currentDirection) {
		// TODO Auto-generated method stub
		
	}

	public Tank getParent() {
		return parent;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(!(obj instanceof Bullet))
			return false;
		
		Bullet that = (Bullet) obj;
		return this.id.equals(that.id);
	}
}

package com.tankreborn.gameobjects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.tankreborn.helpers.Constants.DIRECTION;
import com.tankreborn.helpers.Util;

public class Tank extends GameObject {

	private int maxBullets = 20;
	private int currentBullets = 0;
	
	private DIRECTION currentDirection;
	private float bulletSpeed = 1.0f;

	public Tank(World world, Image image, Vec2 position, float speedMultiplier) {
		this.image = image;
		this.position = position;
		this.speedMultiplier = speedMultiplier;
		boundingHeight = Util.getInstance().pixelsToMetres(image.getHeight());
		boundingWidth = Util.getInstance().pixelsToMetres(image.getWidth());

		initialize(world);
		currentDirection=DIRECTION.UP;
		
	}

	public Tank(World world) throws SlickException {
		image = new Image("data/images/tank.png");
		position = new Vec2(0, -5);
		boundingHeight = Util.getInstance().pixelsToMetres(image.getHeight());
		boundingWidth = Util.getInstance().pixelsToMetres(image.getWidth());

		initialize(world);
		currentDirection=DIRECTION.UP;
	}

	public Bullet fire(World world) throws SlickException {
		if (currentBullets >= maxBullets)
			return null;

		Bullet bullet = new Bullet(world, this);
		bullet.setSpeedMultiplier(2.0f);
		bullet.applyLinearVelocity(getBulletVelocity());
		currentBullets++;
		return bullet;

	}
	
	private Vec2 getBulletVelocity(){
		Vec2 bulletVelocity = new Vec2();
		switch(currentDirection){
		case LEFT:
			bulletVelocity.x = -bulletSpeed;
			break;
		case RIGHT:
			bulletVelocity.x = bulletSpeed;
			break;
		case UP:
			bulletVelocity.y = bulletSpeed;
			break;
		case DOWN:
			bulletVelocity.y = -bulletSpeed;
			break;
		}
		
		return bulletVelocity;
	}

	public int getMaxBullets() {
		return maxBullets;
	}

	public void setMaxBullets(int maxBullets) {
		this.maxBullets = maxBullets;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof Tank))
			return false;

		Tank that = (Tank) obj;
		return this.id.equals(that.id);
	}


	@Override
	protected BodyDef getBodyDef() {
		BodyDef bd = new BodyDef();
		bd.position = position;
		bd.type = BodyType.DYNAMIC;
		return bd;
	}

	@Override
	protected FixtureDef getFixtureDef() {
		FixtureDef fd = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(boundingWidth / 2, boundingHeight / 2);
		fd.shape = shape;
		fd.isSensor = true;//Don't perform collisions, only detect them
		return fd;
	}

	public void applyLinearVelocity(Vec2 velocity) {// TODO Fix this
		body.setLinearVelocity(velocity.mul(speedMultiplier));
	}

	public DIRECTION getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(DIRECTION currentDirection) {
		this.currentDirection = currentDirection;
	}

}

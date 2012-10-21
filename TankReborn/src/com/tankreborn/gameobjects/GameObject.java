package com.tankreborn.gameobjects;

import java.util.UUID;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import com.tankreborn.helpers.Util;


public abstract class GameObject implements Displayable, Physical {
	protected Vec2 position;
	protected Image image;
	protected Body body;
	public float boundingWidth;
	public float boundingHeight;
	protected float speedMultiplier=1.0f;
	public UUID id;

	protected abstract BodyDef getBodyDef();

	protected abstract FixtureDef getFixtureDef();
	
	protected void initialize(World world){
		initializeId();
		initializeBody(world);
		initializeFixtures(world);
		initializeUserData(id.toString());
	}
	
	protected void initializeBody(World world){
		body = world.createBody(getBodyDef());
	}
	
	protected void initializeFixtures(World world){
		body.createFixture(getFixtureDef());
	}
	
	protected void initializeUserData(Object obj){
		body.setUserData(obj);
	}
	
	protected void initializeId(){
		id = UUID.randomUUID();
	}
	
	@Override
	public void setPosition(Vec2 position){
		this.position = position;
		body.setTransform(position, body.getAngle());
	}
	
	@Override
	public Vec2 getPosition(){
		return  position;
	}
	
	@Override
	public void update(){
		this.position = body.getPosition().clone();
	}
	
	public void applyLinearVelocity(Vec2 velocity){
		body.setLinearVelocity(velocity.mul(speedMultiplier));
	}
	
	@Override
	public void render(Graphics g, Vec2 offset) {
		Util util = Util.getInstance();
		Vec2 offsetPos = new Vec2(position.x + offset.x, position.y + offset.y);
		Vector2f slickPosition = util.boxToSlick(offsetPos);
		
		image.draw(slickPosition.x, slickPosition.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
//		if(!(obj instanceof GameObject))
//			return false;
		if(!obj.getClass().equals(this.getClass()))
			return false;
		
		
		GameObject that = (GameObject) obj;
		return this.id.equals(that.id);
	}
	
	public float getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(float speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

}

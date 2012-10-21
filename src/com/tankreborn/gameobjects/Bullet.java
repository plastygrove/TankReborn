package com.tankreborn.gameobjects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet extends GameObject{

	private Tank parent;

	public Bullet(World world, Tank tank) throws SlickException{
		parent = tank;
		image = new Image("data/images/bullet.png");
		position = new Vec2(tank.getPosition().x, tank.getPosition().y);
//		direction = tank.getCurrentDirection();
		initialize(world);
	}
	

	public Tank getParent() {
		return parent;
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
		shape.setAsBox(boundingWidth, boundingHeight);
		
		fd.shape = shape;
		fd.isSensor = true;
		return fd;
	}
	
}

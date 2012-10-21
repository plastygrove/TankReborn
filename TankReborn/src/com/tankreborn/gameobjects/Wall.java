package com.tankreborn.gameobjects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.tankreborn.helpers.Util;

public class Wall extends GameObject {

	private int strength = 2;// Number of hits to destroy completely
	public enum WALL_TYPE{
		BRICK, ICE, GRASS, BOUNDARY
	}
	private WALL_TYPE type;
	
	public Wall(World world, WALL_TYPE type, Vec2 position) throws SlickException {
		this.type = type;
		this.position = position;
		switch(type){
		case BRICK:
			image = new Image("data/images/wall_brick.png");
			break;
		default:
			
			break;
		}
		
		boundingWidth = Util.getInstance().pixelsToMetres(image.getWidth());
		boundingHeight = Util.getInstance().pixelsToMetres(image.getHeight());
		
		initialize(world);
	}

	public WALL_TYPE getType() {
		return type;
	}
	
	public int getStrength(){
		return strength;
	}
	
	public void setStrength(int strength){
		this.strength = strength;
	}
	
	public void destroy() throws SlickException{
		image.destroy();
		
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
		shape.setAsBox(boundingWidth/2, boundingHeight/2);
		fd.shape = shape;
		return fd;
	}

}

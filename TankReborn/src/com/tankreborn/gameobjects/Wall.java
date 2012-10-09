package com.tankreborn.gameobjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Wall implements GameObject {

	private Image image;
	private int strength = 2;// Number of hits to destroy completely
	private Vector2f position;
	public enum WALL_TYPE{
		BRICK, ICE, GRASS, BOUNDARY
	}
	private WALL_TYPE type;
	
	public Wall(WALL_TYPE type, Vector2f position) throws SlickException {
		this.type = type;
		this.position = position;
		switch(type){
		case BRICK:
			image = new Image("data/images/wall_brick.png");
			break;
		default:
			
			break;
		}
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

}

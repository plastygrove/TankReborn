package com.tankreborn.gameobjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public interface GameObject {
	public void render(Graphics g);

	public Vector2f getPosition();

	public void setPosition(Vector2f position);
}

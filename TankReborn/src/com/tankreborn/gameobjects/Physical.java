package com.tankreborn.gameobjects;

import org.jbox2d.common.Vec2;

public interface Physical {

	public void update();

	public void setPosition(Vec2 position);

	public Vec2 getPosition();

}

package com.tankreborn.gameobjects;

public interface Obstacle extends GameObject {
	public enum TYPE{
		BRICK, BORDER
	}
	
	public TYPE getType();

}

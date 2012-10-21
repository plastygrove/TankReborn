package com.tankreborn;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;

import com.tankreborn.gameobjects.GameObject;

public class Camera {
	private Vec2 position;
	private float viewWidth;
	private float viewHeight;
	private boolean cameraInvert = false;

	public Camera(Vec2 position, float viewWidth, float viewHeight) {
		this.position = position;
		this.viewHeight = viewHeight;
		this.viewWidth = viewWidth;
	}

	public void move(Vec2 displacement) {
		if (cameraInvert) {
			this.position.x -= displacement.x;
			this.position.y -= displacement.y;
		} else {
			this.position.x += displacement.x;
			this.position.y += displacement.y;
		}
	}

	public void render(Graphics g, List<GameObject> allObjects) {
		Vec2 offset = new Vec2(-position.x, -position.y);
		for (GameObject obj : allObjects) {
			if (shouldDisplay(obj))
				obj.render(g, offset);
		}
	}

	private boolean isInView(float x, float y) {
		float cameraLeft = position.x - viewWidth / 2;
		float cameraRight = position.x + viewWidth / 2;
		float cameraTop = position.y - viewHeight / 2;
		float cameraBottom = position.y + viewHeight / 2;

		if (x > cameraLeft && x < cameraRight && y > cameraTop && y < cameraBottom)
			return true;
		else
			return false;
	}

	private boolean shouldDisplay(GameObject obj) {
		boolean bDisp = false;
		Vec2 objPos = obj.getPosition();

		float objLeft = objPos.x - obj.boundingWidth / 2;
		float objRight = objPos.x + obj.boundingWidth / 2;
		float objTop = objPos.y - obj.boundingHeight / 2;
		float objBottom = objPos.y + obj.boundingHeight / 2;

		if (isInView(objLeft, objTop) || isInView(objRight, objTop) || isInView(objLeft, objBottom) || isInView(objRight, objBottom))
			bDisp = true;

		return bDisp;
	}

	public boolean isCameraInvert() {
		return cameraInvert;
	}

	public void setCameraInvert(boolean cameraInvert) {
		this.cameraInvert = cameraInvert;
	}

}

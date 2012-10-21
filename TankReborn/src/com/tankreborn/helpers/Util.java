package com.tankreborn.helpers;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Vector2f;

public class Util {
	public float metresToPixelsCF = 50;
	public float screenWidth;
	public float screenHeight;

	private static Util instance;

	public static Util getInstance() {
		if (instance == null)
			instance = new Util();

		return instance;
	}

	private Util(float metresToPixelsCF, float screenWidth, float screenHeight) {
		this.metresToPixelsCF = metresToPixelsCF;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	private Util() {
		this.metresToPixelsCF = 50;
		this.screenWidth = 800;
		this.screenHeight = 600;
	}

	public Vector2f boxToSlick(Vec2 boxVec) {
		Vector2f slickVec = new Vector2f();
		slickVec.x = boxVec.x * metresToPixelsCF + screenWidth / 2;
		slickVec.y = screenHeight / 2 - boxVec.y * metresToPixelsCF;
		return slickVec;
	}

	public Vector2f boxToSlick(Vec2 boxVec, float transX, float transY) {
		Vector2f slickVec = new Vector2f();
		slickVec.x = boxVec.x * metresToPixelsCF + screenWidth / 2 + metresToPixels(transX);
		slickVec.y = screenHeight / 2 - boxVec.y * metresToPixelsCF + metresToPixels(transY);
		return slickVec;
	}
	
	public Vector2f rectBoxToSlick(Vec2 boxVec, float rectWidth, float rectHeight){
		Vector2f slickVec = new Vector2f();
		slickVec.x = boxVec.x * metresToPixelsCF + screenWidth / 2 - metresToPixels(rectWidth)/2;
		slickVec.y = screenHeight / 2 - boxVec.y * metresToPixelsCF - metresToPixels(rectHeight)/2;
		return slickVec;
	}

	public Vec2 slickToBox(Vector2f slickVec) {
		Vec2 boxVec = new Vec2();
		boxVec.x = (slickVec.x - screenWidth / 2) / metresToPixelsCF;
		boxVec.y = (screenHeight / 2 - slickVec.y) / metresToPixelsCF;
		return boxVec;
	}

	public float pixelsToMetres(float pixels) {
		return pixels / metresToPixelsCF;
	}

	public float metresToPixels(float metres) {
		return metres * metresToPixelsCF;
	}

	public float getMetresToPixelsCF() {
		return metresToPixelsCF;
	}

	public void setMetresToPixelsCF(float metresToPixelsCF) {
		this.metresToPixelsCF = metresToPixelsCF;
	}

	public float getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}

}

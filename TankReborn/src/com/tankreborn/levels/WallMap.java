package com.tankreborn.levels;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.tankreborn.gameobjects.GameObject;
import com.tankreborn.gameobjects.Wall;
import com.tankreborn.gameobjects.Wall.WALL_TYPE;
import com.tankreborn.helpers.Util;

public class WallMap {
	private Wall[][] wallMatrix;
	
	public WallMap(World world, List<GameObject> allObjects, int[][] numMatrix, int wallWidth, int wallHeight, int maxInRow, int maxInCol) throws SlickException{
		wallMatrix = new Wall[wallWidth][wallHeight];
		generateWallMap(world, allObjects, numMatrix, wallWidth, wallHeight, maxInRow, maxInCol);
	}
	
	private void generateWallMap(World world, List<GameObject> allObjects, int[][] numMatrix, int wallWidth, int wallHeight, int maxInRow, int maxInCol) throws SlickException{
		//Uncomment this later. Commented it out for now because it's a nuisance in testing
		/*if(maxInRow*wallWidth > mapWidth || maxInCol*wallHeight>mapHeight){
			throw new SlickException("Map cannot fit on screen");
		}*/
		int xPos = -7;
		int yPos = 6;
		for(int i=0; i<numMatrix.length; i++){
			for(int j=0; j<numMatrix[i].length; j++){
				Wall newWall = null;
				switch(numMatrix[i][j]){
				case 0: //Blank
					
					break;
				case 1: //Brick wall
					newWall = new Wall(world, WALL_TYPE.BRICK, new Vec2(xPos+j*Util.getInstance().pixelsToMetres(wallWidth), yPos-i*Util.getInstance().pixelsToMetres(wallHeight)));
					allObjects.add(newWall);
					break;
				default:
					
					break;
				}
				wallMatrix[i][j] = newWall;
			}
		}
	}
	
//	public void render(Graphics g, HashMap<Integer, Integer> wallRenderMap) throws SlickException{
//		for (Integer i : wallRenderMap.keySet()) {
//			Wall wall = wallMatrix[i][wallRenderMap.get(i)];
//			
//			if(wall == null){
//				continue;
//			} else if(wall.getStrength() <= 0){
//				wall.destroy();
//				wallMatrix[i][wallRenderMap.get(i)] = null;//Free resources
//			} else {
////				wall.render(g);//TODO
//			}
//		}
//	}
//	
//	public void render(Graphics g){
//		for(int i=0; i<wallMatrix.length; i++){
//			for(int j=0; j<wallMatrix[i].length; j++){
//				Wall wall = wallMatrix[i][j];
//				if(wall!=null){
////					wall.render(g);//TODO
//				}
//			}
//		}
//	}

	public void add(List<GameObject> allObjects) {
		for (int i = 0; i < wallMatrix.length; i++) {
			for(int j=0; j<wallMatrix[i].length; j++){
				allObjects.add(wallMatrix[i][j]);
			}
			
		}
	}
	
	public void render(Graphics g, Vec2 offset){
		for (int i = 0; i < wallMatrix.length; i++) {
			for(int j=0; j<wallMatrix[i].length; j++){
				wallMatrix[i][j].render(g, offset);
			}
			
		}
	}
	
	
}

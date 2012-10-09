package com.tankreborn.levels;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.tankreborn.gameobjects.Wall;
import com.tankreborn.gameobjects.Wall.WALL_TYPE;

public class WallMap {
	private final int mapHeight=550;
	private final int mapWidth=750;
//	private int wallWidth;
//	private int wallHeight;
	private Wall[][] wallMatrix;
	
	public WallMap(int[][] numMatrix, int wallWidth, int wallHeight, int maxInRow, int maxInCol) throws SlickException{
		wallMatrix = new Wall[wallWidth][wallHeight];
		generateWallMap(numMatrix, wallWidth, wallHeight, maxInRow, maxInCol);
	}
	
	private void generateWallMap(int[][] numMatrix, int wallWidth, int wallHeight, int maxInRow, int maxInCol) throws SlickException{
		//Uncomment this later. Commented it out for now because it's a nuisance in testing
		/*if(maxInRow*wallWidth > mapWidth || maxInCol*wallHeight>mapHeight){
			throw new SlickException("Map cannot fit on screen");
		}*/
		int xPos = 40;
		int yPos = 40;
		for(int i=0; i<numMatrix.length; i++){
			for(int j=0; j<numMatrix[i].length; j++){
				Wall newWall = null;
				switch(numMatrix[i][j]){
				case 0: //Blank
					
					break;
				case 1: //Brick wall
					newWall = new Wall(WALL_TYPE.BRICK, new Vector2f(xPos+j*wallWidth, yPos+i*wallHeight));
					break;
				default:
					
					break;
				}
				wallMatrix[i][j] = newWall;
			}
		}
	}
	
	public void render(Graphics g, HashMap<Integer, Integer> wallRenderMap) throws SlickException{
		for (Integer i : wallRenderMap.keySet()) {
			Wall wall = wallMatrix[i][wallRenderMap.get(i)];
			
			if(wall == null){
				continue;
			} else if(wall.getStrength() <= 0){
				wall.destroy();
				wallMatrix[i][wallRenderMap.get(i)] = null;//Free resources
			} else {
				wall.render(g);
			}
		}
	}
	
	public void render(Graphics g){
		for(int i=0; i<wallMatrix.length; i++){
			for(int j=0; j<wallMatrix[i].length; j++){
				Wall wall = wallMatrix[i][j];
				if(wall!=null){
					wall.render(g);
				}
			}
		}
	}
	
	
}

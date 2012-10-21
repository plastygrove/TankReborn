package com.tankreborn.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.SlickException;

import com.tankreborn.gameobjects.GameObject;
import com.tankreborn.gameobjects.Tank;

public class Level {
	private WallMap wallMap;
	private Tank[] playerTanks;
	private Tank[] enemyTanks;
	private List<GameObject> allObjects;
	private World world;

	private Level(World world, WallMap wallMap, List<GameObject> allObj) {
		this.allObjects = allObj;
		this.wallMap = wallMap;
		this.world = world;
	};

	public static Level loadLevel(World world, String path, List<GameObject> allObjects) throws SlickException {
		HashMap<String, Object> optionsCache = parseFile(world, allObjects, path);
		Level level = new Level(world, (WallMap)optionsCache.get("wall_map"), allObjects);
		return level;
	}

	private static HashMap<String, Object> parseFile(World world, List<GameObject> allObjects, String path) throws SlickException {
		HashMap<String, Object> options = new HashMap<>();
		BufferedReader br;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			while (br.ready()) {
				String line = br.readLine();
				if (line == null) {
					break;
				} else if (line.startsWith("#")) {
					continue;
				}

				String[] vals = line.split(":");
				switch (vals[0]) {
				case "wall_map": {
					int maxInRow = Integer.parseInt(br.readLine());
					int maxInCol = Integer.parseInt(br.readLine());
					int wallHeight = Integer.parseInt(br.readLine());
					int wallWidth = Integer.parseInt(br.readLine());
					int[][] wallNumMatrix = new int[maxInRow][maxInCol];
					for (int i = 0; i < maxInRow; i++) {
						String mapLine = br.readLine();
						if(mapLine == null)
							break;
						String[] lineArray = mapLine.split(",");
						for (int j = 0; j < lineArray.length; j++) {
//							System.out.print(lineArray[j]);
							wallNumMatrix[i][j] = Integer.parseInt(lineArray[j]);
						}
//						System.out.println("\n");
					}
					WallMap loadedMap = new WallMap(world, allObjects, wallNumMatrix, wallWidth, wallHeight, maxInRow, maxInCol);
					options.put("wall_map", loadedMap);
				}
					break;
				case "enemy_on_screen":
					
					break;
				
				default:

					break;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			throw new SlickException("Couldn't find level file", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new SlickException("Error reading level file", e);
		}
		return options;
	}
	
	public WallMap getWallMap() {
		return wallMap;
	}

}

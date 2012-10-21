package com.tankreborn.gamestates;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tankreborn.Camera;
import com.tankreborn.PhysicsHandler;
import com.tankreborn.gameobjects.Bullet;
import com.tankreborn.gameobjects.GameObject;
import com.tankreborn.gameobjects.Tank;
import com.tankreborn.helpers.Constants;
import com.tankreborn.helpers.Debugger;
import com.tankreborn.helpers.Constants.DIRECTION;
import com.tankreborn.levels.Level;

public class GamePlayState extends BasicGameState {
	private int stateId;
	private Tank playerTank;
	// private Image background;
	private Debugger d;
	private int keyPressed;
	private List<Bullet> bullets;
	private boolean isActionKeyPressed = false;
	private float actionDelay = 0.0f;
	private Level level;
	private List<GameObject> allObjects;
	private World world;
	private Camera camera;
	private PhysicsHandler handler;

	public GamePlayState(int id) {
		stateId = id;
		bullets = new ArrayList<>();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		world = new World(new Vec2(0f,0f), false);
		allObjects = new ArrayList<>();
		camera = new Camera(new Vec2(0f, 0f), Constants.CAMERA_VIEW_WIDTH, Constants.CAMERA_VIEW_HEIGHT);
		camera.setCameraInvert(true);
		handler = new PhysicsHandler();
		playerTank = new Tank(world);
		playerTank.setSpeedMultiplier(1.0f);
		allObjects.add(playerTank);
		
		level = Level.loadLevel(world, "data/levels/level1.dat", allObjects);
		
		d = Debugger.getInstance();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		camera.render(g, allObjects);
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		/*
		 * TODO: slick recognises multiple keys by calling this method everytime a key is pressed. I can make it recognise simultaneous keys by adding more variables viz., keyPressed1, keyPressed2 etc
		 */
		d.addText("Key Pressed: " + key);
		keyPressed = key;
		isActionKeyPressed = (key == Input.KEY_SPACE);
	}

	@Override
	public void keyReleased(int key, char c) {
		if (key == keyPressed)
			keyPressed = -1;
		if (key == Input.KEY_SPACE){
			isActionKeyPressed = false;
			actionDelay = 0;//Discard whatever accumulated here so far
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		handler.simulate(world, allObjects, delta*.005f);
		
		Vec2 velocity = new Vec2();
		float speed = 1.0f;
		DIRECTION dir = null;
		switch (keyPressed) {
		case Input.KEY_UP:
			dir = DIRECTION.UP;
			velocity.y = speed;
			break;
		case Input.KEY_DOWN:
			dir=DIRECTION.DOWN;
			velocity.y = -speed;
			break;
		case Input.KEY_LEFT:
			dir=DIRECTION.LEFT;
			velocity.x = -speed;
			break;
		case Input.KEY_RIGHT:
			dir=DIRECTION.RIGHT;
			velocity.x = speed;
			break;
		case Input.KEY_SPACE:
			if (actionDelay>0 && actionDelay < 250)
				break;
			else {
				Bullet bullet = playerTank.fire(world);
				if (bullet != null){
					bullets.add(bullet);
					allObjects.add(bullet);
				}
				actionDelay = 0;//Reset counter after firing
			}
			break;
		default:

		}
		playerTank.applyLinearVelocity(velocity);
		if(dir!=null)
			playerTank.setCurrentDirection(dir);
		
		if (isActionKeyPressed)
			actionDelay += delta;
		updateBullets(container, game, delta);
	}

	private void updateBullets(GameContainer container, StateBasedGame game, int delta) {
		for (Bullet bullet : bullets) {
//			bullet.move(null, delta);
		}
	}

	@Override
	public int getID() {
		return stateId;
	}

}

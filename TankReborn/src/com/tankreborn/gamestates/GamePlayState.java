package com.tankreborn.gamestates;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tankreborn.gameobjects.Bullet;
import com.tankreborn.gameobjects.MovingObject.DIRECTION;
import com.tankreborn.gameobjects.Tank;
import com.tankreborn.helpers.Debugger;
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

	public GamePlayState(int id) {
		stateId = id;
		bullets = new ArrayList<>();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		level = Level.loadLevel("data/levels/level1.dat");
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		playerTank = new Tank();
		d = Debugger.getInstance();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		d.render(g);
		playerTank.render(g);
		for (Bullet bullet : bullets) {
			bullet.render(g);
		}
		level.render(g);
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

		switch (keyPressed) {
		case Input.KEY_UP:
			playerTank.move(DIRECTION.UP, delta);
			break;
		case Input.KEY_DOWN:
			playerTank.move(DIRECTION.DOWN, delta);
			break;
		case Input.KEY_LEFT:
			playerTank.move(DIRECTION.LEFT, delta);
			break;
		case Input.KEY_RIGHT:
			playerTank.move(DIRECTION.RIGHT, delta);
			break;
		case Input.KEY_SPACE:
			if (actionDelay>0 && actionDelay < 250)
				break;
			else {
				Bullet bullet = playerTank.fire();
				if (bullet != null)
					bullets.add(bullet);
				actionDelay = 0;//Reset counter after firing
			}
			break;
		default:

		}
		if (isActionKeyPressed)
			actionDelay += delta;
		updateBullets(container, game, delta);
	}

	private void updateBullets(GameContainer container, StateBasedGame game, int delta) {
		for (Bullet bullet : bullets) {
			bullet.move(null, delta);
		}
	}

	@Override
	public int getID() {
		return stateId;
	}

}

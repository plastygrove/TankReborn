package com.tankreborn;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.tankreborn.gamestates.GamePlayState;
import com.tankreborn.gamestates.MainMenuState;

public class TankReborn extends StateBasedGame {

	public final static int MAIN_MENU_STATE = 0;
	public final static int GAME_PLAY_STATE = 1;
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new TankReborn());
		app.setDisplayMode(800, 600, false);
		app.start();
	}
	
	public TankReborn() {
		super("Tank Reborn");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
//		addState(new MainMenuState(MAIN_MENU_STATE));
		addState(new GamePlayState(GAME_PLAY_STATE));
	}

}

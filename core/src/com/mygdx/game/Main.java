package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.PlayScreen.GameScreen;

public class Main extends Game {

	public static final int VIEWPORT_WIDTH = 380;
	public static final int VIEWPORT_HEIGHT = 180;
	public static final float PIXELS_PER_METRE = 100f;
	private GameScreen gameScreen;

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

	@Override
	public void render(){
		super.render();
	}

}

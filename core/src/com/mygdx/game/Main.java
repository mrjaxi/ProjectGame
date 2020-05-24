package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PlayScreen.GameScreen;

public class Main extends Game {

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;

	public static final String TITLE = "Escape From Durka";
	public static final float VERSION = 0.1f;
	public static final int V_WIDTH = 760;
	public static final int V_HEIGHT = 360;
	public static final float PIXELS_PER_METRE = 100f;

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PlayScreen.GameScreen;
import com.mygdx.game.PlayScreen.LoadingScreen;
import com.mygdx.game.PlayScreen.MainMenuGame;
import com.mygdx.game.PlayScreen.SplashScreen;

public class Main extends Game {

	public static final String TITLE = "The Durka";
	public static final float VERSION = 0.57f;
	public static final int V_WIDTH = 460;
	public static final int V_HEIGHT = 360;
	public static final float PIXELS_PER_METRE = 100f;
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 8;
	public static final short COINS = 8;
	public static final short DESTROYED_BIT = 16;

	public OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	public BitmapFont font24;
	public AssetManager assets;
	public LoadingScreen loadingScreen;
	public SplashScreen splashScreen;
	public MainMenuGame mainMenuGame;
	public GameScreen gameScreen;

	@Override
	public void create () {
		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		batch = new SpriteBatch();

		fonts();

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		mainMenuGame = new MainMenuGame(this);
		gameScreen = new GameScreen(this);

//		this.setScreen(loadingScreen);

//		this.setScreen(new SplashScreen(this));
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render(){
		super.render();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		font24.dispose();
		assets.dispose();
		loadingScreen.dispose();
		splashScreen.dispose();
		mainMenuGame.dispose();
		gameScreen.dispose();
	}

	public void fonts(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Piedra.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

		params.size = 24;
		params.color = Color.BLACK;
		font24 = generator.generateFont(params);
	}
}

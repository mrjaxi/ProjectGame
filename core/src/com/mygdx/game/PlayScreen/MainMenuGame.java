package com.mygdx.game.PlayScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Main;

public class MainMenuGame implements Screen {

    private Main app;
    private GameScreen gameScreen;

    private Stage stage;

    private ShapeRenderer shapeRenderer;

    public MainMenuGame(final Main main) {
        this.stage = new Stage(new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, gameScreen.camera));
        this.shapeRenderer = new ShapeRenderer();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

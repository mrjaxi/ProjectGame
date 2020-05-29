package com.mygdx.game.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Main;

public class LoadingScreen implements Screen {

    private final Main main;

    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(final Main main) {
        this.main = main;
        this.shapeRenderer = new ShapeRenderer();
    }

    private void queueAssets() {
        main.assets.load("img/loading.png", Texture.class);
        main.assets.load("User interface/plain-james-ui.atlas", TextureAtlas.class);
    }


    @Override
    public void show() {
        System.out.println("LOADING");
        shapeRenderer.setProjectionMatrix(main.camera.combined);
        this.progress = 0f;
        queueAssets();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, main.assets.getProgress(), 0.059f);
        if (main.assets.update() && progress >= main.assets.getProgress() - .00003f) {
            main.setScreen(main.splashScreen);
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(12/255f, 0/255.0f, 43/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(32, main.camera.viewportHeight / 2 - 8, main.camera.viewportWidth - 64, 7);

        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.rect(32, main.camera.viewportHeight / 2 - 8, progress * (main.camera.viewportWidth - 64), 7);
        shapeRenderer.end();
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
        shapeRenderer.dispose();
    }
}
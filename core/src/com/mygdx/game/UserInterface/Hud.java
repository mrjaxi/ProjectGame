package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;
    public float counter = 0;
    public static Integer intKey;
    public static Integer intCoin;
    public static String warning;
    private Main main;

    Label key;
    Label coin;
    Label warnings;

    public Hud(SpriteBatch spriteBatch, Main main){

        this.main = main;
        intKey = 0;
        intCoin = 0;

        viewport = new ExtendViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.bottom();
        table.setFillParent(true);

        key = new Label("Key: " + intKey, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        warnings = new Label(warning, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        coin = new Label("Coin: " + intCoin, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(key).expandX().padBottom(15);
        table.add(warnings).expandX().padBottom(15);
        table.add(coin).expandX().padBottom(15);
        table.row();

        stage.addActor(table);

    }

    public void update(float dt){
        key.setText("Key: " + intKey);
        counter = dt;
        if (dt < counter + 0.0005f) {
            warnings.setText(warning);
        }else {
            warning = "";
            warnings.setText(warning);
        }
        coin.setText("Coin: " + intCoin);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

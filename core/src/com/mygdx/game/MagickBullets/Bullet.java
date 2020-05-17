package com.mygdx.game.MagickBullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    private static final int SPEED = 500;
    public static final int DEFAULT_X = 40;
    private static Texture texture;

    float x, y;
    public boolean remove = false;

    public Bullet(float x){
        this.x = DEFAULT_X;
        this.x = x;

        texture = new Texture("bullet.png");
    }

    public void update(float delta){
        x += SPEED * delta;
        if (x > Gdx.graphics.getHeight()){
            remove = true;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }

}

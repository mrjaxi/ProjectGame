package com.mygdx.game.MyInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputProcessorOne implements InputProcessor {

    public InputProcessorOne(){}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Gdx.graphics.getWidth() / 2f * 0.5f < vector3.x && Gdx.graphics.getHeight() / 2f < vector3.y && player.body2d.getLinearVelocity().x <= 2) { player.body2d.applyLinearImpulse(new Vector2(0.15f, 0), player.body2d.getWorldCenter(), true); }
        if (Gdx.graphics.getWidth() / 2f * 0.5f > vector3.x && Gdx.graphics.getHeight() / 2f < vector3.y && player.body2d.getLinearVelocity().x >= -2) { player.body2d.applyLinearImpulse(new Vector2(-0.15f, 0), player.body2d.getWorldCenter(), true); }


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

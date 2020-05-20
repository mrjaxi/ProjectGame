package com.mygdx.game.MyInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Player.PlayerAdv;

public class InputProcessorOne implements InputProcessor {

    private PlayerAdv player;

    public InputProcessorOne(PlayerAdv player){
        this.player = player;
    }

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
//        if (Gdx.graphics.getWidth() / 2f * 0.5f < screenX && Gdx.graphics.getHeight() / 2f < screenY && player.body2d.getLinearVelocity().x <= 2) {
//            player.body2d.applyLinearImpulse(new Vector2(1f, 0), player.body2d.getWorldCenter(), true);
//        }
//        if (Gdx.graphics.getWidth() / 2f * 0.5f > screenX && Gdx.graphics.getHeight() / 2f < screenY && player.body2d.getLinearVelocity().x >= -2) {
//            player.body2d.applyLinearImpulse(new Vector2(-1f, 0), player.body2d.getWorldCenter(), true);
//        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Gdx.graphics.getWidth() / 2f * 0.5f < screenX && Gdx.graphics.getHeight() / 2f < screenY && player.body2d.getLinearVelocity().x <= 2) {
            player.body2d.applyLinearImpulse(new Vector2(1f, 0), player.body2d.getWorldCenter(), true);
        }
        if (Gdx.graphics.getWidth() / 2f * 0.5f > screenX && Gdx.graphics.getHeight() / 2f < screenY && player.body2d.getLinearVelocity().x >= -2) {
            player.body2d.applyLinearImpulse(new Vector2(-1f, 0), player.body2d.getWorldCenter(), true);
        }
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

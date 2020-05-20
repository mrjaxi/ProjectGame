package com.mygdx.game.MyInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Player.PlayerAdv;

public class InputProcessorTwo implements InputProcessor {

    private PlayerAdv player;

    public InputProcessorTwo(PlayerAdv player){
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
        while (Gdx.graphics.getHeight() / 2f > screenY && player.currentState != PlayerAdv.State.JUMPING){
            player.body2d.applyForceToCenter(0, 230f, true);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//
//        if (Gdx.graphics.getHeight() / 2f > screenY && player.currentState != PlayerAdv.State.JUMPING) {
//            player.body2d.applyForceToCenter(0, 230f, true);
//        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Gdx.graphics.getHeight() / 2f > screenY && player.currentState != PlayerAdv.State.JUMPING) {
            player.body2d.applyForceToCenter(0, 230f, true);
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

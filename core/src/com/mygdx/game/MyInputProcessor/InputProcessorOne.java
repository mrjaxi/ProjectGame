package com.mygdx.game.MyInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
        if ((Gdx.graphics.getWidth() / 2f) < screenX && player.currentState != PlayerAdv.State.JUMPING){
            player.body2d.applyForceToCenter(0, 160f, true);
        }
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

package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.PlayScreen.GameScreen;

public class PlayerAdv extends Sprite {

    public enum State {FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    private State previousState;
    private World world;
    public Body body2d;
    private TextureRegion region;
    private TextureRegion playerStand;
    /*
    Create object of Animation class
     */
    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerJump;

    private float stateTimer;
    private boolean runningRight;

    public PlayerAdv(World world, GameScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;

        /*
        Set variables params
         */
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 10, 16, 16));
        playerRun = new Animation<>(0.1f, frames);
        frames.clear();

        for (int i = 4; i < 6; i++){
            frames.add(new TextureRegion(getTexture(),i * 16, 10, 16, 16));
        }
        playerJump = new Animation<>(0.1f, frames);
        TextureRegion playerStand = new TextureRegion(getTexture(), 1, 10, 16, 16);
        definePlayer();
        setBounds(0, 0, 24 / Main.PIXELS_PER_METRE, 24 / Main.PIXELS_PER_METRE);
        setRegion(playerStand);
    }

    public void update(float dt){
        setPosition(body2d.getPosition().x - getWidth() / 2, body2d.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        switch (currentState){
            case FALLING:
            case JUMPING:
                region = playerJump.getKeyFrame(stateTimer);
                break;
            case STANDING:
                break;
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            default:
                region = playerStand;
        }
        if ((body2d.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }else if ((body2d.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return  region;

    }

    public State getState() {
        if (body2d.getLinearVelocity().y > 0 || (body2d.getLinearVelocity().y < 0 && previousState == State.JUMPING)){
            return State.JUMPING;
        }else if (body2d.getLinearVelocity().y < 0){
            return State.FALLING;
        }else if (body2d.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else {
            return State.STANDING;
        }
    }

    public void definePlayer(){
        BodyDef bodyDef = new BodyDef();
        /*
        Выставляем параметры для начальной позиции игрока по X и Y
         */
        bodyDef.position.set(64 / Main.PIXELS_PER_METRE, 150 / Main.PIXELS_PER_METRE);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body2d = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(7.5f / Main.PIXELS_PER_METRE, 10.3f / Main.PIXELS_PER_METRE);

        fixtureDef.shape = shape;
        body2d.createFixture(fixtureDef);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        body2d.setUserData("PlayerBody");
    }
}

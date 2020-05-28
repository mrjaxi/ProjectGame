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

    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerJump;

    private float stateTimer;
    private boolean runningRight;

    public PlayerAdv(World world, GameScreen screen){
        super(screen.getAtlas().findRegion("Ghost"));
        this.world = world;

        /*
        Set variables params
         */
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        /*
        Парсинг фреймов с текстуры
         */
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 64, -1, 60, 60));
        playerRun = new Animation<>(0.1f, frames);
        frames.clear();

        for (int i = 4; i < 6; i++){
            frames.add(new TextureRegion(getTexture(),i * 64, -1, 60, 60));
        }
        playerJump = new Animation<>(0.1f, frames);
        TextureRegion playerStand = new TextureRegion(getTexture(), 1, 8, 60, 60);
        definePlayer();
        setBounds(0, 0, 20 / Main.PIXELS_PER_METRE, 20 / Main.PIXELS_PER_METRE);
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
                /*
                По прыжку будет получать фреймы и перерисовывать их
                 */
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
        /*
        Условие для поворота персоонажа
         */
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
        bodyDef.position.set(500 / Main.PIXELS_PER_METRE, 200 / Main.PIXELS_PER_METRE);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body2d = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(7.7f / Main.PIXELS_PER_METRE, 7.6f / Main.PIXELS_PER_METRE);

        fixtureDef.filter.categoryBits = Main.PLAYER_BIT;
        fixtureDef.filter.maskBits = Main.DEFAULT_BIT | Main.COINS;

        fixtureDef.shape = shape;
//        fixtureDef.isSensor = true;
        body2d.createFixture(fixtureDef).setUserData("PlayerBody");;
    }
}

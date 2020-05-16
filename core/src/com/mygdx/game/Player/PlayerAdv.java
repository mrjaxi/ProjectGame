package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;
import com.mygdx.game.PlayScreen.GameScreen;

public class PlayerAdv extends Sprite {

    private World world;
    public Body body2d;

    public PlayerAdv(World world, GameScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        definePlayer();

        TextureRegion playerStand = new TextureRegion(getTexture(), 1, 11, 16, 16);
        setBounds(0, 0, 32 / Main.PIXELS_PER_METRE, 32 / Main.PIXELS_PER_METRE);
        setRegion(playerStand);
    }

    public void update(float ft){
        setPosition(body2d.getPosition().x - getWidth() / 2f, body2d.getPosition().y - getHeight() / 2f);
    }

    private void definePlayer() {
        BodyDef bdef = new BodyDef();
        /*
        Выставляем параметры для начальной позиции игрока по X и Y
         */
        bdef.position.set(50 / Main.PIXELS_PER_METRE, 180 / Main.PIXELS_PER_METRE);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body2d = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10 / Main.PIXELS_PER_METRE, 10 / Main.PIXELS_PER_METRE);
        fixtureDef.shape = shape;
        body2d.createFixture(fixtureDef);
    }
}

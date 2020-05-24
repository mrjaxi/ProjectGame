package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;

public class TestCollisionObject extends InteractiveTileObjects{

    public BodyDef bodyDef;
    public FixtureDef fdef;
    public PolygonShape shape;

    public TestCollisionObject(World world, TiledMap map) {
        super(world, map);
        bodyDef = new BodyDef();
        fdef = new FixtureDef();
        shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(110f / Main.PIXELS_PER_METRE, 170f/ Main.PIXELS_PER_METRE);
        body = world.createBody(bodyDef);
        shape.setAsBox(7f / Main.PIXELS_PER_METRE, 7f / Main.PIXELS_PER_METRE);
        fdef.shape = shape;
        body.createFixture(fdef);

    }
}

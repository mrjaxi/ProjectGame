package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;
import com.mygdx.game.PlayScreen.GameScreen;

public abstract class DynamicTileObjects extends Sprite {

    public World world;
    public TiledMapTile tile;
    public TiledMap tiledMap;
    public Rectangle bounds;
    public GameScreen screen;
    public Body body;

    public DynamicTileObjects(World world, TiledMap tiledMap, Rectangle bounds, GameScreen screen){
        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = bounds;
        this.screen = screen;
    }

    public abstract void update(float delta);

    public void defineDynamicBox(){
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / Main.PIXELS_PER_METRE,
                (bounds.getY() + bounds.getHeight() / 2) / Main.PIXELS_PER_METRE);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / Main.PIXELS_PER_METRE,
                bounds.getHeight() / 2 / Main.PIXELS_PER_METRE);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}

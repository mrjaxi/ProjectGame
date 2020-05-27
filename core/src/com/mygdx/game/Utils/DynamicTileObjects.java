package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;
import com.mygdx.game.PlayScreen.GameScreen;

public class DynamicTileObjects extends Sprite {

    public World world;
    protected TiledMapTile tile;
    private TiledMap tiledMap;
    protected Rectangle bounds;
    public Body body;
    private TextureRegion region;

    public DynamicTileObjects(World world, TiledMap tiledMap, Rectangle bounds, GameScreen screen){
        super(screen.getBox().findRegion("dynamic-box"));
        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = bounds;

        defineDynamicBox();

        region = new TextureRegion(getTexture(), 32, 32, 32, 32);
        setBounds(0, 0, 32 / Main.PIXELS_PER_METRE, 32 / Main.PIXELS_PER_METRE);
        setRegion(region);
    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth() / 2,body.getPosition().y - getHeight() / 2);
    }

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

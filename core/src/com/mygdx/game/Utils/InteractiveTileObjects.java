package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;

public abstract class InteractiveTileObjects {

    protected World world;
    protected TiledMapTile tile;
    private TiledMap tiledMap;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObjects(World world, TiledMap tiledMap, Rectangle bounds){
        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / Main.PIXELS_PER_METRE,
                (bounds.getY() + bounds.getHeight() / 2) / Main.PIXELS_PER_METRE);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / Main.PIXELS_PER_METRE,
                bounds.getHeight() / 2 / Main.PIXELS_PER_METRE);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void onHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCellDoor(){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(9);
        return layer.getCell((int)(body.getPosition().x * Main.PIXELS_PER_METRE / 16),
                (int)(body.getPosition().y * Main.PIXELS_PER_METRE / 16));
    }

    public TiledMapTileLayer.Cell getCellCoin(){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(7);
        return layer.getCell((int)(body.getPosition().x * Main.PIXELS_PER_METRE / 16),
                (int)(body.getPosition().y * Main.PIXELS_PER_METRE / 16));
    }

    public TiledMapTileLayer.Cell getCellKey(){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(7);
        return layer.getCell((int)(body.getPosition().x * Main.PIXELS_PER_METRE / 16),
                (int)(body.getPosition().y * Main.PIXELS_PER_METRE / 16));
    }
}

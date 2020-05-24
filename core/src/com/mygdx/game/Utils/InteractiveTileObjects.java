package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class InteractiveTileObjects {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Body body;

    public InteractiveTileObjects(World world, TiledMap map){
        this.world = world;
        this.map = map;
    }

}

package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;

public class CollisionObjectDoor extends InteractiveTileObjects{

    public CollisionObjectDoor(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Main.COINS);
    }

    @Override
    public void onHit() {
        setCategoryFilter(Main.DESTROYED_BIT);
        getCellDoor().setTile(getOpenedDoorPart1().getTile());
        getOverLayer().setTile(getOpenedDoorPart2().getTile());
    }
}

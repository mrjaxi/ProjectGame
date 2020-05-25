package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
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
        System.out.println("DESTROET BIT");
        setCategoryFilter(Main.DESTROYED_BIT);
        getCellDoor().setTile(null);
        System.out.println("Door Open");
    }
}

package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PlayScreen.GameScreen;
import com.mygdx.game.Main;

public class CollisionObjectCoin extends InteractiveTileObjects{



    public CollisionObjectCoin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Main.COINS);
    }

    @Override
    public void onHit() {
        System.out.println("DESTROET BIT");
        setCategoryFilter(Main.DESTROYED_BIT);
        getCellCoin().setTile(null);
        System.out.println("Plus One Coin");
    }
}

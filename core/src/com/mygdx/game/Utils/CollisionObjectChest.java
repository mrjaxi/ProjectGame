package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;
import com.mygdx.game.UserInterface.Hud;

public class CollisionObjectChest extends InteractiveTileObjects{

    public CollisionObjectChest(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Main.COINS);
    }

    @Override
    public void onHit() {
        setCategoryFilter(Main.DESTROYED_BIT);
        getOverLayerChest().setTile(null);
        getCellChest().setTile(null);
        Hud.intCoin += 250;
    }
}

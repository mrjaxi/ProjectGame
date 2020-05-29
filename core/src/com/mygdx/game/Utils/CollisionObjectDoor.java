package com.mygdx.game.Utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;
import com.mygdx.game.UserInterface.Hud;

public class CollisionObjectDoor extends InteractiveTileObjects{

    public CollisionObjectDoor(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Main.COINS);
    }

    @Override
    public void onHit() {
        if(Hud.intKey > 0){
            setCategoryFilter(Main.DESTROYED_BIT);
            getCellDoor().setTile(getOpenedDoorPart1().getTile());
            getOverLayerDoor().setTile(getOpenedDoorPart2().getTile());
            Hud.warning = "Door opened";
            Hud.intKey--;
        }
        else Hud.warning = "NeedKey";
    }
}

package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Main;
import com.mygdx.game.PlayScreen.GameScreen;

public class DynamicBody extends DynamicTileObjects {

    protected TiledMapTile tile;
    private TextureRegion region;

    public DynamicBody(World world, TiledMap map, Rectangle bounds, GameScreen screen){
        super(world, map, bounds, screen);

        defineDynamicBox();

        region = new TextureRegion(screen.getBox().findRegion("home16").getTexture(), 49, 115, 16, 16);
        setBounds(0, 0, 16 / Main.PIXELS_PER_METRE, 16 / Main.PIXELS_PER_METRE);
        setRegion(region);
    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth() / 2,body.getPosition().y - getHeight() / 2);
    }
}

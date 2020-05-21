package com.mygdx.game.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;
import com.mygdx.game.MyInputProcessor.InputProcessorOne;
import com.mygdx.game.Player.PlayerAdv;
import com.mygdx.game.Utils.TileMapObjects;


public class GameScreen implements Screen {

    public Main main;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Viewport viewport;
    private OrthographicCamera camera;

    private OrthogonalTiledMapRenderer renderer;
    private Vector3 vector3;
    public World world;
    private Box2DDebugRenderer b2dr;
    private PlayerAdv player;

    private InputMultiplexer inputMultiplexer;

    public GameScreen(Main main){
        this.main = main;
        atlas = new TextureAtlas("Mario.pack");
        batch = new SpriteBatch();
        batch.disableBlending();

        camera = new OrthographicCamera();

        viewport = new ExtendViewport(Main.VIEWPORT_WIDTH / Main.PIXELS_PER_METRE, Main.VIEWPORT_HEIGHT / Main.PIXELS_PER_METRE, camera);

        vector3 = new Vector3();

        /*
        Подгрузка карт
         */
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap map = tmxMapLoader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PIXELS_PER_METRE);

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        /*
        Инициализация мира
        new Vector() устанавливает направление гравитации в мире
         */
        world = new World(new Vector2(0, -9.8f), true);
        b2dr = new Box2DDebugRenderer();

        //---------------------------------------------------
        /*
        Переменные для инициализации и создания обводки для объектов
         */
//        BodyDef bodyDef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fDef = new FixtureDef();

        /*
        Сложная конструкция, которая позволяет идентифицировать все объекты на карте
        Эта хуйня с линиями зелеными вокруг объектов, поэтому закомментил
         */
//        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.getX() + rect.getWidth() / 2f) / Main.PIXELS_PER_METRE, (rect.getY() + rect.getHeight() / 2f) / Main.PIXELS_PER_METRE);
//
//            Body body = world.createBody(bodyDef);
//            shape.setAsBox(rect.getWidth() / 2 / Main.PIXELS_PER_METRE, rect.getHeight() / 2 / Main.PIXELS_PER_METRE);
//            fDef.shape = shape;
//            body.createFixture(fDef);
//        }

        //-----------------------------------------------------

        /*
        А это норм пацан, без линий делает, в Utils основной код
         */
        TileMapObjects.parseTileMapObject(map, world);

        /*
        Инициализируем игрока на карте
         */
        player = new PlayerAdv(world, this);

        InputProcessor inputOne = new InputProcessorOne(player);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputOne);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float dt){
        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        camera.position.x = camera.position.x + (player.body2d.getPosition().x * 1f - camera.position.x + 0.2f) * .3f;
        camera.position.y = camera.position.y + (player.body2d.getPosition().y * 1f - camera.position.y) * .5f;

        player.update(dt);

        camera.update();
        renderer.setView(camera);
    }

    private void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.currentState != PlayerAdv.State.JUMPING)
            player.body2d.applyForceToCenter(0, 230f, true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body2d.getLinearVelocity().x <= 2)
            player.body2d.applyLinearImpulse(new Vector2(0.15f, 0), player.body2d.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body2d.getLinearVelocity().x >= -2)
            player.body2d.applyLinearImpulse(new Vector2(-0.15f, 0), player.body2d.getWorldCenter(), true);

        if (Gdx.input.isTouched()){
            vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if ((Gdx.graphics.getWidth() / 4f) < vector3.x && (Gdx.graphics.getHeight() / 2f < vector3.y) && player.body2d.getLinearVelocity().x <= 2) { player.body2d.applyLinearImpulse(new Vector2(0.15f, 0), player.body2d.getWorldCenter(), true); }
            if ((Gdx.graphics.getWidth() / 4f) > vector3.x && (Gdx.graphics.getHeight() / 2f) < vector3.y && player.body2d.getLinearVelocity().x >= -2) { player.body2d.applyLinearImpulse(new Vector2(-0.15f, 0), player.body2d.getWorldCenter(), true); }
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(100/255.0f, 100/255.0f, 120/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        batch.setProjectionMatrix(camera.combined);
//        b2dr.render(world, camera.combined);

        batch.begin();
            player.draw(batch);
        batch.end();

        Gdx.app.log("GameScreen FPS", (1/delta) + "");
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        batch.dispose();
        b2dr.dispose();
    }

    public InputProcessor CustomInputProcessorOne(){

        return null;
    }
}
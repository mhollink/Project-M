package nl.icode4living.projectm.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import nl.icode4living.projectm.GdxGame;
import nl.icode4living.projectm.entities.Brick;
import nl.icode4living.projectm.entities.Coin;

public class MapLoader {

    private static MapLoader instance;

    public static MapLoader getInstance() {
        if(instance == null) instance = new MapLoader();
        return instance;
    }

    private TmxMapLoader tmxMapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private Box2DDebugRenderer box2DDebugRenderer;
    private World world;

    private MapLoader(){
        tmxMapLoader = new TmxMapLoader();
        box2DDebugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -10), true);
    }

    public void loadMap(String path) {
        world.dispose();
        world = new World(new Vector2(0, -10), true);

        tiledMap = tmxMapLoader.load(path);
        setOrthogonalTiledMapRenderer();

        createAllObjects();
    }

    private void createAllObjects() {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // GROUND
        for (MapObject object : MapLoader.getInstance().getTiledMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(
                    (rectangle.getX()+ rectangle.getWidth() / 2)  / GdxGame.PPM,
                    (rectangle.getY()+ rectangle.getHeight()/ 2)  / GdxGame.PPM);


            body = world.createBody(bodyDef);

            shape.setAsBox(
                    rectangle.getWidth() / 2 / GdxGame.PPM,
                    rectangle.getHeight()/ 2 / GdxGame.PPM);
            fixtureDef.shape = shape;

            body.createFixture(fixtureDef);
        }

        // MUSHROOM
        for (MapObject object : MapLoader.getInstance().getTiledMap().getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(
                    (rectangle.getX()+ rectangle.getWidth() / 2)  / GdxGame.PPM,
                    (rectangle.getY()+ rectangle.getHeight()/ 2)  / GdxGame.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(
                    rectangle.getWidth() / 2 / GdxGame.PPM,
                    rectangle.getHeight()/ 2 / GdxGame.PPM);            fixtureDef.shape = shape;

            body.createFixture(fixtureDef);
        }

        // PIPE
        for (MapObject object : MapLoader.getInstance().getTiledMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(
                    rectangle.getX()/ GdxGame.PPM + rectangle.getWidth() / 2  / GdxGame.PPM,
                    rectangle.getY()/ GdxGame.PPM + rectangle.getHeight()/ 2  / GdxGame.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(
                    rectangle.getWidth() / 2 / GdxGame.PPM,
                    rectangle.getHeight()/ 2 / GdxGame.PPM);

            fixtureDef.shape = shape;

            body.createFixture(fixtureDef);
        }

        // BRICK
        for (MapObject object : MapLoader.getInstance().getTiledMap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new Brick(world, MapLoader.getInstance().getTiledMap(), rectangle);
        }

        // COIN
        for (MapObject object : MapLoader.getInstance().getTiledMap().getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new Coin(world, MapLoader.getInstance().getTiledMap(), rectangle);
        }
    }

    private void setOrthogonalTiledMapRenderer( ) {
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/GdxGame.PPM);
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public OrthogonalTiledMapRenderer getOrthogonalTiledMapRenderer() {
        return orthogonalTiledMapRenderer;
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }

    public World getWorld() {
        return world;
    }
}

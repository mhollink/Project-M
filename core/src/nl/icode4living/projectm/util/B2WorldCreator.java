package nl.icode4living.projectm.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import nl.icode4living.projectm.GdxGame;
import nl.icode4living.projectm.entities.enemies.Enemy;
import nl.icode4living.projectm.entities.enemies.Goomba;
import nl.icode4living.projectm.entities.enemies.Turtle;
import nl.icode4living.projectm.entities.interactive_tiles.Brick;
import nl.icode4living.projectm.entities.interactive_tiles.Coin;
import nl.icode4living.projectm.ui.screens.LevelScreen;

public class B2WorldCreator {

    public static final int GRAPHICS_LAYER = 2;
    public static final int GROUND_LAYER = 3;
    public static final int PIPE_LAYER = 4;
    public static final int BRICK_LAYER = 5;
    public static final int COIN_LAYER = 6;
    public static final int GOOMBA_LAYER = 7;
    public static final int TURTLE_LAYER = 8;

    private Array<Goomba> goombas;
    private Array<Turtle> turtles;

    public B2WorldCreator(LevelScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(GROUND_LAYER).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GdxGame.PPM, (rect.getY() + rect.getHeight() / 2) / GdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GdxGame.PPM, rect.getHeight() / 2 / GdxGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create pipe bodies/fixtures
        for(MapObject object : map.getLayers().get(PIPE_LAYER).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GdxGame.PPM, (rect.getY() + rect.getHeight() / 2) / GdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GdxGame.PPM, rect.getHeight() / 2 / GdxGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = GdxGame.OBJECT_BIT;
            body.createFixture(fdef);
        }

        //create brick bodies/fixtures
        for(MapObject object : map.getLayers().get(BRICK_LAYER).getObjects().getByType(RectangleMapObject.class)){
            new Brick(screen, object);
        }

        //create coin bodies/fixtures
        for(MapObject object : map.getLayers().get(COIN_LAYER).getObjects().getByType(RectangleMapObject.class)){
            new Coin(screen, object);
        }

        //create all goombas
        goombas = new Array<Goomba>();
        for(MapObject object : map.getLayers().get(GOOMBA_LAYER).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goomba(screen, rect.getX() / GdxGame.PPM, rect.getY() / GdxGame.PPM));
        }

        //create all turtles
        turtles = new Array<Turtle>();
        for(MapObject object : map.getLayers().get(TURTLE_LAYER).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.add(new Turtle(screen, rect.getX() / GdxGame.PPM, rect.getY() / GdxGame.PPM));
        }
    }

    public Array<Enemy> getEnemies(){
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(goombas);
        enemies.addAll(turtles);
        return enemies;
    }
}

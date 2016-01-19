package nl.icode4living.projectm.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import nl.icode4living.projectm.GdxGame;

public class Mario extends Sprite {

    private static Mario instance;
    public static Mario getInstance() {
        return instance;
    }

    World world;
    Body body;

    public Mario(World world) {
        this.world = world;
        init();
        instance=this;
    }

    private void init() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(80/ GdxGame.PPM, 32/GdxGame.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ GdxGame.PPM);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
    }

    public void jump(){
        body.applyLinearImpulse(new Vector2(0, 3.8f), body.getWorldCenter(), true);
    }

    public void move(float direction) {

        if (direction < 0 && body.getLinearVelocity().x <= 2) {
            body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
        } else if (direction > 0 && body.getLinearVelocity().x >= -2) {
            body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);
        }

    }

    public Body getBody() {
        return body;
    }

    public void moveToCursor(int x) {
        move(x);
    }

}

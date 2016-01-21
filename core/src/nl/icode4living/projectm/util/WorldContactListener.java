package nl.icode4living.projectm.util;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import nl.icode4living.projectm.GdxGame;
import nl.icode4living.projectm.entities.Mario;
import nl.icode4living.projectm.entities.enemies.Enemy;
import nl.icode4living.projectm.entities.interactive_tiles.InteractiveTileObject;
import nl.icode4living.projectm.entities.items.Item;
import nl.icode4living.projectm.entities.other.FireBall;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case GdxGame.MARIO_HEAD_BIT | GdxGame.BRICK_BIT:
            case GdxGame.MARIO_HEAD_BIT | GdxGame.COIN_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Mario) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Mario) fixB.getUserData());
                break;
            case GdxGame.ENEMY_HEAD_BIT | GdxGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
                break;
            case GdxGame.ENEMY_BIT | GdxGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case GdxGame.MARIO_BIT | GdxGame.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.MARIO_BIT)
                    ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
                else
                    ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
                break;
            case GdxGame.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;
            case GdxGame.ITEM_BIT | GdxGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case GdxGame.ITEM_BIT | GdxGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
                break;
            case GdxGame.FIREBALL_BIT | GdxGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == GdxGame.FIREBALL_BIT)
                    ((FireBall)fixA.getUserData()).setToDestroy();
                else
                    ((FireBall)fixB.getUserData()).setToDestroy();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
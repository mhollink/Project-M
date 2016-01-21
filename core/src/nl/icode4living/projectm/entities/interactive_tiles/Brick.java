package nl.icode4living.projectm.entities.interactive_tiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;

import nl.icode4living.projectm.GdxGame;
import nl.icode4living.projectm.entities.Mario;
import nl.icode4living.projectm.ui.scenes.Hud;
import nl.icode4living.projectm.ui.screens.LevelScreen;

public class Brick extends InteractiveTileObject {

    public Brick(LevelScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(GdxGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(mario.isBig()) {
            setCategoryFilter(GdxGame.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            GdxGame.manager.get(GdxGame.SFX_BREAK_BLOCK, Sound.class).play();
        }
        GdxGame.manager.get(GdxGame.SFX_BUMP, Sound.class).play();

        mario.currentState = Mario.State.FALLING;
    }

}

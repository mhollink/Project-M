package nl.icode4living.projectm.entities.interactive_tiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;

import nl.icode4living.projectm.GdxGame;
import nl.icode4living.projectm.entities.Mario;
import nl.icode4living.projectm.entities.items.ItemDef;
import nl.icode4living.projectm.entities.items.Mushroom;
import nl.icode4living.projectm.ui.scenes.Hud;
import nl.icode4living.projectm.ui.screens.LevelScreen;

public class Coin extends nl.icode4living.projectm.entities.interactive_tiles.InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 5;

    public Coin(LevelScreen screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("Mario_Tiles");
        fixture.setUserData(this);
        setCategoryFilter(GdxGame.COIN_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(getCell().getTile().getId() == BLANK_COIN)
            GdxGame.manager.get(GdxGame.SFX_BUMP, Sound.class).play();
        else {
            if(object.getProperties().containsKey("mushroom")) {
                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / GdxGame.PPM),
                        Mushroom.class));
                GdxGame.manager.get(GdxGame.SFX_POWER_UP_APPEARS, Sound.class).play();
            }
            else
                GdxGame.manager.get(GdxGame.SFX_COIN, Sound.class).play();
            getCell().setTile(
                    tileSet.getTile(BLANK_COIN)
            );
            Hud.addScore(350);
        }
        mario.currentState = Mario.State.FALLING;
    }
}

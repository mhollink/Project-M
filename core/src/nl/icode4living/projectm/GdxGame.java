package nl.icode4living.projectm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.icode4living.projectm.ui.screens.LevelScreen;

public class GdxGame extends Game {

	//Virtual Screen size and Box2D Scale(Pixels Per Meter)
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	//Box2D Collision Bits
	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIO_HEAD_BIT = 512;
	public static final short FIREBALL_BIT = 1024;

	public static final String SFX_MUSIC = "audio/music/Super Mario Bros. Music - Ground Theme.mp3";
    public static final String SFX_1UP = "audio/sounds/1-up.wav";
    public static final String SFX_BREAK_BLOCK = "audio/sounds/breakblock.wav";
    public static final String SFX_BUMP = "audio/sounds/bump.wav";
    public static final String SFX_COIN = "audio/sounds/coin.wav";
    public static final String SFX_DIE = "audio/sounds/die.wav";
    public static final String SFX_FIREBALL = "audio/sounds/fireball.wav";
    public static final String SFX_FLAG = "audio/sounds/flagpole.wav";
    public static final String SFX_GAMEOVER = "audio/sounds/gameover.wav";
    public static final String SFX_JUMP_SMALL = "audio/sounds/jump-small.wav";
    public static final String SFX_JUMP_BIG = "audio/sounds/jump-super.wav";
    public static final String SFX_KICK = "audio/sounds/kick.wav";
    public static final String SFX_PIPE = "audio/sounds/pipe.wav";
    public static final String SFX_POWER_UP_USED = "audio/sounds/powerup.wav";
    public static final String SFX_POWER_UP_APPEARS = "audio/sounds/powerup_appears.wav";
    public static final String SFX_STAGE_CLEAR = "audio/sounds/stage_clear.wav";
    public static final String SFX_HURRY = "audio/sounds/warning.wav";

    public static final String GFX_TILES = "graphics/tilesheets/Cloud.png";
    public static final String GFX_CLOUD = "graphics/tilesheets/Mario_Tiles.png";

    public static final String TMX_MENU = "tmx/mario_menu.tmx";
    public static final String TMX_LEVEL_W1L1 = "tmx/mario_1-1.tmx";


	public SpriteBatch batch;

	/* WARNING Using AssetManager in a static way can cause issues, especially on Android.
	Instead you may want to pass around Assetmanager to those the classes that need it.
	We will use it in the static context to save time for now. */
	public static AssetManager manager;

	@Override
	public void create () {
        Gdx.app.log(getClass().getSimpleName(), "Starting");

		batch = new SpriteBatch();
        Gdx.app.log(getClass().getSimpleName(), "Loading all sound files");
        manager = new AssetManager();
		manager.load(SFX_MUSIC, Music.class);
		manager.load(SFX_COIN, Sound.class);
		manager.load(SFX_BUMP, Sound.class);
		manager.load(SFX_BREAK_BLOCK, Sound.class);
        manager.load(SFX_JUMP_BIG, Sound.class);
        manager.load(SFX_JUMP_SMALL, Sound.class);
		manager.load(SFX_POWER_UP_APPEARS, Sound.class);
		manager.load(SFX_POWER_UP_USED, Sound.class);
		manager.load(SFX_KICK, Sound.class);
		manager.load(SFX_DIE, Sound.class);
		manager.load(SFX_GAMEOVER, Sound.class);

		manager.finishLoading();
        Gdx.app.log(getClass().getSimpleName(), "Finished loading");

		setScreen(new LevelScreen(this));
	}


	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}

	@Override
	public void render () {
		super.render();
	}
}

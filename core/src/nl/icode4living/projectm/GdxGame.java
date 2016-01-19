package nl.icode4living.projectm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.icode4living.projectm.ui.screens.MenuScreen;

public class GdxGame extends Game {

	public static final int WIDTH =  800;
	public static final int HEIGHT = 480;
	public static final float PPM = 100;

	public static final String TITLE = "Project-M";

	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

    public SpriteBatch getBatch() {
        return batch;
    }
}

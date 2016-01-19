package nl.icode4living.projectm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

import nl.icode4living.projectm.ui.screens.MenuScreen;
import nl.icode4living.projectm.util.CustomGestureListener;

public class GdxGame extends Game {

	public static final int V_WIDTH =  400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final String TITLE = "Project-M";

	private SpriteBatch batch;
	private GestureDetector gestureDetector;

	@Override
	public void create () {
		batch = new SpriteBatch();

        setScreen(new MenuScreen(this));

        gestureDetector = new GestureDetector(20, 0.5f, 2, 0.15f, new CustomGestureListener(this));
        Gdx.input.setInputProcessor(gestureDetector);
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

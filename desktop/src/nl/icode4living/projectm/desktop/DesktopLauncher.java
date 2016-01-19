package nl.icode4living.projectm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nl.icode4living.projectm.GdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GdxGame.WIDTH;
		config.height = GdxGame.HEIGHT;
		config.title = GdxGame.TITLE;
		new LwjglApplication(new GdxGame(), config);
	}
}

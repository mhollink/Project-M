package nl.icode4living.projectm.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.icode4living.projectm.GdxGame;

public class MenuScreen implements Screen{

    private OrthographicCamera camera;
    private Viewport viewport;

    private final GdxGame game;
    public MenuScreen(GdxGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(GdxGame.V_WIDTH/GdxGame.PPM, GdxGame.V_HEIGHT/GdxGame.PPM, camera);

        camera.position.set((viewport.getWorldWidth()/2),(viewport.getWorldHeight()/2),0);
    }

    private void handleInput(float delta) {
        if(Gdx.input.justTouched()){
            game.setScreen(new LevelScreen(game));
            dispose();
        }
    }

    private void update(float delta) {
        handleInput(delta);
        camera.update();

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

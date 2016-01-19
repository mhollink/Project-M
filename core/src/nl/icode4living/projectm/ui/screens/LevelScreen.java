package nl.icode4living.projectm.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.icode4living.projectm.GdxGame;
import nl.icode4living.projectm.entities.Mario;
import nl.icode4living.projectm.ui.scenes.Hud;
import nl.icode4living.projectm.util.MapLoader;

public class LevelScreen implements Screen {



    private OrthographicCamera camera;
    private Viewport viewport;

    private Hud hud;

    private Mario player;

    private final GdxGame game;

    public LevelScreen(GdxGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(GdxGame.V_WIDTH / GdxGame.PPM, GdxGame.V_HEIGHT / GdxGame.PPM, camera);
        hud = new Hud(game.getBatch());

        MapLoader.getInstance().loadMap("tmx/mario_1-1.tmx");
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        player = new Mario(MapLoader.getInstance().getWorld());
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(hud.stage.getCamera().combined);

        MapLoader.getInstance().getOrthogonalTiledMapRenderer().render();
        MapLoader.getInstance().getBox2DDebugRenderer().render(MapLoader.getInstance().getWorld(), camera.combined);
        hud.stage.draw();
    }

    private void update(float delta) {
        handleInput(delta);

        MapLoader.getInstance().getWorld().step(1 / 60f, 6, 2);

        camera.position.x = player.getBody().getPosition().x;

        camera.update();
        MapLoader.getInstance().getOrthogonalTiledMapRenderer().setView(camera);
    }

    private void handleInput(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.jump();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.move(-1f);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.move(1f);

        if(Gdx.input.isTouched()) {
            player.moveToCursor(viewport.getScreenWidth() / 2 - Gdx.input.getX());
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
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

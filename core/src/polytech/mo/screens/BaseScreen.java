package polytech.mo.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import polytech.mo.assets.Assets;
import polytech.mo.core.MyGame;
import polytech.mo.core.Updatable;
import polytech.mo.screens.interfaces.BaseInterface;
import polytech.mo.screens.interfaces.Interfacable;

public abstract class BaseScreen<T extends BaseInterface> extends ScreenAdapter implements Updatable, Interfacable, InputProcessor, ControllerListener{
    protected T UI;
    protected Assets assets;
    protected MyGame game;

    public BaseScreen(MyGame game) {
        this.game=game;
        assets= MyGame.getAssets();
    }

    @Override
    public void resize(int width, int height) {
        UI.getStage().getViewport().update(width, height);
    }

    @Override
    public void show() {
        setUI();
    }

    @Override
    public void hide() {
        UI.dispose();
    }

    @Override
    public void render(float delta) {
        update(delta);
        UI.render();
    }

    @Override
    public void update(float delta) {
        UI.update(delta);
    }

    public void switchScreen(final MyGame.ScreenType screen){
        UI.exitAnimation(new Runnable() {
            @Override
            public void run() {
                game.switchScreen(screen);
            }
        });
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    public Assets getAssets() {
        return assets;
    }
}

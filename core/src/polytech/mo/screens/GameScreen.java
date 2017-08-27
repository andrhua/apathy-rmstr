package polytech.mo.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;

import polytech.mo.core.MyGame;
import polytech.mo.screens.interfaces.GameInterface;
import polytech.mo.utils.Constants;

public class GameScreen extends BaseScreen<GameInterface> {
    private OrthographicCamera camera;

    public GameScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        camera=MyGame.getGameCamera();
    }

    @Override
    public void setUI() {
        UI =new GameInterface(this);
    }
}

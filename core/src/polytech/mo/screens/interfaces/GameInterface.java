package polytech.mo.screens.interfaces;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import polytech.mo.screens.BaseScreen;
import polytech.mo.screens.GameScreen;
import polytech.mo.utils.Constants;

public class GameInterface extends BaseInterface<GameScreen>{
    private Camera camera;

    public GameInterface(GameScreen screen) {
        super(screen);
        camera=new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
        camera.translate(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        camera.update();
    }

    @Override
    public void createStage() {

    }
}

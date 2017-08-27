package polytech.mo.screens.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Disposable;

import polytech.mo.core.MyGame;
import polytech.mo.core.Renderable;
import polytech.mo.core.Updatable;
import polytech.mo.screens.BaseScreen;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public abstract class BaseInterface<T extends BaseScreen> implements Renderable, Updatable, Stageable, Disposable{
    protected int width, height;
    private Stage stage;
    T screen;
    protected AssetManager assets;
    protected SpriteBatch batch;
    protected Button[][] buttons;
    protected Vector2 selectedButton;

    public BaseInterface(T screen){
        this.screen=screen;
        selectedButton=new Vector2(0,0);
        assets=MyGame.getAssets().getManager();
        width= Constants.WIDTH;
        height=Constants.HEIGHT;
        batch =new SpriteBatch();
        batch.setProjectionMatrix(MyGame.getCamera().combined);
        stage =new Stage(MyGame.getViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        enterAnimation();
        createStage();
    }

    @Override
    public void render() {
        drawBackground();
        stage.draw();
    }

    protected void drawBackground(){
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    protected void enterAnimation(){
        stage.addAction(sequence(
                alpha(0),
                fadeIn(Constants.ENTER_ANIMATION_DURATION)
                )
        );
    }

    public void exitAnimation(Runnable runnable) {
        Gdx.input.setInputProcessor(MyGame.getEmptyInputProccessor());
        stage.addAction(sequence(
                fadeOut(Constants.EXIT_ANIMATION_DURATION),
                run(runnable)
        ));
    }

    public Stage getStage() {
        return stage;
    }

}

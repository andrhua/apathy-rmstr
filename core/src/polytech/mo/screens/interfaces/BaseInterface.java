package polytech.mo.screens.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

import polytech.mo.assets.Assets;
import polytech.mo.core.MyGame;
import polytech.mo.game.objects.Renderable;
import polytech.mo.game.objects.Updatable;
import polytech.mo.screens.BaseScreen;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public abstract class BaseInterface<T extends BaseScreen> implements Renderable, Updatable, Stageable, Disposable{
    protected int width, height;
    private Stage stage;
    protected Table table;
    protected T screen;
    protected AssetManager assets;
    protected SpriteBatch spriteBatch;
    protected ShapeRenderer shapeRenderer;
    private Color color1, color2;
    protected Button[][] buttons;
    protected Vector2 selectedButton;
    private boolean isForwardEnter;
    private Vector2 gradient;

    public BaseInterface(T screen, boolean isForwardEnter){
        this.screen=screen;
        this.isForwardEnter = isForwardEnter;
        selectedButton=new Vector2(0,0);
        assets=MyGame.getAssets().getManager();
        width= Constants.WIDTH;
        height=Constants.HEIGHT;
        spriteBatch=new SpriteBatch();
        spriteBatch.setProjectionMatrix(MyGame.getCamera().combined);
        shapeRenderer=new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(MyGame.getCamera().combined);
        loadGradient();
        table=new Table();
        table.setFillParent(true);
        table.pad(Constants.TABLE_PAD);
        table.setDebug(true);
        stage =new Stage(MyGame.getViewport(), spriteBatch);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        enterAnimation();
        createStage();
    }

    protected void loadGradient(){
        color1=assets.get(Assets.uiSkin).getColor("light-violet");
        color2=assets.get(Assets.uiSkin).getColor("light-pink");
    }

    @Override
    public void render() {
        drawBackground();
        stage.draw();
    }

    protected void drawBackground(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(
                0,
                0,
                width,
                height,
                color2,
                color2,
                color1,
                color1
        );
        shapeRenderer.end();
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

    protected void enterAnimation(){
        stage.addAction(sequence(
                moveTo(0, (isForwardEnter ? 1 : -1) * Constants.HEIGHT),
                moveTo(0, 0, Constants.ENTER_ANIMATION_DURATION, Interpolation.exp5Out)
        ));
    }

    public void exitAnimation(Runnable runnable, boolean isForwardExit) {
        Gdx.input.setInputProcessor(MyGame.getEmptyInputProccessor());
        stage.addAction(sequence(
                moveTo(0, (isForwardExit ? -1 : 1) * Constants.HEIGHT, Constants.EXIT_ANIMATION_DURATION, Interpolation.exp5In),
                delay(.1f),
                run(runnable)
        ));
    }

    public Stage getStage() {
        return stage;
    }

}

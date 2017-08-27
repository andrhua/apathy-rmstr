package polytech.mo.screens.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import polytech.mo.screens.BaseScreen;
import polytech.mo.screens.SplashScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class SplashInterface extends BaseInterface<SplashScreen> {
    private boolean isActionAdded;
    private Table table;

    public SplashInterface(SplashScreen screen) {
        super(screen);
        isActionAdded=false;
    }

    @Override
    protected void enterAnimation() {

    }

    @Override
    protected void drawBackground() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void createStage() {
        table=new Table();
        table.setFillParent(true);
        getStage().addActor(table);
        Texture logo=new Texture("gfx/logo.png");
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image logoImage =new Image(logo);
        table.defaults().align(Align.center);
        table.add(logoImage).width(200).height(200);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (screen.isAssetsLoaded())
            if (!isActionAdded) {
                table.addAction(fadeOut(.7f));
                isActionAdded = true;
            }
    }

}

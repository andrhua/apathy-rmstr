package polytech.mo.screens;

import polytech.mo.assets.GameAssets;
import polytech.mo.core.MyGame;
import polytech.mo.screens.interfaces.LevelSelectorInterface;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static polytech.mo.utils.Constants.DIALOG_ANIMATION_DURATION;

public class LevelSelectorScreen extends BaseScreen<LevelSelectorInterface> {
    private boolean isForward;

    public LevelSelectorScreen(MyGame game, boolean isForward) {
        super(game);
        this.isForward=isForward;
    }

    public void back(){
        switchScreen(MyGame.ScreenType.START, false);
    }

    public void loadLevel(final int i){
        UI.getLoadingDialog().show(UI.getStage(), sequence(
                alpha(0),
                fadeIn(DIALOG_ANIMATION_DURATION),
                run(new Runnable() {
                    @Override
                    public void run() {
                        new GameAssets();
                        //LevelBuilder.build(LevelBuilder.Level.values()[i]);
                        //switchScreen(MyGame.ScreenType.GAME, true);
                    }
                })
        ));
    }

    @Override
    public void setUI() {
        UI =new LevelSelectorInterface(this, isForward);
    }

}

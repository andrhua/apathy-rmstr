package polytech.mo.screens;

import com.badlogic.gdx.Gdx;

import polytech.mo.core.MyGame;
import polytech.mo.screens.interfaces.StartMenuInterface;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class StartMenuScreen extends BaseScreen<StartMenuInterface> {
    private boolean isForward;

    public StartMenuScreen(MyGame game, boolean isForward) {
        super(game);
        this.isForward = isForward;
    }

    public void showExitDialog() {
        UI.getExitDialog().show(UI.getStage(),
                sequence(
                        alpha(0),
                        fadeIn(Constants.DIALOG_ANIMATION_DURATION)
                ));
    }

    public void hideExitDialog() {
        UI.getExitDialog().hide(fadeOut(Constants.DIALOG_ANIMATION_DURATION));
    }

    public void exit() {
        Gdx.app.exit();
    }

    public void start() {
        switchScreen(MyGame.getSettings().isIntroduced() ? MyGame.ScreenType.LEVEL_SELECTOR : MyGame.ScreenType.INTRODUCTION, true);
    }

    public void goToSettings() {
        switchScreen(MyGame.ScreenType.SETTINGS, false);
    }

    @Override
    public void setUI() {
        UI = new StartMenuInterface(this, isForward);
    }
}

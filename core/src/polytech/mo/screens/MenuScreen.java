package polytech.mo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;

import polytech.mo.audio.Music;
import polytech.mo.core.I18n;
import polytech.mo.core.MyGame;
import polytech.mo.game.levels.LevelBuilder;
import polytech.mo.profile.Resetable;
import polytech.mo.screens.interfaces.MenuScreenInterface;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static polytech.mo.core.MyGame.getCamera;
import static polytech.mo.core.MyGame.getSettings;

public class MenuScreen extends BaseScreen<MenuScreenInterface> implements Resetable{
    public enum MenuState {START, SETTINGS, INTRO, LEVEL_SELECTOR}
    private MenuState menuState;

    private void setMenuState(MenuState menuState, boolean isForwardAnimation){
        UI.isNeedToMoveGradient=true;
        UI.ifa=isForwardAnimation;
        final Table quittingTable=UI.getTables()[this.menuState.ordinal()];
        Table enteringTable=UI.getTables()[menuState.ordinal()];
        Gdx.input.setInputProcessor(MyGame.getEmptyInputProccessor());
        enteringTable.setVisible(true);
        UI.getScreenTable().addAction(sequence(
                moveBy(0, (isForwardAnimation?-1:1)*(1+Constants.INTERSCREEN_PAD)*Constants.HEIGHT, Constants.ENTER_ANIMATION_DURATION, Interpolation.exp5),
                run(new Runnable() {
                    @Override
                    public void run() {
                        Gdx.input.setInputProcessor(UI.getStage());
                        quittingTable.setVisible(false);
                    }
                })
        ));
        this.menuState=menuState;
    }

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        menuState=MenuState.START;
        UI.getTables()[menuState.ordinal()].setVisible(true);
    }

    @Override
    public void setUI() {
        UI=new MenuScreenInterface(this);
    }

    @Override
    public void reset(){
        getSettings().reset();
        backToStartMenu(true);
    }

    public void exit() {
        Gdx.app.exit();
    }

    public void start(boolean ifa) {
        setMenuState(MyGame.getSettings().isIntroduced() ? MenuState.LEVEL_SELECTOR : MenuState.INTRO, ifa);
    }

    public void goToSettings(boolean ifa) {
        setMenuState(MenuState.SETTINGS, ifa);
    }

    public void setSfx(){
        getSettings().toggleSfxEnabled();
    }

    public void setMusic(){
        getSettings().toggleMusicEnabled();
        if (getSettings().isMusicEnabled()) MyGame.initAsyncMusic(); else Music.stop();

    }

    public void setLanguage(I18n.Language language){
        I18n.setLanguage(language);
        setUI();
        backToStartMenu(true);
    }

    public void backToStartMenu(boolean ifa){setMenuState(MenuState.START, ifa);}

    public void loadLevel(LevelBuilder.Level level){}

    public void showDialog(Dialog dialog){
        dialog.show(UI.getStage(),
                sequence(
                        alpha(0),
                        fadeIn(Constants.DIALOG_ANIMATION_DURATION)
                ));
    }

    public void hideDialog(Dialog dialog){
        dialog.hide(fadeOut(Constants.DIALOG_ANIMATION_DURATION));
    }

}

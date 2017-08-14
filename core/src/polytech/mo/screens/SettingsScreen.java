package polytech.mo.screens;

import polytech.mo.audio.Music;
import polytech.mo.core.I18n;
import polytech.mo.core.MyGame;
import polytech.mo.profile.Resetable;
import polytech.mo.screens.interfaces.SettingsInterface;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static polytech.mo.core.MyGame.getSettings;

public class SettingsScreen extends BaseScreen<SettingsInterface> implements Resetable {
    public SettingsScreen(MyGame game) {
        super(game);
    }

    public void setSfx(){
        getSettings().toggleSfxEnabled();
    }

    public void setMusic(){
        getSettings().toggleMusicEnabled();
        if (getSettings().isMusicEnabled()) MyGame.initAsyncMusic(); else Music.stop();

    }

    @Override
    public void reset(){
        getSettings().reset();
        back();
    }

    public void setLanguage(I18n.Language language){
        I18n.setLanguage(language);
        back();
    }

    public void back(){
        switchScreen(MyGame.ScreenType.START, true);
    }

    public void showResetDialog(){
        UI.getResetDialog().show(UI.getStage(),
                sequence(
                        alpha(0),
                        fadeIn(Constants.DIALOG_ANIMATION_DURATION)
                ));
    }

    public void hideResetDialog(){
        UI.getResetDialog().hide(fadeOut(Constants.DIALOG_ANIMATION_DURATION));
    }

    @Override
    public void setUI() {
        UI =new SettingsInterface(this);
    }
}

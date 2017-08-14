package polytech.mo.screens;

import com.badlogic.gdx.utils.Timer;

import polytech.mo.assets.Assets;
import polytech.mo.audio.Music;
import polytech.mo.audio.SFX;
import polytech.mo.core.I18n;
import polytech.mo.core.MyGame;
import polytech.mo.profile.Progress;
import polytech.mo.profile.Settings;
import polytech.mo.screens.interfaces.SplashInterface;
import polytech.mo.ui.UIBuilder;

public class SplashScreen extends BaseScreen<SplashInterface>{
    private boolean isAssetsLoaded;

    @Override
    public void show() {
        super.show();
        isAssetsLoaded =false;
    }

    public SplashScreen(final MyGame game){
        super(game);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (assets.getManager().update()&&!isAssetsLoaded) {
            MyGame.setSettings(new Settings());
            MyGame.setProgress(new Progress());
            new SFX(assets.getManager());
            new Music();
            new I18n();
            new UIBuilder(assets.getManager());
            isAssetsLoaded =true;
            assets.setState(Assets.State.LOADED);
            MyGame.setMusic(new Music());
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.switchScreen(MyGame.ScreenType.START);
                }
            }, 1);
        }
    }

    public boolean isAssetsLoaded() {
        return isAssetsLoaded;
    }

    @Override
    public void setUI() {
        UI =new SplashInterface(this);
    }
}

package polytech.mo.audio;

import polytech.mo.assets.Assets;
import polytech.mo.game.objects.Updatable;

import static polytech.mo.core.MyGame.getAssets;
import static polytech.mo.core.MyGame.getSettings;

public class Music implements Updatable {
    private static com.badlogic.gdx.audio.Music music;
    private float elapsedTime, fadeOutTime;
    private static boolean isNeedToStop;

    public Music(){
        music= getAssets().getManager().get(Assets.music);
        music.setLooping(true);
        elapsedTime=0;
        fadeOutTime=3f;
        isNeedToStop=false;
    }

    public static void start(){
        if (getSettings().isMusicEnabled()) {
            music.setVolume(1);
            music.play();
        }
    }

    public static void stop(){
        isNeedToStop=true;
    }

    @Override
    public void update(float delta) {
        if (isNeedToStop){
            if (elapsedTime<=fadeOutTime) {
                music.setVolume(1 - elapsedTime/fadeOutTime);
                elapsedTime += delta;
            } else {
                music.stop();
                isNeedToStop=false;
                elapsedTime=0;
            }
        }
    }
}

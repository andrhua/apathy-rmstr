package polytech.mo.audio;

import com.badlogic.gdx.assets.AssetManager;

import polytech.mo.assets.Assets;
import polytech.mo.core.MyGame;
import polytech.mo.profile.Settings;

public class SFX {
    public enum SoundType{CLICK_POSITIVE, CLICK_NEGATIVE}
    private static AssetManager assets;

    public SFX(AssetManager assets){
        SFX.assets=assets;
    }

    public static void play(SoundType soundType){
        if (MyGame.getSettings().isSfxEnabled()) {
            switch (soundType) {
                case CLICK_NEGATIVE:assets.get(Assets.negativeClick).play();break;
                case CLICK_POSITIVE:assets.get(Assets.positiveClick).play();break;
            }
        }
    }

    public void disposeGameSfx(){

    }
}

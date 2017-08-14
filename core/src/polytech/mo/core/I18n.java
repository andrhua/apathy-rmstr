package polytech.mo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

import polytech.mo.profile.Settings;

import static polytech.mo.core.MyGame.getSettings;

public class I18n {
    public enum Language {EN, RU}
    private static I18NBundle bundle;
    private static FileHandle file;

    public I18n(){
        file = Gdx.files.internal("i18n/strings");
        setLanguage(getSettings().getLanguage());
    }

    public static boolean setLanguage(Language language){
        Language prev= getSettings().getLanguage();
        getSettings().setLanguage(language);
        boolean res=prev.name().equals(language.name());
        Locale locale = new Locale(language.name());
        bundle = I18NBundle.createBundle(file, locale);
        return res;
    }

    public static String getString(String key, Object... args){
        return bundle.format(key, args);
    }
}

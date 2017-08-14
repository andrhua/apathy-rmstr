package polytech.mo.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Locale;

import polytech.mo.core.I18n;

public class Settings extends BasePlayerData{
    private final String PREFERENCES = "уровень эволюции влюбился в клиентку", LANGUAGE = "language", SFX = "SFX", INTRO="intro", MUSIC="music";
    private boolean isSfxEnabled, isIntroduced, isMusicEnabled;
    private I18n.Language language;
    private Preferences preferences;

    public Settings(){
        preferences = Gdx.app.getPreferences(PREFERENCES);
        isSfxEnabled=true;
        isMusicEnabled=true;
        isIntroduced=false;
        language= Locale.getDefault().toString().contains("ru")? I18n.Language.RU : I18n.Language.EN;
        read();
    }


    @Override
    public void read() {
        if (preferences.contains(LANGUAGE)) language = I18n.Language.valueOf(preferences.getString(LANGUAGE));
        if (preferences.contains(SFX)) isSfxEnabled = preferences.getBoolean(SFX);
        if (preferences.contains(MUSIC)) isMusicEnabled=preferences.getBoolean(MUSIC);
        if (preferences.contains(INTRO)) isIntroduced=preferences.getBoolean(INTRO);
    }

    @Override
    public void write() {
        preferences.putString(LANGUAGE, language.name());
        preferences.putBoolean(SFX, isSfxEnabled);
        preferences.putBoolean(MUSIC, isMusicEnabled);
        preferences.putBoolean(INTRO, isIntroduced);
        preferences.flush();
    }

    @Override
    public void reset() {
        isSfxEnabled=true;
        isMusicEnabled=true;
        isIntroduced=false;
        write();
    }

    public void setIsIntroduced(boolean isIntroduced){ this.isIntroduced=isIntroduced;}

    public void toggleSfxEnabled() {
        isSfxEnabled = !isSfxEnabled;
    }

    public void toggleMusicEnabled(){isMusicEnabled=!isMusicEnabled;}

    public void setLanguage(I18n.Language language) {
        this.language = language;
    }

    public boolean isSfxEnabled() {
        return isSfxEnabled;
    }

    public boolean isIntroduced() {
        return isIntroduced;
    }

    public boolean isMusicEnabled() {
        return isMusicEnabled;
    }

    public I18n.Language getLanguage() {
        return language;
    }

}
package polytech.mo.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;

import polytech.mo.utils.Constants;

public class Assets {
    private AssetManager manager;
    public enum State {UNLOADED, LOADED}
    private State state;
    public static AssetDescriptor<Skin> uiSkin;
    public static AssetDescriptor<Sound> positiveClick, negativeClick;
    public static AssetDescriptor<Music> music;
    public static BitmapFont mainRegularFont, mainLogoFont, mainPreviewFont, mainDialogFont;

    public Assets(){
        state=State.UNLOADED;
        initManager();
    }

    public void initManager(){
        manager =new AssetManager(new InternalFileHandleResolver());

        uiSkin= new AssetDescriptor<>("gfx/skin/ui.json", Skin.class);
        initFonts();
        ObjectMap<String, Object> fontMap = new ObjectMap<>();
        fontMap.put("main-regular", mainRegularFont);
        fontMap.put("main-preview", mainPreviewFont);
        fontMap.put("main-dialog", mainDialogFont);
        fontMap.put("main-logo", mainLogoFont);
        SkinLoader.SkinParameter parameter = new SkinLoader.SkinParameter(fontMap);

        positiveClick= new AssetDescriptor<>("audio/click-positive.mp3", Sound.class);
        negativeClick= new AssetDescriptor<>("audio/click-negative.wav", Sound.class);
        music= new AssetDescriptor<>("audio/music.mp3", Music.class);

        manager.load(uiSkin.fileName, Skin.class, parameter);
        manager.load(positiveClick);
        manager.load(negativeClick);
        manager.load(music);
    }

    private void initFonts(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("main.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.minFilter= Texture.TextureFilter.Linear;
        parameter.magFilter= Texture.TextureFilter.Linear;
        parameter.characters="абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        parameter.size= Constants.FONT_REGULAR;
        mainRegularFont =generator.generateFont(parameter);
        mainRegularFont.setUseIntegerPositions(false);

        parameter.size=Constants.FONT_LOGO;
        mainLogoFont =generator.generateFont(parameter);

        parameter.size=Constants.FONT_PREVIEW;
        parameter.color=Color.BLACK;
        mainDialogFont =generator.generateFont(parameter);

        parameter.color=Color.WHITE;
        parameter.borderColor=Color.BLACK;
        parameter.borderWidth=1;
        mainPreviewFont=generator.generateFont(parameter);

        generator.dispose();
    }

    public AssetManager getManager(){
        return manager;
    }

    private void onFinish(){
        Skin skin=manager.get(uiSkin);
        mainRegularFont.setColor(skin.getColor("font-regular"));
       // mainPreviewFont.setColor(skin.getColor("font-unselected"));
        mainLogoFont.setColor(skin.getColor("white"));
    }

    public void setState(State state) {
        switch (state){
            case LOADED:
                if (this.state!=State.LOADED) onFinish();
                break;
        }
        this.state = state;
    }


}

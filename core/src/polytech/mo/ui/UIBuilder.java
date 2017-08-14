package polytech.mo.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import polytech.mo.assets.Assets;
import polytech.mo.core.I18n;
import polytech.mo.profile.Progress;
import polytech.mo.utils.Constants;

public class UIBuilder {
    private static AssetManager assets;
    private static Skin uiSkin;

    public UIBuilder(AssetManager assets){
        UIBuilder.assets=assets;
        uiSkin=assets.get(Assets.uiSkin);
    }

    public static ImageButton initImageButton(String styleName){
        ImageButton button=new ImageButton(uiSkin, styleName);
        button.addListener(new TouchListener(button));
        return button;
    }

    public static TextButton initTextButton(String text, boolean... args){
        TextButton button=new TextButton(I18n.getString(text), uiSkin);
        button.addListener(new TouchListener(button, args));
        return button;
    }

    public static TextButton initTextButton(String text, String styleName, boolean... args){
        TextButton button=new TextButton(I18n.getString(text), uiSkin, styleName);
        button.addListener(new TouchListener(button, args));
        return button;
    }

    public static MyCheckBox initCheckBox(String text){
        return new MyCheckBox(text, assets);
    }

    public static Image initImage(String name){
        return new Image(uiSkin.getDrawable(name));
    }

    public static Label initLabel(String text, String styleName, Object... args){
        Label label=new Label(I18n.getString(text), uiSkin, styleName);
        label.setAlignment(Align.center);
        return label;
    }

    public static Container<Stack> initLevelPreview(Progress.Level level, ActorGestureListener listener){
        Container<Stack>container=new Container<Stack>();
        Image levelPreview=new Image(uiSkin.getDrawable("preview-".concat(String.valueOf(level.getLevel().ordinal()))));
        String name=level.getLevel().name().toLowerCase();
        Stack stack=new Stack(levelPreview);
        if (!level.isUnlocked()){
            Image locked=new Image(uiSkin.getDrawable("locked"));
            stack.add(locked);
        } else {
            Label levelName=initLabel(name, "preview");
            stack.add(levelName);
        }
        container.align(Align.center).size(Constants.PREVIEW_WIDTH, Constants.PREVIEW_WIDTH/1.7f).setActor(stack);
        if (level.isUnlocked()) {
            container.addListener(new TouchListener(stack));
            container.addListener(listener);
        }

        return container;
    }

}

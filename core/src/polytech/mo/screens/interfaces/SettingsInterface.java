package polytech.mo.screens.interfaces;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import polytech.mo.assets.Assets;
import polytech.mo.core.I18n;
import polytech.mo.screens.SettingsScreen;
import polytech.mo.ui.MyCheckBox;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static polytech.mo.core.MyGame.getSettings;
import static polytech.mo.ui.UIBuilder.initCheckBox;
import static polytech.mo.ui.UIBuilder.initImage;
import static polytech.mo.ui.UIBuilder.initLabel;
import static polytech.mo.ui.UIBuilder.initTextButton;
import static polytech.mo.utils.Constants.DIALOG_ANIMATION_DURATION;

public class SettingsInterface extends BaseInterface<SettingsScreen> {
    private Dialog resetDialog;

    public SettingsInterface(SettingsScreen screen) {
        super(screen, false);
    }

    @Override
    public void createStage() {
        Image divider=initImage("divider");
        MyCheckBox sfxCheckBox=initCheckBox("sfx");
        sfxCheckBox.setChecked(getSettings().isSfxEnabled());
        sfxCheckBox.getLabelCell().pad(20);
        sfxCheckBox.getTable().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                screen.setSfx();
            }
        });
        MyCheckBox musicCheckBox=initCheckBox("music");
        musicCheckBox.setChecked(getSettings().isMusicEnabled());
        musicCheckBox.getLabelCell().pad(20);
        musicCheckBox.getTable().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                screen.setMusic();
            }
        });

        TextButton resetButton= initTextButton("reset_data");
        resetButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.showResetDialog();
            }
        });

        TextButton englishButton=initTextButton("english");
        englishButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.setLanguage(I18n.Language.EN);
            }
        });
        TextButton russianButton=initTextButton("russian");
        russianButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.setLanguage(I18n.Language.RU);
            }
        });
        TextButton backButton=initTextButton("back", false);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.back();
            }
        });

        table.defaults().expand().align(Align.center);
        table.add(sfxCheckBox.getTable());
        table.add(musicCheckBox.getTable()).row();
        table.add(new Image(divider.getDrawable())).colspan(2).width(Constants.DIVIDER_WIDTH).row();
        table.add(resetButton).colspan(2).row();
        table.add(divider).colspan(2).width(Constants.DIVIDER_WIDTH).row();
        table.add(englishButton);
        table.add(russianButton).row();
        table.add(backButton).align(Align.bottomLeft).expand(true, false);

        resetDialog=new Dialog("", assets.get(Assets.uiSkin)){
            @Override
            protected void result(Object object) {
                if ((Boolean)object) screen.reset(); else screen.hideResetDialog();
            }
        };
        resetDialog.getButtonTable().defaults().pad(40, 35, 40, 35);
        resetDialog
                .text(initLabel("reset-confirmation", "dialog"))
                .button(initTextButton("yes", "dialog"), true)
                .button(initTextButton("no", "dialog"), false);
        resetDialog.setPosition(width/2-resetDialog.getPrefWidth()/2, height/2-resetDialog.getPrefHeight()/2);
    }

    public Dialog getResetDialog() {
        return resetDialog;
    }

}

package polytech.mo.screens.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import polytech.mo.assets.Assets;
import polytech.mo.screens.StartMenuScreen;

import static polytech.mo.ui.UIBuilder.initImage;
import static polytech.mo.ui.UIBuilder.initLabel;
import static polytech.mo.ui.UIBuilder.initTextButton;

public class StartMenuInterface extends BaseInterface<StartMenuScreen> {
    private Dialog exitDialog;

    public StartMenuInterface(StartMenuScreen screen, boolean isForward) {
        super(screen, isForward);
    }

    @Override
    public void createStage() {
        Image divider = initImage("divider");
        Label apathyLabel = initLabel("apathy", "logo");
        TextButton startButton = initTextButton("start");
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.start();
            }
        });
        TextButton settingsButton = initTextButton("settings");
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.goToSettings();
            }
        });
        TextButton exitButton = initTextButton("exit");
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.showExitDialog();
            }
        });

        buttons = new Button[1][3];
        buttons[0][0] = startButton;
        buttons[0][1] = settingsButton;
        buttons[0][2] = exitButton;

        Table buttonTable = new Table();
        buttonTable.defaults().align(Align.center).pad(20);
        buttonTable.add(startButton).row();
        buttonTable.add(settingsButton).row();
        buttonTable.add(exitButton).row();

        table.defaults().align(Align.center);
        table.add(apathyLabel).row();
        table.add(divider).width(apathyLabel.getWidth()).padBottom(20).row();
        table.add(buttonTable);

        exitDialog = new Dialog("", assets.get(Assets.uiSkin)){
            @Override
            protected void result(Object object) {
                if ((Boolean) object) screen.exit(); else screen.hideExitDialog();
            }
        };
        exitDialog.text(initLabel("exit-confirmation", "dialog"));
        exitDialog.getButtonTable().defaults().pad(40, 35, 40, 35);
        exitDialog.button(initTextButton("yes", "dialog"), true).button(initTextButton("no", "dialog"), false);
        exitDialog.setPosition(width/2-exitDialog.getPrefWidth()/2, height/2-exitDialog.getPrefHeight()/2);
    }

    public Dialog getExitDialog() {
        return exitDialog;
    }


}

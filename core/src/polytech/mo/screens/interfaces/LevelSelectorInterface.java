package polytech.mo.screens.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import polytech.mo.assets.Assets;
import polytech.mo.core.MyGame;
import polytech.mo.profile.Progress;
import polytech.mo.screens.LevelSelectorScreen;

import static polytech.mo.ui.UIBuilder.initLabel;
import static polytech.mo.ui.UIBuilder.initLevelPreview;
import static polytech.mo.ui.UIBuilder.initTextButton;

public class LevelSelectorInterface extends BaseInterface<LevelSelectorScreen> {
    private Dialog loadingDialog;
    public LevelSelectorInterface(LevelSelectorScreen screen, boolean isForward) {
        super(screen, isForward);
    }

    @Override
    public void createStage() {
        loadingDialog =new Dialog("", assets.get(Assets.uiSkin), "loading");
        loadingDialog.text(initLabel("loading", "dialog"));
        loadingDialog.setPosition(width/2-loadingDialog.getPrefWidth()/2, height/2-loadingDialog.getPrefHeight()/2);
        TextButton backButton=initTextButton("back", false);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.back();
            }
        });
        Array<Progress.Level> progress = MyGame.getProgress().getLevels();
        Array<Container<Stack>> previews = new Array<Container<Stack>>(6);
        Array<Label> deaths = new Array<Label>(6);
        for (int i = 0; i < 6; i++) {
            Progress.Level tmp= progress.get(i);
            previews.add(initLevelPreview(tmp, new ActorGestureListener(){
                @Override
                public void tap(InputEvent event, float x, float y, int count, int button) {
                    screen.loadLevel(0);
                }
            }));
            deaths.add(initLabel("deaths", "default", tmp.getDeaths()));
        }

        table.defaults().align(Align.center).expandX().pad(100, 20, 25, 20);
        table.add(previews.get(0));
        table.add(previews.get(1));
        table.add(previews.get(2)).row();
        table.defaults().pad(0);
        table.add(deaths.get(0));
        table.add(deaths.get(1));
        table.add(deaths.get(2)).row();
        table.defaults().pad(70, 20, 25, 20);
        table.add(previews.get(3));
        table.add(previews.get(4));
        table.add(previews.get(5)).row();
        table.defaults().pad(0);
        table.add(deaths.get(3));
        table.add(deaths.get(4));
        table.add(deaths.get(5)).row();
        table.add().colspan(3).expandY().row();
        table.add(backButton).align(Align.bottomLeft).colspan(3).expand(false, false);
    }

    public Dialog getLoadingDialog() {
        return loadingDialog;
    }
}

package polytech.mo.screens.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import polytech.mo.assets.Assets;
import polytech.mo.core.I18n;
import polytech.mo.core.MyGame;
import polytech.mo.game.levels.LevelBuilder;
import polytech.mo.profile.Progress;
import polytech.mo.screens.MenuScreen;
import polytech.mo.ui.GradientSprite;
import polytech.mo.ui.MyCheckBox;
import polytech.mo.utils.Constants;

import static polytech.mo.core.MyGame.getSettings;
import static polytech.mo.ui.UIBuilder.initCheckBox;
import static polytech.mo.ui.UIBuilder.initImage;
import static polytech.mo.ui.UIBuilder.initLabel;
import static polytech.mo.ui.UIBuilder.initLevelPreview;
import static polytech.mo.ui.UIBuilder.initTable;
import static polytech.mo.ui.UIBuilder.initTextButton;
import static polytech.mo.utils.Constants.ENTER_ANIMATION_DURATION;
import static polytech.mo.utils.Constants.INTERSCREEN_PAD;

public class MenuScreenInterface extends BaseInterface<MenuScreen>{
    private Dialog menuExitDialog;
    private Dialog settingsResetDialog;
    private Table[] tables;
    private Table screenTable;
    private GradientSprite gradientSprite;
    public boolean isNeedToMoveGradient, ifa;
    private float elapsedTime;

    public MenuScreenInterface(MenuScreen screen) {
        super(screen);
    }

    @Override
    public void createStage() {
        loadGradient();
        Stack stack = new Stack();
        stack.setFillParent(true);
        screenTable=new Table();
        screenTable.defaults().width(width).height(height).padTop(INTERSCREEN_PAD*height/2).padBottom(INTERSCREEN_PAD*height/2);
        tables=new Table[MenuScreen.MenuState.values().length];
        for(int i=0; i<MenuScreen.MenuState.values().length; i++) {
            tables[i]=initTable();
            tables[i].setVisible(false);
        }
        initStart();
        initSettings();
        initIntro();
        initLevelSelector();

        Stack thirdFloorStack = new Stack();
        thirdFloorStack.setFillParent(true);
        thirdFloorStack.add(tables[MenuScreen.MenuState.INTRO.ordinal()]);
        thirdFloorStack.add(tables[MenuScreen.MenuState.LEVEL_SELECTOR.ordinal()]);

        screenTable.add(thirdFloorStack).row();
        screenTable.add(tables[MenuScreen.MenuState.START.ordinal()]).row();
        screenTable.add(tables[MenuScreen.MenuState.SETTINGS.ordinal()]).row();
        stack.add(screenTable);
        getStage().addActor(stack);
    }

    private void initStart(){
        Image menuDivider = initImage("divider");
        //Image dialogDivder=initImage("black-divider");
        Label menuLabel = initLabel("apathy", "logo");
        TextButton menuStartButton = initTextButton("start");
        menuStartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.start(true);
            }
        });
        TextButton menuSettingsButton = initTextButton("settings");
        menuSettingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.goToSettings(false);
            }
        });
        TextButton menuExitButton = initTextButton("exit");
        menuExitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.showDialog(menuExitDialog);
            }
        });

        buttons = new Button[1][3];
        buttons[0][0] = menuStartButton;
        buttons[0][1] = menuSettingsButton;
        buttons[0][2] = menuExitButton;

        Table buttonTable = new Table();
        buttonTable.defaults().align(Align.center).pad(20);
        buttonTable.add(menuStartButton).row();
        buttonTable.add(menuSettingsButton).row();
        buttonTable.add(menuExitButton).row();

        Table t=tables[MenuScreen.MenuState.START.ordinal()];
        t.defaults().align(Align.center);
        t.add(menuLabel).row();
        t.add(menuDivider).width(menuLabel.getWidth()).padBottom(20).row();
        t.add(buttonTable);

        menuExitDialog = new Dialog("", assets.get(Assets.uiSkin)){
            @Override
            protected void result(Object object) {
                if ((Boolean) object) screen.exit(); else screen.hideDialog(menuExitDialog);
            }
        };
        menuExitDialog.text(initLabel("exit-confirmation", "dialog"));
        menuExitDialog.getContentTable().row();
        //menuExitDialog.getContentTable().add(dialogDivder).width(450);
        menuExitDialog.getButtonTable().defaults().pad(40, 35, 40, 35);
        menuExitDialog.button(initTextButton("yes", "dialog"), true).button(initTextButton("no", "dialog"), false);
        menuExitDialog.setPosition(width/2-menuExitDialog.getPrefWidth()/2, height/2-menuExitDialog.getPrefHeight()/2);
    }

    private void initSettings(){
        Image settingsDivider = initImage("divider");
        MyCheckBox settingsSfxCheckBox = initCheckBox("sfx");
        settingsSfxCheckBox.setChecked(getSettings().isSfxEnabled());
        settingsSfxCheckBox.getLabelCell().pad(20);
        settingsSfxCheckBox.getTable().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                screen.setSfx();
            }
        });
        MyCheckBox settingsMusicCheckBox = initCheckBox("music");
        settingsMusicCheckBox.setChecked(getSettings().isMusicEnabled());
        settingsMusicCheckBox.getLabelCell().pad(20);
        settingsMusicCheckBox.getTable().addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                screen.setMusic();
            }
        });

        TextButton settingsResetButton = initTextButton("reset_data");
        settingsResetButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.showDialog(settingsResetDialog);
            }
        });

        TextButton settingsEnglishButton = initTextButton("english");
        settingsEnglishButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.setLanguage(I18n.Language.EN);
            }
        });
        TextButton settingsRussianButton = initTextButton("russian");
        settingsRussianButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.setLanguage(I18n.Language.RU);
            }
        });
        TextButton settingsBackButton = initTextButton("back", false);
        settingsBackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.backToStartMenu(true);
            }
        });

        Table t=tables[MenuScreen.MenuState.SETTINGS.ordinal()];
        t.defaults().expand().align(Align.center);
        t.add(settingsSfxCheckBox.getTable());
        t.add(settingsMusicCheckBox.getTable()).row();
        t.add(new Image(settingsDivider.getDrawable())).colspan(2).width(Constants.DIVIDER_WIDTH).row();
        t.add(settingsResetButton).colspan(2).row();
        t.add(settingsDivider).colspan(2).width(Constants.DIVIDER_WIDTH).row();
        t.add(settingsEnglishButton);
        t.add(settingsRussianButton).row();
        t.add(settingsBackButton).align(Align.bottomLeft).expand(true, false);

        settingsResetDialog=new Dialog("", assets.get(Assets.uiSkin)){
            @Override
            protected void result(Object object) {
                if ((Boolean)object) screen.reset(); else screen.hideDialog(settingsResetDialog);
            }
        };
        settingsResetDialog.getButtonTable().defaults().pad(40, 35, 40, 35);
        settingsResetDialog
                .text(initLabel("reset-confirmation", "dialog"))
                .button(initTextButton("yes", "dialog"), true)
                .button(initTextButton("no", "dialog"), false);
        settingsResetDialog.setPosition(width/2-settingsResetDialog.getPrefWidth()/2, height/2-settingsResetDialog.getPrefHeight()/2);
    }

    private void initIntro(){

    }

    private void initLevelSelector(){
        Dialog selectorLoadingDialog = new Dialog("", assets.get(Assets.uiSkin), "loading");
        selectorLoadingDialog.text(initLabel("loading", "dialog"));
        selectorLoadingDialog.setPosition(width/2- selectorLoadingDialog.getPrefWidth()/2, height/2- selectorLoadingDialog.getPrefHeight()/2);
        TextButton selectorBackButton = initTextButton("back", false);
        selectorBackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.backToStartMenu(false);
            }
        });
        Array<Progress.Level> progress = MyGame.getProgress().getLevels();
        Array<Container<Stack>> previews = new Array<>(6);
        Array<Label> deaths = new Array<>(6);
        for (int i = 0; i < 6; i++) {
            Progress.Level tmp= progress.get(i);
            previews.add(initLevelPreview(tmp, new ActorGestureListener(){
                @Override
                public void tap(InputEvent event, float x, float y, int count, int button) {
                    screen.loadLevel(LevelBuilder.Level.AGGRESSION);
                }
            }));
            deaths.add(initLabel("deaths", "default", tmp.getDeaths()));
        }

        Table t=tables[MenuScreen.MenuState.LEVEL_SELECTOR.ordinal()];
        t.defaults().align(Align.center).expandX().pad(100, 20, 25, 20);
        t.add(previews.get(0));
        t.add(previews.get(1));
        t.add(previews.get(2)).row();
        t.defaults().pad(0);
        t.add(deaths.get(0));
        t.add(deaths.get(1));
        t.add(deaths.get(2)).row();
        t.defaults().pad(70, 20, 25, 20);
        t.add(previews.get(3));
        t.add(previews.get(4));
        t.add(previews.get(5)).row();
        t.defaults().pad(0);
        t.add(deaths.get(3));
        t.add(deaths.get(4));
        t.add(deaths.get(5)).row();
        t.add().colspan(3).expandY().row();
        t.add(selectorBackButton).align(Align.bottomLeft).colspan(3).expand(false, false);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (isNeedToMoveGradient){
            if (elapsedTime<= ENTER_ANIMATION_DURATION) {
                gradientSprite.translate(0, (ifa?-1:1)*(delta/ENTER_ANIMATION_DURATION)*.5f*height);
                elapsedTime += delta;
            } else {
                isNeedToMoveGradient=false;
                elapsedTime=0;
            }
        }
    }

    private void loadGradient(){
        isNeedToMoveGradient=false;
        elapsedTime =0;
        Color color1 = assets.get(Assets.uiSkin).getColor("light-violet");
        Color color2 = assets.get(Assets.uiSkin).getColor("light-pink");
        gradientSprite=new GradientSprite(new Texture("gfx/white-pixel.png"));
        gradientSprite.setBounds(0, 0, width, height*2);
        gradientSprite.setPosition(0, -.5f*height);
        gradientSprite.setGradientColor(color1, color2, false);
    }

    @Override
    protected void drawBackground() {
        batch.begin();
        gradientSprite.draw(batch);
        batch.end();
    }

    public Table[] getTables() {
        return tables;
    }

    public Table getScreenTable() {
        return screenTable;
    }
}

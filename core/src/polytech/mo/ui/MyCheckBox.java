package polytech.mo.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

import polytech.mo.assets.Assets;
import polytech.mo.core.I18n;
import polytech.mo.utils.Constants;

public class MyCheckBox {
    private Table table;
    private boolean isChecked;
    private CheckBox.CheckBoxStyle cbs;
    private Image image;

    public MyCheckBox(String text, AssetManager assets){
        isChecked=false;
        Skin skin=assets.get(Assets.uiSkin);
        cbs= skin.get("default", CheckBox.CheckBoxStyle.class);
        Label label=new Label(I18n.getString(text), new Label.LabelStyle(Assets.mainRegularFont, skin.getColor("font-regular")));
        image=new Image(cbs.checkboxOff);
        image.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                isChecked=!isChecked;
                image.setDrawable(isChecked?cbs.checkboxOn:cbs.checkboxOff);
            }
        });
        table=new Table();
        table.defaults().align(Align.center);
        table.setBackground(assets.get(Assets.uiSkin).getDrawable("button-background"));
        table.add(image).align(Align.center).size(Constants.CHECKBOX_SIZE);
        table.add(label);
        table.setTransform(true);
    }

    public Table getTable() {
        return table;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        image.setDrawable(isChecked?cbs.checkboxOn:cbs.checkboxOff);
    }

    public Cell getLabelCell(){
        return table.getCells().get(0);
    }

    public Cell getImageCell(){
        return table.getCells().get(1);
    }
}

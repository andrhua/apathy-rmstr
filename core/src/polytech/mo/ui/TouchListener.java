package polytech.mo.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import polytech.mo.audio.SFX;
import polytech.mo.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class TouchListener extends ClickListener {
    private WidgetGroup group;
    private boolean isForwardSound;

    public TouchListener(WidgetGroup button, boolean... args){
        group =button;
        group.setTransform(true);
        if (args.length>0) isForwardSound =args[0]; else isForwardSound =true;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        group.setOrigin(this.group.getWidth() / 2, this.group.getHeight() / 2);
        group.addAction(scaleTo(Constants.BUTTON_SCALE, Constants.BUTTON_SCALE, Constants.BUTTON_SCALE_DURATION));
        SFX.play(isForwardSound?SFX.SoundType.CLICK_POSITIVE: SFX.SoundType.CLICK_NEGATIVE);
        return true;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        group.addAction(scaleTo(1, 1, Constants.BUTTON_SCALE_DURATION));
    }
}

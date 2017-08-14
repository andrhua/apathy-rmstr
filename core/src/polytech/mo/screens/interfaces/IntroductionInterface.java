package polytech.mo.screens.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

import polytech.mo.core.I18n;
import polytech.mo.screens.BaseScreen;
import polytech.mo.screens.IntroductionScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static polytech.mo.ui.UIBuilder.initLabel;

public class IntroductionInterface extends BaseInterface {
    public IntroductionScreen.State state;
    private Label epigraphLabel, determinationLabel;
    private ActorGestureListener listener;

    public IntroductionInterface(BaseScreen screen) {
        super(screen, true);
    }

    @Override
    public void createStage() {
        epigraphLabel= initLabel("epigraph", "default");
        determinationLabel=initLabel("self_determination", "default");

        table.defaults().align(Align.center);
        table.add(epigraphLabel).row();

        listener=new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                switch (state){
                    case EPIGRAPH: setState(IntroductionScreen.State.SELF_DETERMINATION); break;
                    case SELF_DETERMINATION:
                        determinationLabel.addAction(sequence(
                                fadeOut(.7f),
                                run(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((IntroductionScreen)screen).loadGame();
                                    }
                                })
                        ));
                        break;
                }

            }
        };
        getStage().addListener(listener);

        setState(IntroductionScreen.State.EPIGRAPH);
    }

    public void setState(IntroductionScreen.State state){
        this.state=state;
        switch (state){
            case EPIGRAPH:
                epigraphLabel.addAction(fadeIn(.9f)); break;
            case SELF_DETERMINATION:
                epigraphLabel.addAction(sequence(
                        fadeOut(.7f),
                        run(new Runnable() {
                            @Override
                            public void run() {
                                table.clearChildren();
                                table.add(determinationLabel);
                                determinationLabel.setColor(new Color(1, 1, 1, 0));
                                determinationLabel.addAction(fadeIn(.9f));
                            }
                        })
                ));
                break;
        }
    }
}

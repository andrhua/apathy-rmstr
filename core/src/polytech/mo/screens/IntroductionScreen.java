package polytech.mo.screens;

import polytech.mo.core.MyGame;
import polytech.mo.screens.interfaces.IntroductionInterface;

public class IntroductionScreen extends BaseScreen<IntroductionInterface> {

    public enum State {EPIGRAPH, SELF_DETERMINATION}

    public IntroductionScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        MyGame.getSettings().setIsIntroduced(true);
    }

    public void loadGame(){
        game.switchScreen(MyGame.ScreenType.GAME);
    }

    @Override
    public void setUI() {
        UI=new IntroductionInterface(this);
    }

}

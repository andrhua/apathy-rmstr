package polytech.mo.screens;

import polytech.mo.core.MyGame;
import polytech.mo.screens.interfaces.GameInterface;

public class GameScreen extends BaseScreen<GameInterface> {
    public GameScreen(MyGame game) {
        super(game);
    }

    @Override
    public void setUI() {
        UI =new GameInterface(this);
    }
}

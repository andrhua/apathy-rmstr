package polytech.mo.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GradientSprite extends Sprite {

    public GradientSprite (Texture white){
        setRegion(white);
    }

    public void setGradientColor(Color a, Color b, boolean isHorizontal){
        float vertices[]=getVertices();
        float ca=a.toFloatBits();
        float cb=b.toFloatBits();
        vertices[SpriteBatch.C1]=isHorizontal?ca:cb;
        vertices[SpriteBatch.C2]=ca;
        vertices[SpriteBatch.C3]=isHorizontal?cb:ca;
        //vertices[SpriteBatch.C4]=cb;
    }
}

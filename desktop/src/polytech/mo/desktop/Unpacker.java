package polytech.mo.desktop;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TextureUnpacker;

public class Unpacker {
    public static void main(String...args){
        TextureUnpacker unpacker =new TextureUnpacker();
        unpacker.splitAtlas(new TextureAtlas.TextureAtlasData(
                        new FileHandle("in/backgrounds.atlas"),
                        new FileHandle("in"),
                        false
                ),
                "unpacked");
    }
}

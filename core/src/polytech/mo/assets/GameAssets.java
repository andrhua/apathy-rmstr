package polytech.mo.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;

public class GameAssets {

    private AssetManager manager;

    public GameAssets(){
        manager=new AssetManager(new InternalFileHandleResolver());

    }

    public AssetManager getManager() {
        return manager;
    }

}

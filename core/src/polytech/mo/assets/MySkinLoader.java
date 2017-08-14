package polytech.mo.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

public class MySkinLoader extends SkinLoader {

    public MySkinLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Skin loadSync(AssetManager manager, String fileName, FileHandle file, SkinParameter parameter) {
        String textureAtlasPath = file.pathWithoutExtension() + ".atlas";
        ObjectMap<String, Object> resources = null;
        if (parameter != null) {
            if (parameter.textureAtlasPath != null){
                textureAtlasPath = parameter.textureAtlasPath;
            }
            if (parameter.resources != null){
                resources = parameter.resources;
            }
        }
        TextureAtlas atlas = manager.get(textureAtlasPath, TextureAtlas.class);
        Skin skin = new Skin(atlas);
        if (resources != null) {
            for (ObjectMap.Entry<String, Object> entry : resources.entries()) {
                skin.add(entry.key, entry.value);
            }
        }
        skin.load(file);
        return skin;
    }
}

package polytech.mo.game.levels;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.async.AsyncTask;

import polytech.mo.assets.GameAssets;

public class LevelBuilder /*implements AsyncTask<BaseLevel>*/ {
    private final static String
            JOE = "joe", PANHANDLER = "panhandler",
            PLATFORM = "platform", DISPOSABLE_FLASHING_PLATFORM = "disposable_flashing_" + PLATFORM, DISAPPEARING_PLATFORM = "disappearing_" + PLATFORM,
            TRANSFORMABLE_PLATFORM = "transformable_" + PLATFORM, MOVING_PLATFORM = "moving_" + PLATFORM, FAKE_PLATFORM = "fake_" + PLATFORM,
            BEAUTEOUS_PLATFORM = "beauteous_" + PLATFORM,
            SMASH_PLATFORM = "smash_" + PLATFORM,
            INDICATOR = "indicator", DEATH_INDICATOR = "death_" + INDICATOR, GRAVITY_SWITCHER = "gravity_switcher",
            SPIKE = "spike", TIMING_SPIKE = "timing_" + SPIKE,
            AMPHIBIAN = "amphibian", MUK = "muk", WOOMBA = "woomba", DRAGON = "dragon", CLOUD = "cloud", SHURIKEN = "shuriken",
            OCTOPUS = "octopus", SPIDER = "spider", BIRD = "bird", GHOST = "ghost", PACMAN_GHOST = "pacman_" + GHOST, BLASTOISE = "blastoise",
            BLADE = "blade", FAKE_BLADE = "fake_" + BLADE, INVISIBLE_BLADE = "invisible_" + BLADE,
            COIN = "coin", PREQUARK = "prequark", COLLECTIBLE = "collectible", BANANA = "banana",
            WATER = "water", TRAFFIC_LIGHT = "traffic_light", GRAVITY_ANOMALY = "gravity_anomaly", TELEPORT = "teleport", PRISON_TIMER = "prison_timer",
            SPRITE = "sprite", HINT = "hint", BACKGROUND = "background", OVERLAP = "overlap", DIALOG = "dialog",
            SEPARATOR = "_";
    public enum Level {NEGATION, AGGRESSION, BARGAINING, DEPRESSION, ADOPTION, DEATH}
    private Level level;

    public LevelBuilder(Level level){
        this.level=level;
    }
    /*

    @Override
    public BaseLevel call() throws Exception {
        new GameAssets();
        TmxMapLoader tmxMapLoader=new TmxMapLoader();
        TmxMapLoader.Parameters parameters=new TmxMapLoader.Parameters();
        parameters.flipY=true;
        TiledMap map=tmxMapLoader.load("levels/".concat(level.name().toLowerCase()).concat(".tmx"), parameters);
        BaseLevel tmp=null;
        Layer[]layers=initLayers(map.getLayers());
        switch (level){
            case NEGATION: tmp=new NegationLevel(layers); break;
            case AGGRESSION: tmp=new AggressionLevel(layers); break;
            case BARGAINING: tmp=new BargainingLevel(layers); break;
        }
        return tmp;
    }

    private GameObject[] initObjects(MapObjects objects){
        GameObject[] gameObjects=new GameObject[objects.getCount()];
        for (int i=0; i<objects.getCount(); i++){
            MapObject object=objects.get(i);
            GameObject gameObject=null;
            switch (object.getName()){
                case JOE:
            }
            gameObjects[i]=gameObject;
        }
        return gameObjects;
    }

    private Layer[] initLayers(MapLayers mapLayers){
        Layer[] layers=new Layer[mapLayers.getCount()];
        for (int i=0; i<layers.length; i++){
            MapLayer mapLayer=mapLayers.get(i);
            layers[i]=new Layer(initObjects(mapLayer.getObjects()), mapLayer.isVisible());
        }
        return layers;
    }
    */


}

package polytech.mo.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashMap;

import polytech.mo.assets.Assets;
import polytech.mo.audio.Music;
import polytech.mo.profile.Progress;
import polytech.mo.profile.Settings;
import polytech.mo.screens.AboutScreen;
import polytech.mo.screens.BaseScreen;
import polytech.mo.screens.GameScreen;
import polytech.mo.screens.IntroductionScreen;
import polytech.mo.screens.LevelSelectorScreen;
import polytech.mo.screens.SettingsScreen;
import polytech.mo.screens.SplashScreen;
import polytech.mo.screens.StartMenuScreen;
import polytech.mo.utils.Constants;

public class MyGame extends Game {
    private static Music music;
	private static Assets assets;
	private static Settings settings;
	private static Progress progress;
	private static Camera camera;
	private static FitViewport viewport;
	private static InputAdapter emptyInputProccessor;
	public enum ScreenType {SPLASH, START, SETTINGS, ABOUT, INTRODUCTION, LEVEL_SELECTOR, GAME}
	private ScreenType currentScreenType;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		emptyInputProccessor=new InputAdapter();
		new Constants();
		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, camera);
		viewport.apply();
		camera.translate(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
		camera.update();
		assets=new Assets();
		switchScreen(ScreenType.SPLASH);
	}

	@Override
	public void render() {
		camera.update();
        super.render();
        if (music!=null) music.update(Gdx.graphics.getDeltaTime());
	}

	public void switchScreen(ScreenType screenType){
		Gdx.app.log("screen", screenType.name());
		BaseScreen tmp=null;
		switch (screenType) {
			case GAME:tmp = new GameScreen(this);break;
			case SPLASH:tmp = new SplashScreen(this);break;
			case START:tmp = new StartMenuScreen(this, currentScreenType!=ScreenType.LEVEL_SELECTOR);break;
			case SETTINGS:tmp = new SettingsScreen(this);break;
			case ABOUT: tmp=new AboutScreen(this); break;
			case INTRODUCTION: tmp=new IntroductionScreen(this); break;
			case LEVEL_SELECTOR: tmp=new LevelSelectorScreen(this, currentScreenType==ScreenType.START); break;
		}
		currentScreenType=screenType;
		setScreen(tmp);

	}

	@Override
	public void pause() {
		settings.write();
		progress.write();
		super.pause();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	public static void initAsyncMusic(){
		Timer.Task musicTask=new Timer.Task() {
			@Override
			public void run() {
				Music.start();
			}
		};
		musicTask.run();
	}

	public static FitViewport getViewport() {
		return viewport;
	}

	public static Camera getCamera(){ return camera;}

	public static Assets getAssets() {
		return assets;
	}

	public static Settings getSettings() {
		return settings;
	}

	public static void setSettings(Settings settings) {
		MyGame.settings = settings;
	}

	public static Progress getProgress() {
		return progress;
	}

	public static void setProgress(Progress progress) {
		MyGame.progress = progress;
	}

	public static InputAdapter getEmptyInputProccessor() {
		return emptyInputProccessor;
	}

    public static void setMusic(Music music) {
        MyGame.music = music;
        initAsyncMusic();
    }

}

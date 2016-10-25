package kana.main;

import javafx.stage.Stage;
import kana.gui.canvasPage.CanvasSceneCtrl;
import kana.sceneTransition.Launcher;

public final class MainApp extends Launcher{

	@Override
	public void start(Stage primaryStage) {
		super.start(primaryStage);

		getStage().setTitle("たいとる");

		moveScene(new CanvasSceneCtrl());
	}

	private static Launcher instance = null;

	@Override
	protected void setInstance(Launcher me) {
		instance = me;
	}

	/**
	 * インスタンスを取得するよ。
	 * @return GUILauncherのインスタンス。
	 */
	public static Launcher getInstance(){
		return instance;
	}




}

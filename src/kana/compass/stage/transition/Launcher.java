package kana.compass.stage.transition;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Launcher extends Application {

	private Stage stage = null;
	private SceneCtrl sceneCtrl;

	@Override
	public void start(Stage primaryStage) {
		// インスタンス
		instance = this;

		// set ステージ
		stage = primaryStage;
	}

	public void moveScene(SceneCtrl ctrl) {
		Scene scene = new Scene(ctrl.getRoot());

		stage.setScene(scene);

		ctrl.init();

		sceneCtrl = ctrl;
	}


	private static Launcher instance = null;

	public static Launcher getInstance(){ return instance; }

	public final Stage getStage() { return stage; }

	public final SceneCtrl getSceneCtrl() { return sceneCtrl; }

}


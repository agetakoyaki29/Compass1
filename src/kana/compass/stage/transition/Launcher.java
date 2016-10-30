package kana.compass.stage.transition;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Launcher extends Application {

	private boolean isStart = false;
	private Stage stage = null;
	private SceneCtrl sceneCtrl;

	@Override
	public void start(Stage primaryStage) {
		isStart = true;

		// インスタンス
		setInstance(this);

		// set ステージ
		stage = primaryStage;

		stage.show();
	}

	public void moveScene(SceneCtrl ctrl) {
		Scene scene = new Scene(ctrl.getRoot());

		stage.setScene(scene);

		ctrl.init();

		sceneCtrl = ctrl;
	}

	public final boolean isStart() { return isStart; }

	public final Stage getStage() { return stage; }

	public final SceneCtrl getSceneCtrl() { return sceneCtrl; }

	// ---- abstract ----

	/**
	 * staticなinstenceにsetするために使う
	 */
	protected abstract void setInstance(Launcher me);

}


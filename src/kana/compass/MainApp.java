package kana.compass;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kana.compass.gui.drawScene.DrawSceneCtrl;
import kana.compass.stage.transition.Launcher;


public final class MainApp extends Launcher{

	@Override
	public void start(Stage stage) {
		super.start(stage);

		getStage().setTitle("たいとる");

		Pane root = new Pane();
		root.setPrefWidth(200);
		root.setPrefHeight(200);
		stage.setScene(new Scene(root));
		stage.centerOnScreen();

		moveScene(new DrawSceneCtrl());
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


	public static void main(String[] args) {
		launch(args);
	}

}

package kana.compass;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kana.compass.gui.drawScene.DrawSceneCtrl;
import kana.compass.gui.startScene.StartSceneCtrl;
import kana.compass.stage.transition.Launcher;


public final class MainApp extends Launcher{

	private final Stage toy = new Stage();

	public MainApp() {
		toy();
	}

	@Override
	public void start(Stage stage) {
		super.start(stage);

		stage.setTitle("たいとる");
		stage.setWidth(100);
		stage.setHeight(100);
		stage.centerOnScreen();

		moveScene(new DrawSceneCtrl());
		stage.show();
		toy.hide();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void toy() {
		StartSceneCtrl startSceneCtrl = new StartSceneCtrl();
		toy.setScene(new Scene(startSceneCtrl.getRoot()));

		toy.initStyle(StageStyle.UNDECORATED);
		toy.centerOnScreen();

		toy.show();
	}

}

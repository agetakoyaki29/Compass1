package kana.compass;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kana.compass.gui.drawScene.DrawSceneCtrl;
import kana.compass.stage.transition.Launcher;


public final class MainApp extends Launcher{

	private final Stage toy;

	public MainApp() {
		toy = new Stage();
		toy();
	}

	@Override
	public void start(Stage stage) {
		super.start(stage);

//		stage.setTitle("たいとる");
//		stage.setWidth(100);
//		stage.setHeight(100);
//		stage.centerOnScreen();

		moveScene(new DrawSceneCtrl());
		stage.show();
		toy.hide();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void toy() {
		toy.initStyle(StageStyle.TRANSPARENT);
		toy.setWidth(300);
		toy.setHeight(200);
		toy.centerOnScreen();

		toy.setScene(new Scene(new AnchorPane(new Canvas())));
		toy.show();
	}

}

package kana.compass.stage.transition;

import javafx.stage.Stage;

public abstract class SceneCtrl extends Ctrl {

	public abstract void init();

	public Stage getStage() { return (Stage) getWindow(); }

}

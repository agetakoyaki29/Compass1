package kana.compass.gui.menuBar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import kana.compass.gui.drawScene.DrawSceneCtrl;
import kana.compass.stage.transition.Ctrl;

public class MenuBarCtrl extends Ctrl {

	@FXML private MenuItem close;

	private DrawSceneCtrl canvasPageController;

	public MenuBarCtrl(DrawSceneCtrl canvasPageController) {
		super();

		this.canvasPageController = canvasPageController;

		close.setOnAction(e -> System.out.println(1));
	}

	@FXML
	private void delete(ActionEvent event) {
		System.out.println("hi");
	}

}

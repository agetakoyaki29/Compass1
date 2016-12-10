package kana.compass.gui.drawScene.menuBar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import kana.compass.stage.transition.Ctrl;

public class MenuBarCtrl extends Ctrl {

	@FXML private MenuItem close;

	public MenuBarCtrl() {
		super();

		close.setOnAction(e -> System.out.println(1));
	}

	@FXML
	private void delete(ActionEvent event) {
		System.out.println("hi");
	}

}

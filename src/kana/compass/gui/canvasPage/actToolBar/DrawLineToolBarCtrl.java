package kana.compass.gui.canvasPage.actToolBar;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;


public class DrawLineToolBarCtrl extends ActToolBarCtrl {

	public Consumer<Boolean> onAxisAlined = DONO;

	@FXML
	private void axisAligned(ActionEvent event) {
		CheckBox target = (CheckBox) event.getTarget();
		onAxisAlined.accept(target.isSelected());
	}

	public void clearOnAxisAlined() {
		onAxisAlined = DONO;
	}

	public static Consumer<Boolean> DONO = e->{};

}

package kana.gui.canvasPage.hot;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import kana.logic.ActionManager;


public class DrawLineToolBarCtrl extends HotToolBarCtrl {

	public Consumer<Boolean> onAxisAlined = ActionManager.getEmpty();

	@FXML
	private void axisAligned(ActionEvent event) {
		CheckBox target = (CheckBox) event.getTarget();
		onAxisAlined.accept(target.isSelected());
	}

}

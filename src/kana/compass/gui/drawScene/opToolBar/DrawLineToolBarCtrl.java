package kana.compass.gui.drawScene.opToolBar;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import kana.compass.logic.OperationCenter.Operation;
import kana.compass.util.Util;


public class DrawLineToolBarCtrl extends OpToolBarCtrl {

	@FXML private CheckBox axisAlinCheckBox;
	@FXML private TextField angleTField;


	public DrawLineToolBarCtrl(Operation op) {
		super(op);

		ObjectProperty<String> angleTextProperty = Util.textProperty(angleTField);
		BooleanProperty aaSelectedProperty = axisAlinCheckBox.selectedProperty();

		// bind
		StringConverter<Boolean> conv1 = new StringConverter<Boolean>() {
			@Override public String toString(Boolean value) {
				return value ? "" : angleTField.getText();
			}
			@Override public Boolean fromString(String value) {
				return value!="" ? false : axisAlinCheckBox.isSelected();
			}
		};
		Bindings.bindBidirectional(angleTextProperty, aaSelectedProperty, conv1);

		// axis aline action
		aaSelectedProperty.addListener((v, o, n) -> {
			getOp().push("axisAline", n);
		});

		// angleTField action
		angleTextProperty.addListener((v, o, n) -> {
			Double d = Util.parseDouble(angleTField.getText());
			getOp().push("angle", d);
		});
	}

}

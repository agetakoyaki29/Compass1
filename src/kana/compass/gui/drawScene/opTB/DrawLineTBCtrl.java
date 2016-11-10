package kana.compass.gui.drawScene.opTB;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import kana.compass.logic.MyChangeListener;
import kana.compass.logic.OpCentral;
import kana.compass.util.MyDoubleStringConverter;
import kana.compass.util.Util;


public class DrawLineTBCtrl extends OpTBCtrl {

	private final MyChangeListener<Boolean> aaSelectedCListener;
	private final MyChangeListener<Double> angleCListener;

	@FXML private CheckBox axisAlignCheckBox;
	@FXML private TextField angleTField;


	public DrawLineTBCtrl(OpCentral central) {
		super(central);

		// local var
		ObjectProperty<String> angleWrtnProp = Util.writtenProperty(angleTField);
		BooleanProperty axisAlignSlctProp = axisAlignCheckBox.selectedProperty();
		ObjectBinding<Double> angleWrtnDoubleBinding = Util.fromStringBinding(new MyDoubleStringConverter(), angleWrtnProp);

		// manage prop
		angleWrtnProp.addListener((v, o, n) -> {
			if(!n.equals("")) axisAlignSlctProp.set(false);
		});
		axisAlignSlctProp.addListener((v, o, n) -> {
			if(n.equals(true)) angleWrtnProp.set("");
		});

		// add listener to push
		aaSelectedCListener = new SPCL<>("axisAlign", axisAlignSlctProp);
		angleCListener = new SPCL<>("angle", angleWrtnDoubleBinding);
	}

	@Override
	public void repush() {
		aaSelectedCListener.call();
		angleCListener.call();
	}

}

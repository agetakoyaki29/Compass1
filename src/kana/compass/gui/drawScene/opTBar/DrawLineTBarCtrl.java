package kana.compass.gui.drawScene.opTBar;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import kana.compass.logic.OpCentral;
import kana.compass.util.MyChangeListener;
import kana.compass.util.Util;
import kana.compass.util.strConv.SafeDoubleStrConv;


public class DrawLineTBarCtrl extends OpTBarCtrl {

	private final MyChangeListener<Boolean> aaSelectedCListener;
	private final MyChangeListener<Double> angleCListener;

	@FXML private CheckBox axisAlignCheckBox;
	@FXML private TextField angleTField;


	public DrawLineTBarCtrl(OpCentral central) {
		super(central);

		// local var
		ObjectProperty<String> angleWrtnProp = Util.writtenProperty(angleTField);
		BooleanProperty axisAlignSlctProp = axisAlignCheckBox.selectedProperty();
		ObjectBinding<Double> angleWrtnDoubleBinding = Util.fromStringBinding(new SafeDoubleStrConv(), angleWrtnProp);

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

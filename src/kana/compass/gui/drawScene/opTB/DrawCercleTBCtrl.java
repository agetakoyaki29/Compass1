package kana.compass.gui.drawScene.opTB;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import kana.compass.drawn.hot.HotCercle;
import kana.compass.drawn.hot.HotCercle.PointingState;
import kana.compass.logic.MyChangeListener;
import kana.compass.logic.OpCentral;
import kana.compass.util.Util;
import kana.compass.util.strConv.JunkObjStrConv;
import kana.compass.util.strConv.SafeDoubleStrConv;


public class DrawCercleTBCtrl extends OpTBCtrl {

	private final MyChangeListener<HotCercle.PointingState> pointing;
	private final MyChangeListener<Double> rangeCListener;

	@FXML private ComboBox<HotCercle.PointingState> ptngCBox;
	@FXML private TextField rangeTField;


	public DrawCercleTBCtrl(OpCentral central) {
		super(central);

		// init comb box
		ptngCBox.setConverter(new JunkObjStrConv<HotCercle.PointingState>());
		ptngCBox.setItems(FXCollections.observableArrayList(HotCercle.PointingState.values()));
		ptngCBox.setValue(HotCercle.PointingState.P2);	// init value

		// local var
		ObjectProperty<PointingState> ptngValue = ptngCBox.valueProperty();
		ObjectProperty<String> rangeWrtnProp = Util.writtenProperty(rangeTField);
		ObjectBinding<Double> rangeWrtnDoubleBinding = Util.fromStringBinding(new SafeDoubleStrConv(), rangeWrtnProp);

		// manage

		// add listener to push
		pointing = new SPCL<HotCercle.PointingState>("pointing", ptngValue );
		rangeCListener = new SPCL<>("range", rangeWrtnDoubleBinding);
	}

	@Override
	public void repush() {
		pointing.call();
	}

}

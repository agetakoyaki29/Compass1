package kana.compass.gui.drawScene.opToolBar;

import kana.compass.logic.OperationCenter.Operation;
import kana.compass.stage.transition.Ctrl;


public abstract class OpToolBarCtrl extends Ctrl {

	private final Operation op;

	public OpToolBarCtrl(Operation op) {
		this.op = op;
	}

	public Operation getOp() { return op; }

}

package kana.compass.gui.drawScene.opTBar;

import javafx.beans.value.ObservableValue;
import kana.compass.logic.MyChangeListener;
import kana.compass.logic.OpCentral;
import kana.compass.stage.transition.Ctrl;


public abstract class OpTBarCtrl extends Ctrl {

	private final OpCentral central;

	public OpTBarCtrl(OpCentral central) {
		this.central = central;
	}

	public OpCentral getOpCentral() { return central; }

	public abstract void repush();

	/**
	 * Simple Push my Change Listener
	 */
	protected class SPCL<T> extends MyChangeListener<T> {
		private final String name;
		public SPCL(String name, ObservableValue<T> observablevalue) {
			super(observablevalue);
			this.name = name;
			this.addListener();
		}
		@Override
		public void changed(T n) {
			getOpCentral().push(name, n);
		}
	}

}

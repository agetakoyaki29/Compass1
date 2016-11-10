package kana.compass.gui.drawScene.opTB;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import kana.compass.logic.OpCentral;
import kana.compass.util.Util;


public class OpTBFactory {

	private final Map<Class<? extends OpTBCtrl>, OpTBCtrl> map = new HashMap<>();

	private final OpCentral central;

	private OpTBCtrl opTBCtrl = null;

	public OpTBFactory(OpCentral central) {
		this.central = central;
	}

	public OpTBCtrl getOpTBCtrl() { return opTBCtrl; }

	public Node makeOpTB(Class<? extends OpTBCtrl> clazz) {
		if( map.containsKey(clazz) ) {
			opTBCtrl = map.get(clazz);
		} else {
			opTBCtrl = Util.instantiate(clazz, central);
			map.put(clazz, opTBCtrl);
		}
		return opTBCtrl.getRoot();
	}

	public void repush() {
		opTBCtrl.repush();
	}

	// ----

	public static final Class<? extends OpTBCtrl> NON = OpTBCtrl.class;

}

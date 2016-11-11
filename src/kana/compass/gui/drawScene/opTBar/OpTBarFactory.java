package kana.compass.gui.drawScene.opTBar;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import kana.compass.logic.OpCentral;
import kana.compass.util.Util;


public class OpTBarFactory {

	private final Map<Class<? extends OpTBarCtrl>, OpTBarCtrl> map = new HashMap<>();

	private final OpCentral central;

	private OpTBarCtrl opTBarCtrl = null;

	public OpTBarFactory(OpCentral central) {
		this.central = central;
	}

	public OpTBarCtrl getOpTBCtrl() { return opTBarCtrl; }

	public Node makeOpTBar(Class<? extends OpTBarCtrl> clazz) {
		if( map.containsKey(clazz) ) {
			opTBarCtrl = map.get(clazz);
		} else {
			opTBarCtrl = Util.instantiate(clazz, central);
			map.put(clazz, opTBarCtrl);
		}
		return opTBarCtrl.getRoot();
	}

	public void repush() {
		opTBarCtrl.repush();
	}

	// ----

	public static final Class<? extends OpTBarCtrl> NON = OpTBarCtrl.class;

}

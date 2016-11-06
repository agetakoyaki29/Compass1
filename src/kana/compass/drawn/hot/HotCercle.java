package kana.compass.drawn.hot;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Drawn;
import kana.compass.geometry.Geo;
import kana.compass.gui.drawScene.opToolBar.DrawCercleToolBarCtrl;
import kana.compass.gui.drawScene.opToolBar.OpToolBarCtrl;
import kana.compass.logic.OperationCenter;
import kana.compass.logic.OperationCenter.HotDrawn;
import kana.compass.logic.Pen;


public class HotCercle extends HotDrawn {
	private Point2D center = null;
	private Double range = null;
	private Cercle pre = null;


	public HotCercle(OperationCenter outer) {
		outer.super();
	}

	@Override
	protected void began() {
		center = null;
		range = null;
		pre = null;
		outer().setStatusText("中心点");
	}

	@Override
	public void preDraw(Pen pen) {
		if(pre == null) return;
		pre.draw(pen);
	}

	@Override
	public void prePushPoint(Point2D pt) {
		double range = Geo.distance(center, pt);
		pre = new Cercle(center, range);
	}

	@Override
	protected Set<Drawn> makeColds() {
		Set<Drawn> ret = new HashSet<>();
		ret.add(pre);
		return ret;
	}

	@Override
	public void pushPoint(Point2D pt) {
		if(center == null) {
			center = pt;
			beginPreDraw();
			outer().setStatusText("半径");
		} else if(isPreDraw()) {
			prePushPoint(pt);
			finish();
		}
	}

	@Override
	public OpToolBarCtrl getOpToolBarCtrl() {
		return new DrawCercleToolBarCtrl(this);
	}

}

package kana.compass.drawn.hot;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import kana.compass.canvas.Pen;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Drawn;
import kana.compass.geometry.Geo;
import kana.compass.gui.drawScene.opTBar.DrawCercleTBarCtrl;
import kana.compass.gui.drawScene.opTBar.OpTBarCtrl;
import kana.compass.logic.OpCentral;
import kana.compass.logic.OpCentral.HotDrawn;


public class HotCercle extends HotDrawn {
	private Point2D center = null;
	private Double range = null;
	private Cercle pre = null;


	public HotCercle(OpCentral outer) {
		outer.super(outer);
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
	public Class<? extends OpTBarCtrl> getDefaultOpTBarCtrl() {
		return DrawCercleTBarCtrl.class;
	}

	public void pushPointing(PointingState pointing) {
		System.out.println(pointing);
	}

	public void pushRange(Double d) {
		System.out.println(d);
	}

	// ---- class PointingState ----

	public static enum PointingState {
		P2 { @Override public String toString() {
			return "２点指定";
		}},
		P3 { @Override public String toString() {
			return "３点指定";
		}},
		CR { @Override public String toString() {
			return "中心=>半径";
		}},
	}

}

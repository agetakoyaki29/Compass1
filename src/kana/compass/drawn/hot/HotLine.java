package kana.compass.drawn.hot;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import kana.compass.canvas.Pen;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Line;
import kana.compass.geometry.Geo;
import kana.compass.gui.drawScene.opTBar.DrawLineTBarCtrl;
import kana.compass.gui.drawScene.opTBar.OpTBarCtrl;
import kana.compass.logic.OpCentral;
import kana.compass.logic.OpCentral.HotDrawn;


public class HotLine extends HotDrawn {
	private Point2D startPt;
	private Line pre;

	private Point2D align = null;
	private boolean axisAlign = false;


	public HotLine(OpCentral outer) {
		outer.super(outer);
	}

	@Override
	protected void began() {
		startPt = null;
		pre = null;
		outer().setStatusText("開始点");
	}

	@Override
	public void preDraw(Pen pen) {
		if(pre == null) return;
		pre.draw(pen);
	}

	@Override
	public void prePushPoint(Point2D pt) {
		Point2D stopPt = alinedPt(pt);
		pre = new Line(startPt, stopPt);
	}

	private Point2D alinedPt(Point2D pt) {
		if(axisAlign) {
			Point2D v = pt.subtract(startPt);
			if(Math.abs(v.getX()) < Math.abs(v.getY())) {
				return new Point2D(startPt.getX(), pt.getY());
			} else {
				return new Point2D(pt.getX(), startPt.getY());
			}
		} else if(align != null) {
			Line straight = new Line(startPt, startPt.add(align));
			return straight.closet(pt);
		}
		return pt;
	}

	@Override
	protected Set<Drawn> makeColds() {
		Set<Drawn> ret = new HashSet<>();
		ret.add(pre);
		return ret;
	}

	@Override
	public void pushPoint(Point2D pt) {
		if(startPt == null) {
			startPt = pt;
			beginPreDraw();
			outer().setStatusText("終了点");
		} else if(isPreDraw()) {
			Point2D stopPt = alinedPt(pt);
			if(startPt.equals(stopPt)) {
				outer().showPopup("同一点です");
				return;
			}
			pre = new Line(startPt, stopPt);
			finish();
		}
	}

	@Override
	public Class<? extends OpTBarCtrl> getDefaultOpTBarCtrl() {
		return DrawLineTBarCtrl.class;
	}

	public void pushAngle(Double angle) {
		System.out.println(angle);
		align = angle==null ? null : Geo.onUnitCercle(angle);
	}

	public void pushAxisAlign(boolean axisAlign) {
		this.axisAlign = axisAlign;
	}

}

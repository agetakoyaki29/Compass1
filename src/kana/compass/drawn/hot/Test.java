package kana.compass.drawn.hot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Line;
import kana.compass.gui.drawScene.actToolBar.ActToolBarCtrl;
import kana.compass.gui.drawScene.actToolBar.DrawLineToolBarCtrl;
import kana.compass.logic.ActionCenter;
import kana.compass.logic.Pen;
import kana.compass.logic.ActionCenter.HotDrawn;


public class Test extends HotDrawn {
	private Dot pt1 = null;
	private Dot pt2 = null;

	private Double angle = null;


	public Test(ActionCenter outer) {
		outer.super();
	}

	@Override
	protected void began() {
		outer().setStatusText("１点目！");
	}

	@Override
	public void draw(Pen pen) {
		if(angle != null) {
		}
		pen.strokeLine(pt1.getPt(), pt2.getPt());
	}

	@Override
	protected Set<Drawn> makeColds() {
		if(angle != null) {
		}
		Drawn cold = new Line(pt1, pt2);
		return new HashSet<Drawn>(Arrays.asList(cold));
	}

	@Override
	public void pushDot(Dot pt) {
		if(pt1 == null) {
			pt1 = pt;
			beginPreDraw();
			outer().setStatusText("２点目！");
		} else if(isPreDraw()) {
			pt2 = pt;
			end();
		}
	}

	public void pushAngle(Double angle) {
		this.angle = angle;
	}

	@Override
	public ActToolBarCtrl getActToolBarCtrl() {
		return new DrawLineToolBarCtrl();
	}

	@Override
	public void prePushDot(Dot pt) {
		pt2 = pt;
	}

}

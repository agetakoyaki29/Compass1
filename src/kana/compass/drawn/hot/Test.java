package kana.compass.drawn.hot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Line;
import kana.compass.gui.canvasPage.actToolBar.ActToolBarCtrl;
import kana.compass.gui.canvasPage.actToolBar.DrawLineToolBarCtrl;
import kana.compass.logic.ActionManager;
import kana.compass.logic.ActionManager.HotDrawn;


public class Test extends HotDrawn {
	private Dot pt1 = null;
	private Dot pt2 = null;

	private Double angle = null;


	public Test(ActionManager outer) {
		outer.super();
	}

	@Override
	public void draw(GraphicsContext gc) {
		Dot pt2 = this.pt2;
		if(angle != null) {

		}
		gc.strokeLine(pt1.getPt().getX(), pt1.getPt().getY(), pt2.getPt().getX(), pt2.getPt().getY());
	}

	@Override
	protected Set<Drawn> makeColds() {
		Dot pt2 = this.pt2;
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

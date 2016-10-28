package kana.drawn.hot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.Drawn;
import kana.drawn.Line;
import kana.drawn.Point;
import kana.gui.canvasPage.actToolBar.ActToolBarCtrl;
import kana.gui.canvasPage.actToolBar.DrawLineToolBarCtrl;
import kana.logic.ActionManager;
import kana.logic.ActionManager.HotDrawn;


public class Test extends HotDrawn {
	private Point pt1 = null;
	private Point pt2 = null;

	private Double angle = null;


	public Test(ActionManager outer) {
		outer.super();
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pt2 = this.pt2;
		if(angle != null) {

		}
		System.out.println("aa");
		gc.strokeLine(pt1.getV().x, pt1.getV().y, pt2.getV().x, pt2.getV().y);
	}

	@Override
	protected Set<Drawn> makeColds() {
		Point pt2 = this.pt2;
		if(angle != null) {

		}
		Drawn cold = new Line(pt1, pt2);
		return new HashSet<Drawn>(Arrays.asList(cold));
	}

	@Override
	public void pushPoint(Point pt) {
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
	public void prePushPoint(Point pt) {
		pt2 = pt;
	}

}

package kana.logic;

import java.util.function.Consumer;

import javafx.scene.input.MouseEvent;
import kana.drawn.CenterPoint;
import kana.drawn.Cercle;
import kana.drawn.Drawn;
import kana.drawn.FreePoint;
import kana.drawn.Point;
import kana.drawn.geometry.Geo;
import kana.drawn.geometry.Vector;
import kana.drawn.hot.HotCercle;
import kana.drawn.hot.HotLine;
import kana.gui.canvasPage.CanvasMouseHandler;
import kana.gui.canvasPage.CanvasSceneCtrl;
import kana.gui.canvasPage.hot.DrawCercleToolBarCtrl;
import kana.gui.canvasPage.hot.DrawLineToolBarCtrl;
import kana.gui.canvasPage.hot.HotToolBarCtrl;


public class ActionManager {

	private final CanvasSceneCtrl ctrl;
	private final CanvasMouseHandler handler;
	private final Paper paper;
	private final HotPaper hotPaper;
	private final DrawnPool pool;

	private HotToolBarCtrl hotToolBarCtrl = null;

	public ActionManager(CanvasSceneCtrl ctrl, CanvasMouseHandler handler, Paper paper, HotPaper hotPaper) {
		this.ctrl = ctrl;
		this.handler = handler;
		this.paper = paper;
		this.hotPaper = hotPaper;
		this.pool = paper.getPool();
	}

	public void callRepaint() {
		paper.repaint();
	}

	// TODO 以下テスト

	public void drawLine() {
		clearOns();
		hotToolBarCtrl = new DrawLineToolBarCtrl();
		ctrl.setHotToolBar(hotToolBarCtrl);

		tempLine();
	}
	public void drawCercle() {
		clearOns();
		hotToolBarCtrl = new DrawCercleToolBarCtrl();
		ctrl.setHotToolBar(hotToolBarCtrl);

		tempCercle();
	}

	public static final <T> Consumer<T> getEmpty() {
		return e->{};
	}

	private void clearOns() {
		handler.clearOnMoved();
		handler.clearOnLeftClicked();
		handler.clearOnRightClicked();
		hotPaper.drawing = null;
		hotPaper.repaint();
	}

	private void tempLine() {
		HotLine cur = new HotLine();
		hotPaper.drawing = cur;

		//pt1 = getMousePt();
		Consumer<MouseEvent> whenPt2 = event -> {
			cur.pt2 = new FreePoint(event);
			hotPaper.repaint();
		};
		Consumer<Point> getPt2 = pt -> {
			cur.pt2 = pt;
			if(cur.pt1.equals(cur.pt2)) {
				ctrl.showSimplePopup("同一点です");
				return;
			}
			paper.add(cur.makeCold());
			hotPaper.drawing = null;
			hotPaper.repaint();

			handler.clearOnMoved();
			tempLine();
		};
		Consumer<Point> getPt1 = pt -> {
			cur.pt1 = pt;

			handler.onMoved = whenPt2;
			setOnLeftClicked4FreePoint(getPt2);
			setOnRightClicked4ReadPoint(getPt2);

			ctrl.setNowText("終点を");
		};

		setOnLeftClicked4FreePoint(getPt1);
		setOnRightClicked4ReadPoint(getPt1);

		ctrl.setNowText("始点を");
	}

	private void tempCercle() {
		HotCercle cur = new HotCercle();
		hotPaper.drawing = cur;

		//pt1 = getMousePt();
		Consumer<MouseEvent> whenPt2 = event -> {
			cur.pt2 = new FreePoint(event);
			hotPaper.repaint();
		};
		Consumer<Point> getPt2 = pt -> {
			cur.pt2 = pt;
			if(cur.pt1.equals(cur.pt2)) {
				ctrl.showSimplePopup("同一点です");
				return;
			}
			hotPaper.drawing = null;
			Drawn cercle = cur.makeCold();
			paper.add(cercle);
			paper.add(new CenterPoint((Cercle) cercle));
			hotPaper.repaint();

			handler.clearOnMoved();
			tempCercle();
		};
		Consumer<Point> getPt1 = pt -> {
			cur.pt1 = pt;

			handler.onMoved = whenPt2;
			setOnLeftClicked4FreePoint(getPt2);
			setOnRightClicked4ReadPoint(getPt2);

			ctrl.setNowText("1点目を");
		};

		setOnLeftClicked4FreePoint(getPt1);
		setOnRightClicked4ReadPoint(getPt1);

		ctrl.setNowText("2点目を");
	}

	private void setOnLeftClicked4FreePoint(Consumer<Point> consumer) {
		handler.onLeftClicked = event -> {
			Point pt = new FreePoint(event);
			consumer.accept(pt);
		};
	}

	private void setOnRightClicked4ReadPoint(Consumer<Point> consumer) {
		handler.onRightClicked = event -> {
			// TODO too heavy
			double r = 5*5;
			Point t = null;
			double distance = r;
			Vector mouse = new FreePoint(event).getV();

			for (Point pt : pool.getPoints()) {
				Vector v = pt.getV();
				double d = Geo.sqeDistance(mouse, v);
				if(d > r) continue;
				if(d > distance) continue;
				distance = d;
				t = pt;
			}

			if(t == null) {
				ctrl.showSimplePopup("点が見つかりません");
				return;
			}
			consumer.accept(t);
		};
	}

}

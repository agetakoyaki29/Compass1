package kana.logic;

import java.util.function.Consumer;

import javafx.scene.input.MouseEvent;
import kana.drawn.CenterPoint;
import kana.drawn.Cercle;
import kana.drawn.Drawn;
import kana.drawn.FreePoint;
import kana.drawn.hot.HotCercle;
import kana.drawn.hot.HotLine;
import kana.gui.canvasPage.CanvasListener;


public class ActionManager {

	private CanvasListener listener;

	private Paper paper;
	private HotPaper hotPaper;

	public ActionManager(CanvasListener listener, Paper paper, HotPaper hotPaper) {
		this.listener = listener;
		this.paper = paper;
		this.hotPaper = hotPaper;
	}

	public void tempLine() {
		HotLine cur = new HotLine();
		hotPaper.drawing = cur;

		//pt1 = getMousePt();
		Consumer<MouseEvent> whenPt2 = event -> {
			cur.pt2 = new FreePoint(event);
			hotPaper.repaint();
		};
		Consumer<MouseEvent> getPt2 = event -> {
			cur.pt2 = new FreePoint(event);
			hotPaper.drawing = null;
			paper.add(cur.makeCold());

			listener.moved = e->{};
			hotPaper.repaint();
			listener.leftClicked = e->{};
			tempLine();
		};
		Consumer<MouseEvent> getPt1 = event -> {
			cur.pt1 = new FreePoint(event);

			listener.moved = whenPt2;
			listener.leftClicked = getPt2;
		};

		listener.leftClicked = getPt1;
	}

	public void tempCercle() {
		HotCercle cur = new HotCercle();
		hotPaper.drawing = cur;

		//pt1 = getMousePt();
		Consumer<MouseEvent> whenPt2 = event -> {
			cur.pt2 = new FreePoint(event);
			hotPaper.repaint();
		};
		Consumer<MouseEvent> getPt2 = event -> {
			cur.pt2 = new FreePoint(event);
			hotPaper.drawing = null;
			Drawn cercle = cur.makeCold();
			paper.add(cercle);
			paper.add(new CenterPoint((Cercle) cercle));

			listener.moved = e->{};
			hotPaper.repaint();
			listener.leftClicked = e->{};
			tempCercle();
		};
		Consumer<MouseEvent> getPt1 = event -> {
			cur.pt1 = new FreePoint(event);

			listener.moved = whenPt2;
			listener.leftClicked = getPt2;
		};

		listener.leftClicked = getPt1;
	}

	public void callRepaint() {
		paper.repaint();
	}

}

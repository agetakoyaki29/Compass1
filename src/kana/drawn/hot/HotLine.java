package kana.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.Drawn;
import kana.drawn.Line;
import kana.drawn.Point;


public class HotLine extends HotDrawn {

	public Point pt1 = null;
	public Point pt2 = null;

	@Override
	public void draw(GraphicsContext gc) {
		if(pt2 == null || pt1 == null) return;

		gc.strokeLine(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
	}

	public Drawn makeCold() {
		return new Line(pt1, pt2);
	}

}

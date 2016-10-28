package kana.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.Drawn;
import kana.drawn.Line;
import kana.drawn.Point;
import kana.drawn.geometry.Pen;


public class HotLine extends OldHotDrawn {

	public Point pt1 = null;
	public Point pt2 = null;

	@Override
	public void draw(GraphicsContext gc) {
		if(pt2 == null || pt1 == null) return;

		Pen.strokeLine(gc, pt1, pt2);
	}

	public Drawn makeCold() {
		return new Line(pt1, pt2);
	}

}

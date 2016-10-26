package kana.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import kana.drawn.Cercle;
import kana.drawn.Drawn;
import kana.drawn.Point;

public class HotCercle extends HotDrawn {

	public Point pt1 = null;
	public Point pt2 = null;

	@Override
	public void draw(GraphicsContext gc) {
		Point c = Point.midPoint(pt1, pt2);
		double x = c.getX();
		double y = c.getY();
		double r = Point.distance(c, pt1);
		gc.strokeArc(x-r, y-r, 2*r, 2*r, 0, 360, ArcType.OPEN);
	}

	@Override
	public Drawn makeCold() {
		return new Cercle(pt1, pt2);
	}

}

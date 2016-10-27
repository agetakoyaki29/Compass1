package kana.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.Cercle;
import kana.drawn.Drawn;
import kana.drawn.Point;
import kana.drawn.geometry.Geo;
import kana.drawn.geometry.Pen;
import kana.drawn.geometry.Vector;

public class HotCercle extends HotDrawn {

	public Point pt1 = null;
	public Point pt2 = null;

	@Override
	public void draw(GraphicsContext gc) {
		Vector c = Geo.midPoint(pt1.getV(), pt2.getV());
		double r = Geo.distance(c, pt1.getV());
		Pen.strokeCercle(gc, c, r);
	}

	@Override
	public Drawn makeCold() {
		return new Cercle(pt1, pt2);
	}

}

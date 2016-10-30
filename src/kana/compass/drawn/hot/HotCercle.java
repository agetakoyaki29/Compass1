package kana.compass.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Point;
import kana.compass.geometry.Geo;
import kana.compass.geometry.Pen;
import kana.compass.geometry.Vector;

public class HotCercle extends OldHotDrawn {

	public Point pt1 = null;
	public Point pt2 = null;

	@Override
	public void draw(GraphicsContext gc) {
		// validate
		Vector c = Geo.midPoint(pt1.getV(), pt2.getV());
		double r = Geo.distance(c, pt1.getV());
		Pen.strokeCercle(gc, c, r);
	}

	@Override
	public Drawn makeCold() {
		return new Cercle(pt1, pt2);
	}

}

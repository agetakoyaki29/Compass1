package kana.compass.drawn.hot;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.geometry.Geo;
import kana.compass.geometry.Pen;


public class HotCercle extends OldHotDrawn {

	public Dot pt1 = null;
	public Dot pt2 = null;

	public Point2D getCenter() {
		return pt1.getPt().midpoint(pt2.getPt());
	}

	public double getRange() {
		return Geo.distance(pt1.getPt(), pt2.getPt()) / 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point2D c = getCenter();
		double r = getRange();
		Pen.strokeCercle(gc, c, r);
	}

	@Override
	public Drawn makeCold() {
		return new Cercle(pt1, pt2);
	}

}

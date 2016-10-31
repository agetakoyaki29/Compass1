package kana.compass.drawn.hot;

import javafx.geometry.Point2D;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.geometry.Bound2D;
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
	public void draw(Pen pen) {
		if(pt2 == null || pt1 == null) return;

		Point2D c = getCenter();
		double r = getRange();
		Point2D rr = new Point2D(r, r);
		Bound2D bounds = new Bound2D(c.subtract(rr), c.add(rr));

		pen.strokeCercle(bounds);
	}

	@Override
	public Drawn makeCold() {
		return new Cercle(pt1, pt2);
	}

}

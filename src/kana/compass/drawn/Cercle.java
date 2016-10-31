package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;
import kana.compass.logic.Pen;

public class Cercle extends Drawn {

	public final Dot dot1;
	public final Dot dot2;

	public Cercle(Dot dot1, Dot dot2) {
		dot1.addParent();
		dot2.addParent();
		this.dot1 = dot1;
		this.dot2 = dot2;
	}

	public Dot getDot1() {
		return dot1;
	}

	public Dot getDot2() {
		return dot2;
	}

	public Point2D getCenter() {
		return dot1.getPt().midpoint(dot2.getPt());
	}

	public double getRange() {
		return Geo.distance(dot1.getPt(), dot2.getPt()) / 2;
	}

	@Override
	public void draw(Pen pen) {
		Point2D c = getCenter();
		double r = getRange();
		Point2D rr = new Point2D(r, r);
		Bound2D bounds = new Bound2D(c.subtract(rr), c.add(rr));
		pen.strokeCercle(bounds);
	}

//	@Override
//	public Box getBoundingBox() {
//		Point2D c = getCenter();
//		double r = getRange();
//		Point2D rr = new Point2D(r, r);
//		return new Box(c.subtract(rr), c.add(rr));
//	}

	@Override
	public List<Drawn> getComponents() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(dot1);
		ret.add(dot2);
		return ret;
	}

}

package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;
import kana.compass.logic.Pen;

public class Arc extends Drawn {

	public final Dot dot1;
	public final Dot dot2;
	private final double startAngle;
	private final double arcExtent;

	public Arc(Dot dot1, Dot dot2, double startAngle, double arcExtent) {
		this.dot1 = dot1;
		this.dot2 = dot2;
		this.startAngle = startAngle;
		this.arcExtent = arcExtent;
	}

	@Override
	public void draw(Pen pen) {
		pen.strokeArc(getCercleBounds(), startAngle, arcExtent);
//		Point2D center = getCenter();
//		double range = getRange();
//		Point2D start = Geo.arcPoint(center, range, startAngle);
//		Point2D stop = Geo.arcPoint(center, range, stopAngle());
//		pen.strokeLine(start, stop);
	}

	@Override
	public Bound2D getBoundingBox() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Drawn> getComponents() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(dot1);
		ret.add(dot2);
		return ret;
	}

	// ---- ----

	public double stopAngle() {
		return startAngle + arcExtent;
	}

	public Point2D getCenter() {
		return dot1.getPt().midpoint(dot2.getPt());
	}

	public double getRange() {
		return Geo.distance(dot1.getPt(), dot2.getPt()) / 2;
	}

	public Bound2D getCercleBounds() {
		Point2D c = getCenter();
		double r = getRange();
		Point2D rr = new Point2D(r, r);
		return new Bound2D(c.subtract(rr), c.add(rr));
	}

}

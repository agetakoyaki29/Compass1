package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;
import kana.compass.logic.Pen;

public class Arc extends Drawn {

	private final Point2D center;
	private final double range;
	private final double startAngle;
	private final double arcExtent;

	public Arc(Point2D center, double range, double startAngle, double arcExtent) {
		this.center = center;
		this.range = range;
		this.startAngle = startAngle;
		this.arcExtent = arcExtent;
	}

	@Override
	public void draw(Pen pen) {
		Point2D min = Geo.diagonally(center, -range);
		Point2D size = new Point2D(2*range, 2*range);
		pen.strokeArc(min, size, startAngle, arcExtent);
	}

	@Override
	public Bound2D getBoundingBox() {
		Point2D startPt = getStartPt();
		double minX, minY, maxX, maxY;
		minX = maxX = startPt.getX();
		minY = maxY = startPt.getY();

		double start = (startAngle / 90 );
		double stop = (getStopAngle() / 90 );
		for(int i=(int)Math.floor(start); i<=stop; i++) {
			// TODO
		}

		return new Bound2D(new Point2D(minX, minY), new Point2D(maxX, maxY));
	}

	@Override
	public List<Point2D> getPts() {
		List<Point2D> ret = new ArrayList<>();
		ret.add(getStartPt());
		ret.add(getStopPt());
		return ret;
	}

	// ---- ----

	public double getStopAngle() {
		return startAngle + arcExtent;
	}

	public Point2D getStartPt() {
		return Geo.onCercle(center, range, startAngle);
	}

	public Point2D getStopPt() {
		return Geo.onCercle(center, range, getStopAngle());
	}

}

package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;
import kana.compass.logic.Pen;

public class Cercle extends Drawn {

	private final Point2D center;
	private final double range;

	public Cercle(Point2D center, double range) {
		this.center = center;
		this.range = range;
	}

	@Override
	public void draw(Pen pen) {
		pen.strokeCercle( getBoundingBox() );
	}

	@Override
	public Bound2D getBoundingBox() {
		Point2D min = Geo.diagonally(center, -range);
		Point2D max = Geo.diagonally(center, +range);
		return new Bound2D(min, max);
	}

	@Override
	public List<Point2D> getPts() {
		List<Point2D> ret = new ArrayList<>();
		return ret;
	}

}

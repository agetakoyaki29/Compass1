package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.canvas.Pen;
import kana.compass.geometry.Bound2D;


public abstract class Dot extends Drawn {

	@Override
	public void draw(Pen pen) {
		double x = getPt().getX();
		double y = getPt().getY();
		double l = pen.get1Pixel();
		pen.strokeLine(x-l, y-l, x+l, y+l);
		pen.strokeLine(x+l, y-l, x-l, y+l);
	}

	@Override
	public Bound2D getBoundingBox() {
		return new Bound2D(getPt(), getPt());
	}

	@Override
	public List<Point2D> getPts() {
		List<Point2D> ret = new ArrayList<>();
		ret.add(getPt());
		return ret;
	}

	public abstract Point2D getPt();

	@Override
	public String toString() {
		Point2D pt = getPt();
		return "Dot[" + pt.getX() + ", " + pt.getY() + "]";
	}

}

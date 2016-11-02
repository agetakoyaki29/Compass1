package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.geometry.Bound2D;
import kana.compass.logic.Pen;


public abstract class Dot extends Drawn {

	@Override
	public void draw(Pen pen) {
		double x = getPt().getX();
		double y = getPt().getY();
		double l = pen.get1Pixel();
		System.out.println(l);
		pen.strokeLine(x, y-l, x, y+l);
		pen.strokeLine(x-l, y, x+l, y);
	}

	@Override
	public Bound2D getBoundingBox() {
		return new Bound2D(getPt(), getPt());
	}

	@Override
	public List<Drawn> getComponents() {	// TODO coordinate?
		ArrayList<Drawn> ret = new ArrayList<>();
		return ret;
	}

	public abstract Point2D getPt();

	@Override
	public String toString() {
		Point2D pt = getPt();
		return "Dot[" + pt.getX() + ", " + pt.getY() + "]";
	}

}

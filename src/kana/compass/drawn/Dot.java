package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.logic.Pen;


public abstract class Dot extends Drawn {

	@Override
	public void draw(Pen pen) {
		double x = getPt().getX();
		double y = getPt().getY();
		pen.strokeLine(x, y-1, x, y+1);
		pen.strokeLine(x-1, y, x+1, y);
	}

//	@Override
//	public Box getBoundingBox() {
//		return new Box(getPt(), getPt());
//	}

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

package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class FreeDot extends Dot {
	private Point2D pt;

	public FreeDot(double x, double y) {
		this.pt = new Point2D(x, y);
	}

	public FreeDot(Point2D pt) {
		this.pt = pt;
	}

	@Override
	public List<Drawn> getComponents() {
		return new ArrayList<>();
	}

	@Override
	public Point2D getPt() {
		return pt;
	}

	public void setPt(Point2D pt) {
		this.pt = pt;
	}
	public void setX(double x) {
		double y = pt.getY();
		pt = new Point2D(x, y);
	}
	public void setY(double y) {
		double x = pt.getX();
		pt = new Point2D(x, y);
	}

}

package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;


public class MidDot extends Dot {
	private final Dot dot1;
	private final Dot dot2;

	public MidDot(Dot dot1, Dot dot2) {
		this.dot1 = dot1;
		this.dot2 = dot2;
	}

	@Override
	public List<Drawn> getComponents() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(dot1);
		ret.add(dot1);
		return ret;
	}

	@Override
	public Point2D getPt() {
		Point2D pt1 = dot1.getPt();
		Point2D pt2 = dot2.getPt();
		return pt1.midpoint(pt2);
	}

}

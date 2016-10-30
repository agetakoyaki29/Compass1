package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class CenterDot extends Dot {
	public final Cercle cercle;

	public CenterDot(Cercle cercle) {
		this.cercle = cercle;
		cercle.addParent();
	}

	@Override
	public List<Drawn> getComponents() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(cercle);
		return ret;
	}

	@Override
	public Point2D getPt() {
		Point2D pt1 = cercle.dot1.getPt();
		Point2D pt2 = cercle.dot2.getPt();
		return pt1.midpoint(pt2);
	}

}

package kana.logic;

import java.util.ArrayList;
import java.util.Collection;

import kana.logic.drawns.Drawn;
import kana.logic.drawns.Point;

public class DrawnPool {

	private ArrayList<Drawn> list = new ArrayList<Drawn>();
	private ArrayList<Point> points = new ArrayList<Point>();

	public void add(Drawn drawn) {
		list.add(drawn);
		points.addAll( drawn.getPoints() );
	}

	public Collection<Drawn> getDrawn() {
		return list;
	}

	public Collection<Point> getPoints() {
		return points;
	}

}

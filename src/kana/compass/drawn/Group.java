package kana.compass.drawn;

import java.util.LinkedList;
import java.util.List;

import kana.compass.geometry.Pen;


public class Group extends DNode {

	List<Drawn> drawns = new LinkedList<>();

	@Override
	public void draw(Pen pen) {
		for (Drawn drawn : drawns) {
			drawn.draw(pen);
		}
	}

	@Override
	public List<Drawn> getDrawns() {
		return drawns;
	}

	public void add(Drawn drawn) {
		drawn.addParent();
		drawns.add(drawn);
	}

}

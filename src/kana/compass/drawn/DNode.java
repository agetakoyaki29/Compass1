package kana.compass.drawn;

import java.util.List;

import kana.compass.canvas.Pen;

public abstract class DNode {

	public abstract void draw(Pen pen);
	public abstract List<Drawn> getDrawns();

}

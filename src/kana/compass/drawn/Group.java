package kana.compass.drawn;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;


public class Group extends DNode {

	List<Drawn> drawns = new LinkedList<>();

	@Override
	public void draw(GraphicsContext gc) {
		for (Drawn drawn : drawns) {
			drawn.draw(gc);
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

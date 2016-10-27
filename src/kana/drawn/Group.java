package kana.drawn;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.geometry.Box;


public class Group extends Drawn {

	List<Drawn> children = new LinkedList<>();

	@Override
	public void draw(GraphicsContext gc) {
		for (Drawn drawn : children) {
			drawn.draw(gc);
		}
	}

	@Override
	public List<Drawn> getChildren() {
		return children;
	}

	public void add(Drawn drawn) {
		drawn.addParent();
		drawn.setInGroup(this);
		children.add(drawn);
	}

	@Override
	public Box getBoundBox() {
		return Box.zero;
	}

}

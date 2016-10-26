package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Group extends Drawn {

	ArrayList<Drawn> children = new ArrayList<>();

	@Override
	public void draw(GraphicsContext gc) {
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

}

package kana.logic.drawns;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;

public abstract class Drawn {

	public abstract void draw(GraphicsContext gc);

	public abstract Collection<Drawn> getChildren();

	public Collection<Point> getPoints() {
		ArrayList<Point> ret = new ArrayList<>();

		if(this instanceof Point) ret.add((Point) this);

		for (Drawn drawn : getChildren()) {
			ret.addAll( drawn.getPoints() );
		}

		return ret;
	}

}

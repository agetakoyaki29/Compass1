package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.geometry.Box;
import kana.drawn.geometry.Vector;


public abstract class Point extends Drawn {

	@Override
	public void draw(GraphicsContext gc) {
		double x = getV().x;
		double y = getV().y;
		gc.strokeLine(x, y-1, x, y+1);
		gc.strokeLine(x-1, y, x+1, y);
	}

	@Override
	public Box getBoundBox() {
		return new Box(getV(), getV());
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		return ret;
	}

	public abstract Vector getV();

}

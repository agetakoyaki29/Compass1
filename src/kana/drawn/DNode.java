package kana.drawn;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public abstract class DNode {

	public abstract void draw(GraphicsContext gc);
	public abstract List<Drawn> getDrawns();

}

package kana.compass.canvas;

import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Layer;
import kana.compass.logic.DrawnPool;
import kana.compass.logic.ScopeTransform;


public class Paper extends AbstractPaper{

	private DrawnPool pool = new DrawnPool();

	public Paper(GraphicsContext gc, ScopeTransform scope) {
		super(gc, scope);

		gc.setFill(null);
		gc.setStroke(Color.BLACK);
		clearWhole();
	}

	@Override
	public void repaint() {
		clearWhole();

//		for (Drawn drawn : pool.getList()) {
//			drawn.draw(gc);
//		}
		for (Layer layer : pool.getLayers()) {
			layer.draw(getPen());
		}
	}

	public void add(Drawn drawn) {
		pool.add(drawn);
		drawn.draw(getPen());
	}

	public void addAll(Collection<? extends Drawn> drawns) {
		for (Drawn drawn : drawns) {
			add(drawn);
		}
	}

	public void delete(Collection<Drawn> del) {
		// TODO
		repaint();
	}

	public DrawnPool getPool() {
		return pool;
	}

}

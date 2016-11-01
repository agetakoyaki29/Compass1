package kana.compass.logic;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.FreeDot;
import kana.compass.drawn.Layer;
import kana.compass.drawn.Line;


public class Paper extends AbstractPaper{

	private DrawnPool pool = new DrawnPool();

	public Paper(GraphicsContext gc, ScopeTransform scope) {
		super(gc, scope);

		gc.setFill(null);
		gc.setStroke(Color.BLACK);
		clearWhole();

		// TODO for test
		this.add(new Line(new FreeDot(10, 12), new FreeDot(44, 51)));
		this.add(new Cercle(new ArrayList<Dot>(pool.getDots()).get(1), new FreeDot(100, 100)));
		this.add(new Cercle(new FreeDot(200, 100), new FreeDot(100, 200)));
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

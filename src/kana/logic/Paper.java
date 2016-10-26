package kana.logic;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.drawn.Cercle;
import kana.drawn.Drawn;
import kana.drawn.FreePoint;
import kana.drawn.Layer;
import kana.drawn.Line;
import kana.drawn.Point;


public class Paper extends AbstractPaper{

	private DrawnPool pool = new DrawnPool();

	public Paper(GraphicsContext gc) {
		super(gc);

		gc.setFill(null);
		gc.setStroke(Color.BLACK);
		clearWhole();

		// TODO for test
		this.add(new Line(new FreePoint(10, 12), new FreePoint(44, 51)));
		this.add(new Cercle(new ArrayList<Point>(pool.getPoints()).get(1), new FreePoint(100, 100)));
		this.add(new Cercle(new FreePoint(200, 100), new FreePoint(100, 200)));
	}

	@Override
	public void repaint() {
		clearWhole();

//		for (Drawn drawn : pool.getList()) {
//			drawn.draw(gc);
//		}
		for (Layer layer : pool.getLayers()) {
			layer.paint(gc);
		}
	}

	public void add(Drawn drawn) {
		pool.add(drawn);
		drawn.draw(gc);
	}

	public void delete(Collection<Drawn> del) {
		// TODO
		repaint();
	}

}

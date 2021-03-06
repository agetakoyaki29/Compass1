package kana.compass.drawn;

import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.canvas.Pen;
import kana.compass.geometry.Bound2D;


public abstract class Drawn {

	// ---- abstract ----

	public abstract void draw(Pen pen);

	public abstract List<Point2D> getPts();

	public abstract Bound2D getBoundingBox();

}

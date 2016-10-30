package kana.compass.drawn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;

public abstract class Drawn {

	private int parentCount = 0;

	public void addParent() {
		parentCount++;
	}
	public void reduceParent() {
		parentCount--;
	}

	/**
	 * 子孫にわたる
	 */
	public Set<Dot> getDots() {
		HashSet<Dot> ret = new HashSet<>();

		if(this instanceof Dot) ret.add((Dot) this);

		for (Drawn drawn : getComponents()) {
			ret.addAll( drawn.getDots() );
		}

		return ret;
	}

	public final void delete() {
		// TODO parent の存在価値
		for (Drawn drawn : getComponents()) {
			drawn.reduceParent();
			if(drawn.parentCount <= 0) {
				drawn.delete();
			}
		}
	}

	// ---- abstract ----

	public abstract void draw(GraphicsContext gc);

	/**
	 * 当代かぎり
	 */
//	public abstract Box getBoundingBox();

	public abstract List<Drawn> getComponents();

}

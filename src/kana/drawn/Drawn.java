package kana.drawn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.geometry.Box;

public abstract class Drawn {

	private int parentCount = 0;
	private Group inGroup = null;

	public void addParent() {
		parentCount++;
	}
	public void reduceParent() {
		parentCount--;
	}

	/**
	 * 子孫にわたる
	 */
	public Set<Point> getPoints() {
		HashSet<Point> ret = new HashSet<>();

		if(this instanceof Point) ret.add((Point) this);

		for (Drawn drawn : getChildren()) {
			ret.addAll( drawn.getPoints() );
		}

		return ret;
	}

	public final void delete() {
		// TODO parent の存在価値
		for (Drawn drawn : getChildren()) {
			drawn.reduceParent();
			if(drawn.parentCount <= 0) {
				drawn.delete();
			}
		}
	}

	public Group getInGroup() { return inGroup; }
	public void setInGroup(Group group) { inGroup = group; }

	// ---- abstract ----

	public abstract void draw(GraphicsContext gc);

	/**
	 * 当代かぎり
	 */
	public abstract Box getBoundBox();

	public abstract List<Drawn> getChildren();

}
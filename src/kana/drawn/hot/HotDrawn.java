package kana.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.Drawn;

public abstract class HotDrawn {

	public abstract void draw(GraphicsContext gc);

	public abstract Drawn makeCold();

}

package kana.compass.drawn.hot;

import javafx.scene.canvas.GraphicsContext;
import kana.compass.drawn.Drawn;

public abstract class OldHotDrawn {

	public abstract void draw(GraphicsContext gc);

	public abstract Drawn makeCold();

}

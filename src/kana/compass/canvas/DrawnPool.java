package kana.compass.canvas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point2D;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Layer;

public class DrawnPool {

	private Set<Point2D> points = new HashSet<>();
	private Set<Drawn> drawns = new HashSet<Drawn>();

	private final int layerSize = 10;
	private ArrayList<Layer> layers = new ArrayList<>();
	private Layer layer;

	public DrawnPool() {
		for(int i=0; i<layerSize; i++) {
			Layer layer = new Layer();
			layers.add(layer);
		}
		this.layer = layers.get(0);
	}

	public void add(Drawn drawn) {
		layer.add(drawn);
		points.addAll( drawn.getPts() );
	}

	public Set<Point2D> getPts() {
		return points;
	}

	public List<Layer> getLayers() {
		return layers;
	}

}

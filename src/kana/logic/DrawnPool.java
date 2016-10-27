package kana.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kana.drawn.Drawn;
import kana.drawn.Layer;
import kana.drawn.Point;

public class DrawnPool {

	//private List<Drawn> list = new ArrayList<>();	// TODO 存在価値が謎
	private Set<Point> points = new HashSet<>();

	private final int layerSize = 10;
	private ArrayList<Layer> layers = new ArrayList<>();
	private Layer layer;

	public DrawnPool() {
		for(int i=0; i<layerSize; i++) {
			Layer layer = new Layer();
			layer.addParent();
			layers.add(layer);
		}
		this.layer = layers.get(0);
	}

	public void add(Drawn drawn) {
		layer.add(drawn);
		points.addAll( drawn.getPoints() );
	}

	public Set<Point> getPoints() {
		return points;
	}

	public List<Layer> getLayers() {
		return layers;
	}

}

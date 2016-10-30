package kana.compass.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Layer;

public class DrawnPool {

	//private List<Drawn> list = new ArrayList<>();	// TODO 存在価値が謎
	private Set<Dot> dots = new HashSet<>();

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
		dots.addAll( drawn.getDots() );
	}

	public Set<Dot> getDots() {
		return dots;
	}

	public List<Layer> getLayers() {
		return layers;
	}

}

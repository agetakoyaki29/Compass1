package kana.compass.logic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import kana.compass.geometry.Bound2D;
import kana.compass.util.MyRuntimeException;


public final class ScopeTransform {

	private DoubleProperty powerProperty = new SimpleDoubleProperty(1);
	private final Affine affine = new Affine();

	private final ObjectProperty<Point2D> centerProperty = new SimpleObjectProperty<Point2D>(Point2D.ZERO);


	public ScopeTransform(ObservableValue<Point2D> centerProperty) {
		this.centerProperty.bind(centerProperty);
		initTrans();
	}

	public ReadOnlyDoubleProperty powerProperty() {
		return powerProperty;
	}
	public ObjectProperty<Point2D> centerProperty() {
		return centerProperty;
	}


	public void appendTranslation(Point2D delta) {
		affine.appendTranslation(delta.getX(), delta.getY());
	}

	public void appendScale(double scale) {
		appendScale(scale, centerProperty.get());
	}
	public void appendScale(double scale, Point2D displayPivot) {
		if(scale <= 0) throw new MyRuntimeException("scale>0, plz");
		powerProperty.set(powerProperty.get() * scale);
		Point2D pivot = inverseTransform(displayPivot);
		affine.appendScale(scale, scale, pivot);
	}

	public void setScale(double scale) {
		if(scale <= 0) throw new MyRuntimeException("scale>0, plz");
		double appendScale = scale / powerProperty.get();
		appendScale(appendScale);
	}

	public void initTrans() {
		affine.setToIdentity();
		appendTranslation(centerProperty.get());
		powerProperty.set(1);
	}

	// ----

	public Point2D transform(Point2D pt) {
		return affine.transform(pt);
	}

	public Point2D inverseTransform(Point2D pt) {
		try {
			return affine.inverseTransform(pt);
		} catch (NonInvertibleTransformException e) {
			throw new MyRuntimeException(e);
		}
	}

	public Point2D inverseDeltaTransform(Point2D delta) {
		try {
			return affine.inverseDeltaTransform(delta);
		} catch (NonInvertibleTransformException e) {
			throw new MyRuntimeException(e);
		}
	}

	public Bound2D transform(Bound2D bounds) {
		Point2D min = affine.transform(bounds.getMin());
		Point2D max = affine.transform(bounds.getMax());
		return new Bound2D(min, max);
	}

	public Bound2D inverseTransform(Bound2D bounds) {
		try {
			Point2D min = affine.inverseTransform(bounds.getMin());
			Point2D max = affine.inverseTransform(bounds.getMax());
			return new Bound2D(min, max);
		} catch (NonInvertibleTransformException e) {
			throw new MyRuntimeException(e);
		}
	}

}

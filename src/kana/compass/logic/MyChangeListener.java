package kana.compass.logic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public abstract class MyChangeListener<T> implements ChangeListener<T> {

	private final ObservableValue<? extends T> observablevalue;

	public MyChangeListener(ObservableValue<? extends T> observablevalue) {
		this.observablevalue = observablevalue;
	}

	@Override
	public void changed(ObservableValue<? extends T> v, T o, T n) {
		changed(n);
	}

	public abstract void changed(T n);

	public void addListener() {
		observablevalue.addListener(this);
	}

	public void removeListener() {
		observablevalue.removeListener(this);
	}

	public void call() {
		changed(observablevalue.getValue());
	}

}

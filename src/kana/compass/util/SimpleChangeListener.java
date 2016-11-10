package kana.compass.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

@FunctionalInterface
public interface SimpleChangeListener<T> extends ChangeListener<T> {

	default void changed(ObservableValue<? extends T> v, T o, T n) {
		changed(n);
	}

	void changed(T n);

}

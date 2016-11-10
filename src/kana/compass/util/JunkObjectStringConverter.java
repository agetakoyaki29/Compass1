package kana.compass.util;

import javafx.util.StringConverter;

public class JunkObjectStringConverter<T> extends StringConverter<T> {

	@Override
	public String toString(T value) {
		return value.toString();
	}

	@Override
	public T fromString(String string) {
		return null;
	}

}

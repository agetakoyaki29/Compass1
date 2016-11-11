package kana.compass.util.strConv;

import javafx.util.StringConverter;

public class JunkObjStrConv<T> extends StringConverter<T> {

	@Override
	public String toString(T value) {
		return value.toString();
	}

	@Override
	public T fromString(String string) {
		return null;
	}

}

package kana.compass.util;

import javafx.util.StringConverter;

public class PercentStringConverter extends StringConverter<String> {

	@Override
	public String toString(String object) {
        if (object == null) return "";

        object = object.trim();

        if (object.length() < 1) return "";

        double d = Double.valueOf(object);

        return String.format("%.1f", d*100);
	}

	@Override
	public String fromString(String string) {
        return string;
	}


}

package kana.compass.util.strConv;

import javafx.util.StringConverter;

public class SafeDoubleStrConv extends StringConverter<Double> {
    @Override public Double fromString(String value) {
        // If the specified value is null or zero-length, return null
        if (value == null) {
            return null;
        }

        value = value.trim();

        if (value.length() < 1) {
            return null;
        }

		try {
			return Double.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
    }

    @Override public String toString(Double value) {
        // If the specified value is null, return a zero-length String
        if (value == null) {
            return "";
        }

        return Double.toString(value.doubleValue());
    }
}

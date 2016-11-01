package test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Test1 {
	private double power = 1;

	public Test1(DoubleProperty doubleProperty) {
		// doubleProperty.set => setPower
		// multiply => set power => doubleProperty.set
	}

	public void multiply(double multiplier) {
		power *= multiplier;
		process(multiplier);
	}

	public void setPower(double distPower) {
		double multiplier = distPower / power;
		multiply(multiplier);
	}

	private void process(double multiplier) {
		// ...
	}


	public static void main(String[] args) {
		DoubleProperty display = new SimpleDoubleProperty();
		Test1 obj = new Test1(display);
		display.set(2);		// setPower(2) => multiply(2) => process(2)
		obj.multiply(3);	// process(3) & display's value 6
	}

}

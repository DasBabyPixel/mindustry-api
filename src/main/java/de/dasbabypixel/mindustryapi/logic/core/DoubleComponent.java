package de.dasbabypixel.mindustryapi.logic.core;

public class DoubleComponent extends PrimitiveComponent<Double> {

	public static final double DEFAULT_VALUE = 0;

	public DoubleComponent(double value) {
		super(DEFAULT_VALUE, value);
	}

	@Override
	public Component copy() {
		return new DoubleComponent(value);
	}
}

package de.dasbabypixel.mindustryapi.logic.core;

public class LongComponent extends PrimitiveComponent<Long> {

	public static final long DEFAULT_VALUE = 0;

	public LongComponent(long value) {
		super(DEFAULT_VALUE, value);
	}

	@Override
	public Component copy() {
		return new LongComponent(value);
	}
}

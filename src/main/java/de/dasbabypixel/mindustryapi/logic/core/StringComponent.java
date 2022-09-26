package de.dasbabypixel.mindustryapi.logic.core;

public class StringComponent extends PrimitiveComponent<String> {

	public static final String DEFAULT_VALUE = "";

	public StringComponent(String value) {
		super(DEFAULT_VALUE, value);
	}

	@Override
	public String toMindustryCode() {
		return String.format("\"%s\"", super.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new StringComponent(value);
	}
}

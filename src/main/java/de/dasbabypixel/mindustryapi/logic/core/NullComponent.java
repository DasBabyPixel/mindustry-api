package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.api.property.NumberValue;

public class NullComponent implements Component {

	private final NumberValue height = NumberValue.zero();

	@Override
	public String toMindustryCode() {
		return "null";
	}

	@Override
	public NumberValue height() {
		return height;
	}

	@Override
	public Component copy() {
		return new NullComponent();
	}

}

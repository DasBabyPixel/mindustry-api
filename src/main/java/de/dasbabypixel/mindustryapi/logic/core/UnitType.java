package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.api.property.NumberValue;

public enum UnitType implements Component {

	MONO("@mono"), POLY("@poly");

	private final String code;
	private final NumberValue height = NumberValue.zero();

	private UnitType(String code) {
		this.code = code;
	}

	@Override
	public String toMindustryCode() {
		return code;
	}

	@Override
	public NumberValue height() {
		return height;
	}

	@Override
	public Component copy() {
		return this;
	}

}

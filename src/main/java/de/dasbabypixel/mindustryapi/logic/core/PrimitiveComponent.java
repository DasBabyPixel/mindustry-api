package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.api.property.NumberValue;

public abstract class PrimitiveComponent<T> implements Component {

	private final NumberValue height = NumberValue.constant(-1);
	
	protected final T defaultValue;

	protected final T value;

	public PrimitiveComponent(T defaultValue, T value) {
		this.defaultValue = defaultValue;
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toMindustryCode() {
		T value = getValue();
		return (value == null ? (defaultValue == null ? "null" : defaultValue) : value).toString();
	}

	@Override
	public NumberValue height() {
		return height;
	}

}

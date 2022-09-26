package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.api.property.NumberValue;

public abstract class AbstractComponent implements Component {

//	private final Collection<JumpComponent> jumps = new HashSet<>();

//	private boolean hasLine = false;

	private final NumberValue line = NumberValue.withValue(0);

	private final NumberValue height = NumberValue.withValue(-1);

	public AbstractComponent(int height) {
		this.height.setNumber(height);
	}
	
	@Override
	public final NumberValue height() {
		return height;
	}

	public final NumberValue line() {
		return line;
	}

//	protected int calculateHeight() {
//		throw new UnsupportedOperationException("Require calculateHeight() in " + this.getClass().getSimpleName());
//	}
//
//
//	boolean hasLine() {
//		return hasLine;
//	}
//
//	protected void setCalculatedLine(int calculatedLine) {
//		this.calculatedLine = calculatedLine;
//		hasLine = true;
//	}

}

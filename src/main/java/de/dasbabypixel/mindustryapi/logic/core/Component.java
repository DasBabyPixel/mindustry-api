package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.api.property.NumberValue;

public interface Component {

	String toMindustryCode();

	NumberValue height();

	Component copy();

}

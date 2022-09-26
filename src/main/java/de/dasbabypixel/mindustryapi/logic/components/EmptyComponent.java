package de.dasbabypixel.mindustryapi.logic.components;

import de.dasbabypixel.mindustryapi.logic.core.AbstractComponent;
import de.dasbabypixel.mindustryapi.logic.core.Component;

public class EmptyComponent extends AbstractComponent {

	public EmptyComponent() {
		super(0);
	}

	@Override
	public String toMindustryCode() {
		return "";
	}

	@Override
	public Component copy() {
		return new EmptyComponent();
	}

}

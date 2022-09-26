package de.dasbabypixel.mindustryapi.logic.core;

public class UnitBindComponent extends AbstractComponent {

	private final UnitType type;

	public UnitBindComponent(UnitType type) {
		super(1);
		this.type = type;
	}

	@Override
	public String toMindustryCode() {
		return String.format("ubind %s", type.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new UnitBindComponent(type);
	}
}

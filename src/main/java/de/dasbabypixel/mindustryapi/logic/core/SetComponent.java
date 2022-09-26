package de.dasbabypixel.mindustryapi.logic.core;

public class SetComponent extends AbstractComponent {

	private final Variable variable;
	private final Component value;

	public SetComponent(Variable variable, Component value) {
		super(1);
		this.variable = variable;
		this.value = value;
	}

	@Override
	public String toMindustryCode() {
		return String.format("set %s %s", variable.toMindustryCode(), value.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new SetComponent(variable.copy(), value.copy());
	}
}

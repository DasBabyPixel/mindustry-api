package de.dasbabypixel.mindustryapi.logic.core;

public class GetLinkComponent extends AbstractComponent {

	private final Variable variable;
	private final Component component;

	public GetLinkComponent(Variable variable, Component component) {
		super(1);
		this.variable = variable;
		this.component = component;
	}

	@Override
	public String toMindustryCode() {
		return String.format("getlink %s %s", variable.toMindustryCode(), component.toMindustryCode());
	}

	@Override
	public GetLinkComponent copy() {
		return new GetLinkComponent(variable, component.copy());
	}
}

package de.dasbabypixel.mindustryapi.logic.core;

public class PrintComponent extends AbstractComponent {

	private final Component argument;

	public PrintComponent(Component argument) {
		super(1);
		this.argument = argument;
	}

	@Override
	public String toMindustryCode() {
		return String.format("print %s", argument.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new PrintComponent(argument.copy());
	}
}

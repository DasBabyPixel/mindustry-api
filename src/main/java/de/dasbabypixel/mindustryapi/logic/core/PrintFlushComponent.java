package de.dasbabypixel.mindustryapi.logic.core;

public class PrintFlushComponent extends AbstractComponent {

	private final Variable target;

	public PrintFlushComponent(Variable target) {
		super(1);
		this.target = target;
	}

	@Override
	public String toMindustryCode() {
		return String.format("printflush %s", target.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new PrintFlushComponent(target.copy());
	}
}

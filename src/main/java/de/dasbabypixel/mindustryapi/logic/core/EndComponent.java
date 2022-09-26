package de.dasbabypixel.mindustryapi.logic.core;

public class EndComponent extends AbstractComponent {

	public EndComponent() {
		super(1);
	}

	@Override
	public String toMindustryCode() {
		return "end";
	}

	@Override
	public Component copy() {
		return new EndComponent();
	}
}

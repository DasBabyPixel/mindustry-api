package de.dasbabypixel.mindustryapi.logic.core;

public class NewLineComponent extends PrimitiveComponent<String> {

	public NewLineComponent() {
		super(null, "\\n");
	}

	@Override
	public String toMindustryCode() {
		return "\"\\n\"";
	}

	@Override
	public Component copy() {
		return new NewLineComponent();
	}
}

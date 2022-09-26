package de.dasbabypixel.mindustryapi.logic.core;

public class WriteComponent extends AbstractComponent {

	private final Component value;
	private final Component cell;
	private final Component index;

	public WriteComponent(Component value, Component cell, Component index) {
		super(1);
		this.value = value;
		this.cell = cell;
		this.index = index;
	}

	@Override
	public String toMindustryCode() {
		return String.format("write %s %s %s", value.toMindustryCode(), cell.toMindustryCode(),
				index.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new WriteComponent(value.copy(), cell.copy(), index.copy());
	}
}

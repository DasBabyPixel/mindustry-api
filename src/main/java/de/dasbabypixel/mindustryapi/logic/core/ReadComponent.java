package de.dasbabypixel.mindustryapi.logic.core;

public class ReadComponent extends AbstractComponent {

	private final Variable result;
	private final Component cell;
	private final Component index;

	public ReadComponent(Variable result, Component cell, Component index) {
		super(1);
		this.result = result;
		this.cell = cell;
		this.index = index;
	}

	@Override
	public String toMindustryCode() {
		return String.format("read %s %s %s", result.toMindustryCode(), cell.toMindustryCode(),
				index.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new ReadComponent(result.copy(), cell.copy(), index.copy());
	}
}

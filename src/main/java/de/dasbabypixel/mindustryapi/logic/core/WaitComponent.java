package de.dasbabypixel.mindustryapi.logic.core;

public class WaitComponent extends AbstractComponent {

	private final Component time;

	public WaitComponent(Component time) {
		super(1);
		this.time = time;
	}

	@Override
	public String toMindustryCode() {
		return String.format("wait %s", time.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new WaitComponent(time);
	}

}

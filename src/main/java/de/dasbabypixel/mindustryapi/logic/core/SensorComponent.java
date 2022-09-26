package de.dasbabypixel.mindustryapi.logic.core;

public class SensorComponent extends AbstractComponent {

	private final SensorType type;
	private final Variable result;
	private final Component sensorObject;

	public SensorComponent(SensorType type, Variable result, Component sensorObject) {
		super(1);
		this.type = type;
		this.result = result;
		this.sensorObject = sensorObject;
	}

	@Override
	public String toMindustryCode() {
		return String.format("sensor %s %s %s", result.toMindustryCode(), sensorObject.toMindustryCode(),
				type.toString());
	}

	public static enum SensorType {
		TYPE("@type");

		private final String name;

		private SensorType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	@Override
	public Component copy() {
		return new SensorComponent(type, result.copy(), sensorObject.copy());
	}
}

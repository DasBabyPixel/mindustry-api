package de.dasbabypixel.mindustryapi.logic.core;

public class OperationComponent extends AbstractComponent {

	private final Variable variable;
	private final OperationType type;
	private final ComponentList values;

	public OperationComponent(Variable variable, OperationType type, ComponentList values) {
		super(1);
		this.variable = variable;
		this.type = type;
		this.values = values;
	}

	@Override
	public String toMindustryCode() {
		return String.format("op %s %s %s", type.getCode(), variable.toMindustryCode(), values.toMindustryCode());
	}

	@Override
	public Component copy() {
		return new OperationComponent(variable, type, values.copy());
	}

	public static enum OperationType {
		ADD("add"), SUBTRACT("sub"),;

		private final String code;

		private OperationType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
}

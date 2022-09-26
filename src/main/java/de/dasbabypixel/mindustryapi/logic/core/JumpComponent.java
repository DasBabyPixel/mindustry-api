package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.api.property.NumberValue;

public class JumpComponent extends AbstractComponent {

	private final AbstractComponent target;

	private final JumpCondition condition;

	public JumpComponent(AbstractComponent target, JumpType jumpType, ComponentList arguments) {
		this(target, new JumpCondition(jumpType, arguments));
	}

	public JumpComponent(AbstractComponent target, JumpCondition condition) {
		super(1);
		this.target = target;
		this.condition = condition;
	}

	@Override
	public JumpComponent copy() {
		return new JumpComponent(target, condition.copy());
	}

	@Override
	public String toMindustryCode() {
		return String.format("jump %s %s", (target == null ? -1 : target.line().intValue()),
				condition.toMindustryCode());
	}

	public static class JumpCondition implements Component {

		private final NumberValue height = NumberValue.constant(0);

		private final JumpType jumpType;

		private final ComponentList arguments;

		public JumpCondition(JumpType jumpType, ComponentList arguments) {
			this.jumpType = jumpType;
			this.arguments = arguments;
			if (arguments != null && arguments.isEmpty()) {
				new IllegalArgumentException(
						"Arguments passed to JumpCondition are empty. Use null as replacement")
								.printStackTrace();
			}
		}

		public ComponentList getArguments() {
			return arguments;
		}

		public JumpType getJumpType() {
			return jumpType;
		}

		@Override
		public String toMindustryCode() {
			return arguments == null || arguments.isEmpty() ? String.format("%s null null", jumpType.code)
					: String.format("%s %s", jumpType.code, arguments.toMindustryCode());
		}

		@Override
		public NumberValue height() {
			return height;
		}

		@Override
		public JumpCondition copy() {
			return new JumpCondition(jumpType, arguments != null ? arguments.copy() : null);
		}

	}

	public static enum JumpType {

		EQUALS("equal"),
		NOT("notEqual"),
		LESS("lessThan"),
		GREATER("greaterThan"),
		LESSEQ("lessThanEq"),
		GREATEREQ("greaterThanEq"),
		STRICTEQUALS("strictEqual"),
		ALWAYS("always");

		private final String code;

		private JumpType(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}

		public String getCode() {
			return code;
		}

	}

}

package de.dasbabypixel.mindustryapi.logic.core;

public interface Variable extends Component {

	@Deprecated
	public static Variable variable(String name) {
		return new SimpleVariable(name);
	}
	
	@Override
	Variable copy();
	
	public static class SimpleVariable extends PrimitiveComponent<String> implements Variable {
		public SimpleVariable(String value) {
			super(null, value);
		}

		@Override
		public Variable copy() {
			return new SimpleVariable(value);
		}
	}
}

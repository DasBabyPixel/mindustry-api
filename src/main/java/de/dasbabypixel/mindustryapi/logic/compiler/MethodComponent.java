package de.dasbabypixel.mindustryapi.logic.compiler;

import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.Constants;
import de.dasbabypixel.mindustryapi.logic.core.SetComponent;
import de.dasbabypixel.mindustryapi.logic.core.Variable;

public class MethodComponent extends ComponentList {

	private final CompilerVariables variables;

	private final Variable returnVariable;

	public MethodComponent(CompilerVariables variables, ComponentList instructions) {
		this.variables = variables;
		this.returnVariable = this.variables.getNextVariable();
		append(instructions);
		append(new SetComponent(Constants.COUNTER, this.returnVariable));
		unmodifiable = true;
	}

	public Variable getReturnVariable() {
		return returnVariable;
	}

}

package de.dasbabypixel.mindustryapi.logic.compiler;

import java.util.HashMap;
import java.util.Map;

import de.dasbabypixel.mindustryapi.logic.core.Variable;

public class CompilerVariables {

	private final Map<String, Integer> names = new HashMap<>();

	public Variable getNextVariable() {
		return getNextVariable("var", true);
	}

	public Variable getNextVariable(String preferredName) {
		return getNextVariable(preferredName, false);
	}

	@SuppressWarnings("deprecation")
	private synchronized Variable getNextVariable(String preferredName, boolean alwaysAppendNumber) {
		int name;
		if (names.containsKey(preferredName)) {
			name = names.get(preferredName);
		} else {
			name = 0;
		}
		name++;
		String vname = name == 1 && !alwaysAppendNumber ? preferredName : String.format("%s%s", preferredName, name);
		names.put(preferredName, name);
		return Variable.variable(vname);
	}

}

package de.dasbabypixel.mindustryapi.logic.kernel;

import de.dasbabypixel.mindustryapi.logic.compiler.CompilerVariables;
import de.dasbabypixel.mindustryapi.logic.components.EmptyComponent;
import de.dasbabypixel.mindustryapi.logic.components.IfComponent;
import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.Constants;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpCondition;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;
import de.dasbabypixel.mindustryapi.logic.core.NullComponent;
import de.dasbabypixel.mindustryapi.logic.core.SetComponent;
import de.dasbabypixel.mindustryapi.logic.core.Variable;

public class MessageOutput {

	private final Kernel kernel;

	private final CompilerVariables variables;

	public final Variable primaryMessage;

	public MessageOutput(Kernel kernel) {
		this.kernel = kernel;
		this.variables = kernel.variables;
		this.primaryMessage = variables.getNextVariable("primary-message");
	}

	public ComponentList hardwareInit(Variable link, Variable linkType) {
		ComponentList list = new ComponentList();
		EmptyComponent end = new EmptyComponent();

		ComponentList primary = new ComponentList();
		primary.append(new SetComponent(primaryMessage, link));

		ComponentList message = new ComponentList();
		message.append(new IfComponent(
				new JumpCondition(JumpType.EQUALS, new ComponentList(primaryMessage, new NullComponent())), primary));

		list.append(new IfComponent(
				new JumpCondition(JumpType.EQUALS, new ComponentList(linkType, Constants.BlockTypes.MESSAGE)),
				message));
		list.append(end);
		return list;
	}

	public ComponentList init() {
		ComponentList list = new ComponentList();

		return list;
	}

}

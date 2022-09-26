package de.dasbabypixel.mindustryapi.logic.components;

import de.dasbabypixel.mindustryapi.logic.core.AbstractComponent;
import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpCondition;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;

public class IfElseComponent extends ComponentList {

	public IfElseComponent(JumpCondition condition, AbstractComponent executeIf, AbstractComponent executeElse) {
		ComponentList end = new ComponentList();
		append(new JumpComponent(executeIf, condition));
		append(new JumpComponent(executeElse, new JumpCondition(JumpType.ALWAYS, null)));
		append(executeIf);
		append(new JumpComponent(end, new JumpCondition(JumpType.ALWAYS, null)));
		append(executeElse);
		append(end);
		unmodifiable = true;
	}

}

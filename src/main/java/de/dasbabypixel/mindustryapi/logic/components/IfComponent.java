package de.dasbabypixel.mindustryapi.logic.components;

import de.dasbabypixel.mindustryapi.logic.core.AbstractComponent;
import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpCondition;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;

public class IfComponent extends ComponentList {

	public IfComponent(JumpCondition condition, AbstractComponent execute) {
		ComponentList end = new ComponentList();
		append(new JumpComponent(execute, condition));
		append(new JumpComponent(end, new JumpCondition(JumpType.ALWAYS, null)));
		append(execute);
		append(end);
		unmodifiable = true;
	}

}

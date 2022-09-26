package de.dasbabypixel.mindustryapi.logic.kernel;

import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;

public class ProcessorLoop {

	private final Kernel kernel;

	public ProcessorLoop(Kernel kernel) {
		super();
		this.kernel = kernel;
	}

	public ComponentList init() {
		ComponentList list = new ComponentList();
		list.append(kernel.constants.ensureUpToDate());
		
		list.append(new JumpComponent(list, JumpType.ALWAYS, null));
		return list;
	}

}

package de.dasbabypixel.mindustryapi.logic.kernel;

import de.dasbabypixel.mindustryapi.logic.components.IfComponent;
import de.dasbabypixel.mindustryapi.logic.components.RestartComponent;
import de.dasbabypixel.mindustryapi.logic.core.Component;
import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.Constants;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpCondition;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;
import de.dasbabypixel.mindustryapi.logic.core.PrimitiveComponent;
import de.dasbabypixel.mindustryapi.logic.core.SetComponent;
import de.dasbabypixel.mindustryapi.logic.core.Variable;

public class KernelConstants {

	public final Kernel kernel;

	public final KernelConstant links;

	public KernelConstants(Kernel kernel) {
		this.kernel = kernel;
		this.links = constant("links");
	}

	private KernelConstant constant(String name) {
		return new KernelConstant(kernel.variables.getNextVariable("kernel-" + name));
	}

	@SuppressWarnings("deprecation")
	public Component init() {
		ComponentList list = new ComponentList();
		list.append(new SetComponent(links.handle, Constants.LINKS));
		return list;
	}

	@SuppressWarnings("deprecation")
	public ComponentList ensureUpToDate() {
		ComponentList c = new ComponentList();
		c.append(new IfComponent(new JumpCondition(JumpType.NOT, new ComponentList(links, Constants.LINKS)),
				new RestartComponent(kernel)));
		return c;
	}

	public static final class KernelConstant extends PrimitiveComponent<String> {

		private final Variable handle;

		private KernelConstant(Variable handle) {
			super(null, handle.toMindustryCode());
			this.handle = handle;
		}

		@Override
		public Component copy() {
			return this;
		}

	}

}

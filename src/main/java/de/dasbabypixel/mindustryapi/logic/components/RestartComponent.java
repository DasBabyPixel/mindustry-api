package de.dasbabypixel.mindustryapi.logic.components;

import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.Constants;
import de.dasbabypixel.mindustryapi.logic.core.DoubleComponent;
import de.dasbabypixel.mindustryapi.logic.core.LongComponent;
import de.dasbabypixel.mindustryapi.logic.core.PrintComponent;
import de.dasbabypixel.mindustryapi.logic.core.PrintFlushComponent;
import de.dasbabypixel.mindustryapi.logic.core.SetComponent;
import de.dasbabypixel.mindustryapi.logic.core.StringComponent;
import de.dasbabypixel.mindustryapi.logic.core.WaitComponent;
import de.dasbabypixel.mindustryapi.logic.kernel.Kernel;

public class RestartComponent extends ComponentList {

	@SuppressWarnings("deprecation")
	public RestartComponent(Kernel kernel) {
		append(new PrintComponent(new StringComponent("Reboot...")));
		append(new PrintFlushComponent(kernel.messageOutput.primaryMessage));
		append(new WaitComponent(new DoubleComponent(2)));
		append(new SetComponent(Constants.COUNTER, new LongComponent(0L)));

		unmodifiable = true;
	}

}

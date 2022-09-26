package de.dasbabypixel.mindustryapi.logic.kernel;

import de.dasbabypixel.mindustryapi.logic.compiler.CompilerVariables;
import de.dasbabypixel.mindustryapi.logic.components.RestartComponent;
import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.GetLinkComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpCondition;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;
import de.dasbabypixel.mindustryapi.logic.core.LongComponent;
import de.dasbabypixel.mindustryapi.logic.core.OperationComponent;
import de.dasbabypixel.mindustryapi.logic.core.OperationComponent.OperationType;
import de.dasbabypixel.mindustryapi.logic.core.SensorComponent;
import de.dasbabypixel.mindustryapi.logic.core.SensorComponent.SensorType;
import de.dasbabypixel.mindustryapi.logic.core.SetComponent;
import de.dasbabypixel.mindustryapi.logic.core.Variable;

public class Kernel {

	public final CompilerVariables variables;

	public final KernelConstants constants;

	public final Memory memory;

	public final MessageOutput messageOutput;

	public final ProcessorLoop processorLoop;

	public Kernel(CompilerVariables variables) {
		this.variables = variables;
		this.constants = new KernelConstants(this);
		this.memory = new Memory(this);
		this.messageOutput = new MessageOutput(this);
		this.processorLoop = new ProcessorLoop(this);
	}

	public ComponentList init() {
		ComponentList list = new ComponentList();
//		ComponentList l2 = new ComponentList();
//		ComponentList l3 = new ComponentList();
//		ComponentList end = new ComponentList();
//		list.append(new IfComponent(
//				new JumpCondition(JumpType.EQUALS, new ComponentList(new LongComponent(1), new LongComponent(1))),
//				new ComponentList(new PrintComponent(new StringComponent("test")))));

		list.append(constants.init());
		list.append(messageOutput.init());
		list.append(memory.preInit());
		list.append(hardwareInit());
		list.append(memory.postInit());

		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));
		list.append(new ComponentList(memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5)), memory.push(new LongComponent(3)),
				memory.push(new LongComponent(7)), memory.push(new LongComponent(5))));

		list.append(processorLoop.init());

		list.append(new RestartComponent(this));
		return list;
	}

	private ComponentList hardwareInit() {
		Variable linkIndex = variables.getNextVariable("linkIndex");
		Variable link = variables.getNextVariable("link");
		Variable linkType = variables.getNextVariable("linkType");

		ComponentList instructions = new ComponentList();
		ComponentList loop = new ComponentList();
		ComponentList end = new ComponentList();

		JumpComponent loopCondition = new JumpComponent(end,
				new JumpCondition(JumpType.GREATEREQ, new ComponentList(linkIndex, constants.links)));

		instructions.append(new SetComponent(linkIndex, new LongComponent(-1)));
		loop.append(new OperationComponent(linkIndex, OperationType.ADD,
				new ComponentList(linkIndex, new LongComponent(1L))));
		loop.append(loopCondition);

		loop.append(new GetLinkComponent(link, linkIndex));
		loop.append(new SensorComponent(SensorType.TYPE, linkType, link));

		loop.append(memory.hardwareInit(linkIndex, link, linkType));
		loop.append(messageOutput.hardwareInit(link, linkType));

		loop.append(new JumpComponent(loop, JumpType.ALWAYS, null));
		instructions.append(loop);
		instructions.append(end);

		return instructions;
	}

}

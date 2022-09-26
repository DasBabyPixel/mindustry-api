package de.dasbabypixel.mindustryapi.logic.kernel;

import de.dasbabypixel.mindustryapi.logic.compiler.CompilerVariables;
import de.dasbabypixel.mindustryapi.logic.components.IfComponent;
import de.dasbabypixel.mindustryapi.logic.core.AbstractComponent;
import de.dasbabypixel.mindustryapi.logic.core.Component;
import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.core.Constants;
import de.dasbabypixel.mindustryapi.logic.core.GetLinkComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpCondition;
import de.dasbabypixel.mindustryapi.logic.core.JumpComponent.JumpType;
import de.dasbabypixel.mindustryapi.logic.core.LongComponent;
import de.dasbabypixel.mindustryapi.logic.core.NullComponent;
import de.dasbabypixel.mindustryapi.logic.core.OperationComponent;
import de.dasbabypixel.mindustryapi.logic.core.OperationComponent.OperationType;
import de.dasbabypixel.mindustryapi.logic.core.PrintComponent;
import de.dasbabypixel.mindustryapi.logic.core.PrintFlushComponent;
import de.dasbabypixel.mindustryapi.logic.core.ReadComponent;
import de.dasbabypixel.mindustryapi.logic.core.SetComponent;
import de.dasbabypixel.mindustryapi.logic.core.StringComponent;
import de.dasbabypixel.mindustryapi.logic.core.Variable;
import de.dasbabypixel.mindustryapi.logic.core.WriteComponent;

public class Memory {

	private final Variable ram;

	private final Variable stackCell;

	private final Variable stackCellOcc;

//	private final Variable stackCellSelf;

	private final Variable stackCellMax;

	private final Variable stackCellPrev;

	private final Variable stackCellNext;

	private final Variable primaryMemory;

	private final Variable memoryBlocks;

	private final Variable cram;

	private final LongComponent stackCellNoValue = new LongComponent(-1);

	private final LongComponent indexStackCellPrev = new LongComponent(0);

//	private final LongComponent indexStackCellSelf = new LongComponent(indexStackCellPrev.getValue() + 1);

	private final LongComponent indexStackCellNext = new LongComponent(indexStackCellPrev.getValue() + 1);

	private final LongComponent indexStackCellOcc = new LongComponent(indexStackCellNext.getValue() + 1);

	private final LongComponent indexStackCellMax = new LongComponent(indexStackCellOcc.getValue() + 1);

	private final LongComponent indexCount = new LongComponent(indexStackCellMax.getValue() + 1);

	private final Variable lastLinkIndex;

	private final Variable lastLink;

	private final Kernel kernel;

	private final CompilerVariables variables;

	public Memory(Kernel kernel) {
		this.kernel = kernel;
		this.variables = kernel.variables;
		this.ram = variables.getNextVariable("ram");
		this.stackCell = variables.getNextVariable("stack-cell");
		this.stackCellOcc = variables.getNextVariable("stack-cell-occupied");
		this.stackCellMax = variables.getNextVariable("stack-cell-max");
		this.stackCellPrev = variables.getNextVariable("stack-cell-previous");
//		this.stackCellSelf = variables.getNextVariable("stack-cell-self");
		this.stackCellNext = variables.getNextVariable("stack-cell-next");
		this.primaryMemory = variables.getNextVariable("primary-memory");
		this.memoryBlocks = variables.getNextVariable("memory-blocks");
		this.cram = variables.getNextVariable("memory-cram");
		this.lastLinkIndex = variables.getNextVariable("last-link-index");
		this.lastLink = variables.getNextVariable("last-link");
	}

	public AbstractComponent push(Component numberSource) {
		ComponentList l = new ComponentList();
		ComponentList nextCell = new ComponentList();
		ComponentList nextCellExists = new ComponentList();
		nextCell.append(new IfComponent(
				new JumpCondition(JumpType.NOT, new ComponentList(stackCellNext, stackCellNoValue)), nextCellExists));
		nextCellExists.append(new WriteComponent(this.stackCellOcc, stackCell, indexStackCellOcc));
		nextCellExists.append(new GetLinkComponent(stackCell, indexStackCellNext));
		nextCellExists.append(new ReadComponent(this.stackCellPrev, stackCell, indexStackCellPrev));
		nextCellExists.append(new ReadComponent(this.stackCellNext, stackCell, indexStackCellNext));
		nextCellExists.append(new ReadComponent(this.stackCellOcc, stackCell, indexStackCellOcc));
		nextCellExists.append(new ReadComponent(this.stackCellMax, stackCell, indexStackCellMax));

		l.append(new IfComponent(new JumpCondition(JumpType.GREATEREQ, new ComponentList(stackCellOcc, stackCellMax)),
				nextCell));

		ComponentList notFull = new ComponentList();
		notFull.append(new WriteComponent(numberSource, stackCell, stackCellOcc));

		l.append(new IfComponent(new JumpCondition(JumpType.LESS, new ComponentList(stackCellOcc, stackCellMax)),
				notFull));
		l.append(new OperationComponent(stackCellOcc, OperationType.ADD,
				new ComponentList(stackCellOcc, new LongComponent(1))));
		l.append(new WriteComponent(stackCellOcc, stackCell, indexStackCellOcc));
		return l;
	}

	public AbstractComponent pop(Variable numberTarget) {
		return new ComponentList();
	}

	public ComponentList hardwareInit(Variable linkIndex, Variable link, Variable linkType) {

		Variable stackCellMax = variables.getNextVariable();

		ComponentList list = new ComponentList();

		ComponentList lprimary = new ComponentList();
		lprimary.append(new SetComponent(primaryMemory, link));
		lprimary.append(new SetComponent(stackCell, primaryMemory));
		lprimary.append(new SetComponent(this.stackCellMax, cram));
		lprimary.append(new SetComponent(stackCellOcc, indexCount));
		lprimary.append(new SetComponent(stackCellPrev, stackCellNoValue));
		lprimary.append(new SetComponent(stackCellNext, stackCellNoValue));

		ComponentList common = new ComponentList();

		common.append(
				new OperationComponent(stackCellMax, OperationType.SUBTRACT, new ComponentList(cram, indexCount)));

		common.append(new IfComponent(
				new JumpCondition(JumpType.EQUALS, new ComponentList(primaryMemory, new NullComponent())), lprimary));

		common.append(new WriteComponent(lastLinkIndex, link, indexStackCellPrev));
		common.append(new WriteComponent(stackCellNoValue, link, indexStackCellNext));
		common.append(new WriteComponent(cram, link, indexStackCellMax));
		common.append(new WriteComponent(indexCount, link, indexStackCellOcc));
		common.append(new IfComponent(new JumpCondition(JumpType.NOT, new ComponentList(lastLink, new NullComponent())),
				new WriteComponent(linkIndex, lastLink, indexStackCellNext)));

		common.append(new SetComponent(lastLinkIndex, linkIndex));
		common.append(new SetComponent(lastLink, link));

		common.append(new OperationComponent(ram, OperationType.ADD, new ComponentList(ram, stackCellMax)));
		common.append(new OperationComponent(memoryBlocks, OperationType.ADD,
				new ComponentList(memoryBlocks, new LongComponent(1L))));

		ComponentList mcell = new ComponentList();
		mcell.append(new SetComponent(cram, new LongComponent(64)));
		mcell.append(new JumpComponent(common, JumpType.ALWAYS, null));

		ComponentList mbank = new ComponentList();
		mbank.append(new SetComponent(cram, new LongComponent(512)));
		mbank.append(new JumpComponent(common, JumpType.ALWAYS, null));

//		read var cell1 0
//		write var cell1 0

		list.append(new IfComponent(
				new JumpCondition(JumpType.EQUALS, new ComponentList(linkType, Constants.BlockTypes.MEMORY_CELL)),
				mcell));
		list.append(new IfComponent(
				new JumpCondition(JumpType.EQUALS, new ComponentList(linkType, Constants.BlockTypes.MEMORY_BANK)),
				mbank));
		ComponentList end = new ComponentList();
		list.append(new JumpComponent(end, JumpType.ALWAYS, null));
		list.append(common);
		list.append(end);
		return list;
	}

	public ComponentList useStackCell(Component cell) {
		ComponentList l = new ComponentList();
		l.append(new ReadComponent(stackCellPrev, cell, indexStackCellPrev));
		l.append(new ReadComponent(stackCellNext, cell, indexStackCellNext));
		l.append(new ReadComponent(stackCellOcc, cell, indexStackCellOcc));
		l.append(new ReadComponent(stackCellMax, cell, indexStackCellMax));
		return l;
	}

	public ComponentList preInit() {
		ComponentList instructions = new ComponentList();

		instructions.append(new SetComponent(lastLink, new NullComponent()));
		instructions.append(new SetComponent(lastLinkIndex, stackCellNoValue));
		instructions.append(new SetComponent(ram, new LongComponent(0L)));
		instructions.append(new SetComponent(memoryBlocks, new LongComponent(0L)));
		instructions.append(new SetComponent(primaryMemory, new NullComponent()));

		return instructions;
	}

	public ComponentList postInit() {
		ComponentList instructions = new ComponentList();
		instructions.append(useStackCell(primaryMemory));
		instructions.append(new PrintComponent(new StringComponent("Ram: ")));
		instructions.append(new PrintComponent(ram));
		instructions.append(new PrintComponent(new StringComponent("\\nMemory Blocks: ")));
		instructions.append(new PrintComponent(memoryBlocks));
		instructions.append(new PrintComponent(new StringComponent("\\nPrimary Memory: ")));
		instructions.append(new PrintComponent(primaryMemory));
		instructions.append(new PrintFlushComponent(kernel.messageOutput.primaryMessage));
		return instructions;
	}

//	private ComponentList pushSafe(Component value) {
//		ComponentList instructions = new ComponentList();
//		instructions.append(pushUnsafe(value));
//		return instructions;
//	}
//
//	private ComponentList pushUnsafe(Component value) {
//		return writePushUnsafe(stackCellOcc, value);
//	}
//
//	private ComponentList writePushUnsafe(Component index, Component value) {
//		ComponentList instructions = new ComponentList();
//		instructions.append(new WriteComponent(value, stackCell, index));
//		instructions.append(new OperationComponent(stackCellOcc, OperationType.ADD,
//				new ComponentList(stackCellOcc, new LongComponent(1L))));
//		return instructions;
//	}
}

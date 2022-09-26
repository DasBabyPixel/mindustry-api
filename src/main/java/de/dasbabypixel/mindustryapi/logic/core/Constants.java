package de.dasbabypixel.mindustryapi.logic.core;

import de.dasbabypixel.mindustryapi.logic.kernel.KernelConstants;

/**
 * @author DasBabyPixel
 */
public class Constants extends PrimitiveComponent<String> {

	/**
	 * The currently bound unit
	 */
	public static final Constants UNIT = new Constants("@unit");

	/**
	 * Use {@link KernelConstants#links} because this might change during execution.
	 */
	@Deprecated
	public static final Constants LINKS = new Constants("@links");

	/**
	 * The line counter - which line of code are we on? Use is disregarded, only use
	 * it if you know what you are doing!
	 */
	@Deprecated
	public static final VariableConstant COUNTER = new VariableConstant("@counter");

	/**
	 * Count of all unit types
	 */
	public static final Constants UNIT_COUNT = new Constants("@unitCount");

	/**
	 * 
	 */
	public static final Constants ITEM_COUNT = new Constants("@itemCount");

	/**
	 * Count of all fluid types
	 */
	public static final Constants LIQUID_COUNT = new Constants("@liquidCount");

	/**
	 * Count of all block types
	 */
	public static final Constants BLOCK_COUNT = new Constants("@blockCount");

	/**
	 * @author DasBabyPixel
	 */
	@SuppressWarnings("javadoc")
	public static enum BlockTypes {

		;

		public static final Constants MEMORY_CELL = new Constants("@memory-cell");

		public static final Constants MEMORY_BANK = new Constants("@memory-bank");

		public static final Constants MESSAGE = new Constants("@message");

	}

	private Constants(String v) {
		super(null, v);
	}

	private static class VariableConstant extends Constants implements Variable {

		private VariableConstant(String v) {
			super(v);
		}

		@Override
		public VariableConstant copy() {
			return this;
		}

	}

	@Override
	public Constants copy() {
		return this;
	}

}

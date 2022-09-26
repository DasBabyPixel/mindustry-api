package de.dasbabypixel.mindustryapi.logic.core;

import java.util.List;

public class ComponentListSerializer {

	private static final boolean printLines = Boolean.valueOf(System.getProperty("printLines", "false"));

	private final StringBuilder builder = new StringBuilder();

	private String string = null;

	private final ComponentList list;

	public ComponentListSerializer(ComponentList list) {
		this.list = list;
	}

	private void space() {
		builder.append(' ');
	}

	private void newLine(AbstractComponent currentComponent) {
		if (builder.length() != 0) {
			builder.append('\n');
		}
		if (printLines) {
			builder.append(currentComponent.line().intValue()).append(": ");
		}
	}

	private ComponentListSerializer append(Component component) {
		String mdcode = component.toMindustryCode();
		if (component.height().intValue() != 0 && component instanceof AbstractComponent) {
			newLine((AbstractComponent) component);
		} else {
			if (builder.length() != 0 && !mdcode.isEmpty()) {
				space();
			}
		}
		builder.append(mdcode);
		return this;
	}

//	void setCalculatedLine(int cline) {
//		List<Component> components = list.getComponents();
//		int csize = components.size();
//		line = cline;
//		for (int i = 0; i < csize; i++) {
//			Component component = components.get(i);
//			if (component instanceof AbstractComponent) {
//				int cheight = component.getHeight();
//				((AbstractComponent) component).setCalculatedLine(line);
//				line += cheight;
//			}
//		}
//	}

	private String serialize(ComponentList list) {
//		if (!list.hasLine()) {
//			list.setCalculatedLine(0);
//		}
		List<Component> components = list.getComponents();
		int csize = components.size();
		for (int i = 0; i < csize; i++) {
			append(components.get(i));
		}
		string = builder.toString();
		return string;
	}

	@Override
	public String toString() {
		return string == null ? serialize(list) : string;
	}

}

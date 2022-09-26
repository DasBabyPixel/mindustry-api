package de.dasbabypixel.mindustryapi.logic.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import de.dasbabypixel.api.property.NumberValue;

public class ComponentList extends AbstractComponent implements Iterable<Component> {

	private final List<Component> components = new ArrayList<>();

	private final NumberValue height = NumberValue.zero();

	private NumberValue curHeight = NumberValue.zero();

	protected boolean unmodifiable = false;

	public ComponentList() {
		super(-1);
		height().bind(height);
	}

	public ComponentList(Collection<Component> components) {
		this();
		append(components);
	}

	public ComponentList(Component... components) {
		this();
		append(components);
	}

	protected void ensureModifiable() {
		if (unmodifiable) {
			throw new UnsupportedOperationException("ComponentList unmodifiable!");
		}
	}

	public void append(Component... components) {
		ensureModifiable();
		for (Component c : components) {
			append(c);
		}
	}

	public void append(Component component) {
		ensureModifiable();
		if (component instanceof AbstractComponent) {
			AbstractComponent ac = (AbstractComponent) component;
			ac.line().bind(this.line().add(curHeight));
		}
		curHeight = curHeight.add(component.height());
		height.unbind();
		height.bind(curHeight);
		this.components.add(component);

	}

	public void append(Collection<Component> components) {
		ensureModifiable();
		for (Component c : components) {
			append(c);
		}
	}

	@Override
	public ComponentList copy() {
		return new ComponentList(this.components.stream().map(c -> c.copy()).collect(Collectors.toList()));
	}
	

	public Component first() {
		return components.size() > 0 ? components.get(0) : null;
	}

	public List<Component> getComponents() {
		return Collections.unmodifiableList(components);
	}

	@Override
	public Iterator<Component> iterator() {
		return components.iterator();
	}

//	@Override
//	protected int calculateHeight() {
//		int height = 0;
//		for (Component component : components) {
//			height += component.getHeight();
//		}
//		return height;
//	}
//
//	@Override
//	protected void setCalculatedLine(int calculatedLine) {
//		new ComponentListSerializer(this).setCalculatedLine(calculatedLine);
//		super.setCalculatedLine(calculatedLine);
//	}

	@Override
	public String toMindustryCode() {
		return new ComponentListSerializer(this).toString();
	}

	@Override
	public String toString() {
		return String.format("ComponentList[%s]", this.components.size());
	}

	public boolean isEmpty() {
		return components.isEmpty();
	}

}

package de.dasbabypixel.mindustryapi.logic.compiler;

import static org.objectweb.asm.Opcodes.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class CompileTree {

	public final Map<String, ClassNode> nodes = new ConcurrentHashMap<>();

	public ClassNode addClass(InputStream in) throws IOException {
		ClassReader reader = new ClassReader(in);
		ClassNode node = new ClassNode(ASM9);
		reader.accept(node, 0);
		nodes.put(node.name, node);
		return node;
	}
}

package de.dasbabypixel.mindustryapi.logic.compiler;

import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.objectweb.asm.tree.ClassNode;

import de.dasbabypixel.mindustryapi.logic.core.ComponentList;
import de.dasbabypixel.mindustryapi.logic.kernel.Kernel;

public class JavaCompiler {

	private final JarFile jar;
	private final CompileTree tree = new CompileTree();
	private final ClassNodeCompiler classNodeCompiler = new ClassNodeCompiler(this);
	private final MethodNodeCompiler methodNodeCompiler = new MethodNodeCompiler(this);
	private final CompilerVariables compilerVariables = new CompilerVariables();
	private final ComponentList compiled;

	public JavaCompiler(JarFile jar) throws IOException, CompilationException {
		this.jar = jar;
		this.compiled = new ComponentList();
		compile();
	}

	private void compile() throws IOException, CompilationException {
		JarEntry entry = jar.getJarEntry("program.json");
		if (entry == null) {
			throw new CompilationException("No program.json in file!");
		}
		ProgramInfo info = new ProgramInfo(jar.getInputStream(entry));
		Kernel kernel = new Kernel(compilerVariables);
		this.compiled.append(kernel.init());

		System.out.println(this.compiled.toMindustryCode());
	}

	private ClassNode findClass(String name) throws IOException {
		ClassNode node = tree.nodes.get(name);
		if (node != null) {
			return node;
		}
		String jname = name + ".class";
		JarEntry jentry;
		if ((jentry = jar.getJarEntry(jname)) == null) {
			return null;
		}
		return tree.addClass(jar.getInputStream(jentry));
	}

	public ClassNodeCompiler getClassNodeCompiler() {
		return classNodeCompiler;
	}

	public CompilerVariables getCompilerVariables() {
		return compilerVariables;
	}

	public MethodNodeCompiler getMethodNodeCompiler() {
		return methodNodeCompiler;
	}

	public ComponentList compiled() {
		return compiled;
	}
}

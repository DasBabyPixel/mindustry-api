package de.dasbabypixel.mindustryapi.logic.compiler;

public class ClassNodeCompiler {

	private final JavaCompiler compiler;

	public ClassNodeCompiler(JavaCompiler compiler) {
		this.compiler = compiler;
	}
	
	public JavaCompiler getCompiler() {
		return compiler;
	}
}

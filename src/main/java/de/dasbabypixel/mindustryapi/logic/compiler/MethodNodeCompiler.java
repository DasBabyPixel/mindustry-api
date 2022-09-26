package de.dasbabypixel.mindustryapi.logic.compiler;

public class MethodNodeCompiler {

	private final JavaCompiler compiler;

	public MethodNodeCompiler(JavaCompiler compiler) {
		this.compiler = compiler;
	}
	
	public JavaCompiler getCompiler() {
		return compiler;
	}
}

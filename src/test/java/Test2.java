import java.io.IOException;

import de.dasbabypixel.mindustryapi.logic.compiler.CompilationException;
import de.dasbabypixel.mindustryapi.logic.compiler.CompilerVariables;
import de.dasbabypixel.mindustryapi.logic.kernel.Kernel;

public class Test2 {

	public static void main(String[] args) throws IOException, CompilationException {
//		JarFile file = new JarFile(new File("mindustry-api-test-0.0.1-SNAPSHOT.jar"));
//		JavaCompiler compiler = new JavaCompiler(file);

		Kernel kernel = new Kernel(new CompilerVariables());
		System.out.println(kernel.init().toMindustryCode());
	}

}

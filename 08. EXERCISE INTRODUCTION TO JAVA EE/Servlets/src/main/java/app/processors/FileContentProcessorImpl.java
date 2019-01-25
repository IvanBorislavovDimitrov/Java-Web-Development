package app.processors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileContentProcessorImpl implements FileContentProcessor {

	@Override
	public String readContent(String filePath) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(filePath).getFile());
		try {
			
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

}

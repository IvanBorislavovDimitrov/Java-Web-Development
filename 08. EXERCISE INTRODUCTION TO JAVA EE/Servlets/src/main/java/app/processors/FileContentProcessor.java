package app.processors;

@FunctionalInterface
public interface FileContentProcessor {

	String readContent(String filePath);
}

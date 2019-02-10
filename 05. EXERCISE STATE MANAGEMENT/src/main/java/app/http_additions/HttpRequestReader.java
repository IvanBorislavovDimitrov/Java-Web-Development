package app.http_additions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HttpRequestReader {

	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	private List<String> validUrls;
	
	public HttpRequestReader() {
		validUrls = new ArrayList<>();
	}

	public String readRequest() throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = bufferedReader.readLine();

		while (line != null && line.length() > 0) {
			sb.append(line).append(System.lineSeparator());
			line = bufferedReader.readLine();
		}

		sb.append(System.lineSeparator()).append(bufferedReader.readLine());

		return sb.toString();
	}
	
	public void readUrls() throws IOException {
		String[] urls = bufferedReader.readLine().split("\\s+");
		validUrls.addAll(Arrays.asList(urls));
	}
	
	public List<String> getValidUrls() {
		return Collections.unmodifiableList(validUrls);
	}
}

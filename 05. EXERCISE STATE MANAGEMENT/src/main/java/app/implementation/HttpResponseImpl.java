package app.implementation;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import app.http_additions.HttpStatusCode;
import app.http_additions.HttpStatusMessage;
import app.interfaces.HttpResponse;

public class HttpResponseImpl implements HttpResponse {

	private Map<String, String> headers;

	private int statusCode;

	private byte[] content;
	
	public HttpResponseImpl() {
		headers = new LinkedHashMap<>();
	}

	@Override
	public Map<String, String> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public byte[] getContent() {
		return content;
	}

	@Override
	public byte[] getBytes() {
		StringBuilder sb = new StringBuilder();
		String responseLine = String.format("HTTP/1.1 %d %s%s", statusCode, getStatusMessage(statusCode),
				System.lineSeparator());
		sb.append(responseLine);
		headers.forEach((header, value) -> {
			sb.append(String.format("%s: %s%s", header, value, System.lineSeparator()));
		});
		sb.append(System.lineSeparator());
		byte[] responseLineAndHeadersBytes = sb.toString().getBytes();
		byte[] bytes = new byte[responseLineAndHeadersBytes.length + content.length];
		int count = 0;
		for (int i = 0; i < responseLineAndHeadersBytes.length; i++) {
			bytes[count++] = responseLineAndHeadersBytes[i];
		}
		for (int i = 0; i < content.length; i++) {
			bytes[count++] = content[i];
		}

		return bytes;
	}

	@Override
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public void addHeader(String header, String value) {
		headers.put(header, value);
	}

	private String getStatusMessage(int statusCode) {
		switch (statusCode) {
		case HttpStatusCode.BAD_REQUEST:
			return HttpStatusMessage.BAD_REQUEST;
		case HttpStatusCode.FORBIDDEN:
			return HttpStatusMessage.FORBIDDEN;
		case HttpStatusCode.FOUND:
			return HttpStatusMessage.FOUND;
		case HttpStatusCode.INTERNAL_SERVER_ERROR:
			return HttpStatusMessage.INTERNAL_SERVER_ERROR;
		case HttpStatusCode.MOVED_PERMANENTLY:
			return HttpStatusMessage.MOVED_PERMANENTLY;
		case HttpStatusCode.MULTIPLE_CHOICES:
			return HttpStatusMessage.MULTIPLE_CHOICES;
		case HttpStatusCode.NOT_FOUND:
			return HttpStatusMessage.NOT_FOUND;
		case HttpStatusCode.NOT_MODIFIED:
			return HttpStatusMessage.NOT_MODIFIED;
		case HttpStatusCode.OK:
			return HttpStatusMessage.OK;
		case HttpStatusCode.TEMPORARY_REDIRECT:
			return HttpStatusMessage.TEMPORARY_REDIRECT;
		case HttpStatusCode.UNAUTHORIZED:
			return HttpStatusMessage.UNAUTHORIZED;
		}

		throw new IllegalArgumentException(String.format("Invalid Status Code: %d", statusCode));
	}
}

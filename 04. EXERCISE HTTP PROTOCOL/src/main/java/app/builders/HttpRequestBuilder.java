package app.builders;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import app.implementation.HttpRequestImpl;
import app.interfaces.HttpRequest;

public class HttpRequestBuilder {

	private String httpRequestString;

	public HttpRequestBuilder(String httpRequestString) {
		this.httpRequestString = httpRequestString;
	}

	public HttpRequest createHttpRequest() {
		String[] requestLineAndHeadersAndBody = httpRequestString.split(System.lineSeparator() + System.lineSeparator());
		String requestLineAndHeaders = requestLineAndHeadersAndBody[0];
		String bodyString = null;
		if (requestLineAndHeadersAndBody.length >= 2) {
			bodyString = requestLineAndHeadersAndBody[1];
		}
		String[] requestLineAndHeadersArr = requestLineAndHeaders.split(System.lineSeparator());
		String requestLine = requestLineAndHeadersArr[0];
		List<String> headersLines = Arrays.asList(requestLineAndHeadersArr).stream().skip(1)
				.collect(Collectors.toList());

		String[] requestLineParameters = requestLine.split("\\s+");
		String method = requestLineParameters[0];
		String requestUrl = requestLineParameters[1];
		Map<String, String> headers = getHeaders(headersLines);
		Map<String, String> bodyParameters = getBodyParameters(bodyString);

		return createHttpRequest(method, requestUrl, headers, bodyParameters);
	}

	private HttpRequest createHttpRequest(String method, String requestUrl, Map<String, String> headers,
			Map<String, String> bodyParameters) {
		HttpRequest httpRequest = new HttpRequestImpl();
		httpRequest.setMethod(method);
		httpRequest.setRequsetUrl(requestUrl);
		setHeaders(httpRequest, headers);
		setBodyParameters(httpRequest, bodyParameters);
		
		return httpRequest;
	}

	private void setBodyParameters(HttpRequest httpRequest, Map<String, String> bodyParameters) {
		bodyParameters.forEach((parameter, value) -> {
			httpRequest.addBodyParameter(parameter, value);
		});
	}

	private void setHeaders(HttpRequest httpRequest, Map<String, String> headers) {
		headers.forEach((header, value) -> {
			httpRequest.addHeader(header, value);
		});
	}

	private Map<String, String> getBodyParameters(String bodyString) {
		if (bodyString == null || bodyString.length() == 0) {
			return Collections.emptyMap();
		}

		Map<String, String> bodyParameters = new LinkedHashMap<>();
		String[] bodyKeyPairValues = bodyString.split("&");

		for (String keyPairValue : bodyKeyPairValues) {
			String[] keyValue = keyPairValue.split("=");
			String bodyParameter = keyValue[0];
			String value = keyValue[1];
			bodyParameters.put(bodyParameter, value);
		}

		return bodyParameters;
	}

	private Map<String, String> getHeaders(List<String> headersLines) {
		Map<String, String> headers = new LinkedHashMap<>();
		headersLines.forEach(hL -> {
			String[] headerKeyValue = hL.split(":\\s+");
			String header = headerKeyValue[0];
			String value = headerKeyValue[1];
			headers.put(header, value);
		});

		return headers;
	}

}

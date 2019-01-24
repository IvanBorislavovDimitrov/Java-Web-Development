package app.implementation;

import java.util.*;

import app.http_additions.HttpCookie;
import app.interfaces.HttpRequest;

public class HttpRequestImpl implements HttpRequest {

	private String requestUrl;
	
	private String method;
	
	private Map<String, String> bodyParameters;
	
	private Map<String, String> headers;

	private List<HttpCookie> httpCookies;

	public HttpRequestImpl() {
		bodyParameters = new LinkedHashMap<>();
		headers = new LinkedHashMap<>();
		httpCookies = new ArrayList<>();
	}
	
	@Override
	public Map<String, String> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}

	@Override
	public Map<String, String> getBodyParameters() {
		return Collections.unmodifiableMap(bodyParameters);
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getRequestUrl() {
		return requestUrl;
	}

	@Override
	public List<HttpCookie> getHttpCookies() {
		return Collections.unmodifiableList(httpCookies);
	}

	@Override
	public void addHttpCookie(HttpCookie httpCookie) {
		httpCookies.add(httpCookie);
	}

	@Override
	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Override
	public void addHeader(String header, String value) {
		headers.put(header, value);
	}

	@Override
	public void addBodyParameter(String parameter, String value) {
		bodyParameters.put(parameter, value);
	}

	@Override
	public boolean isResource() {
		return requestUrl.contains(".");
	}

}

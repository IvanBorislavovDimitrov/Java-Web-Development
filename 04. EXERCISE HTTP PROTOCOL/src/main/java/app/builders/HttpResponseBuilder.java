package app.builders;

import java.util.List;
import java.util.Map;

import app.http_additions.HeaderConstants;
import app.http_additions.HttpMethod;
import app.http_additions.MethodResolver;
import app.http_additions.RequestMethodFactory;
import app.implementation.HttpResponseImpl;
import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;

public class HttpResponseBuilder {

	private HttpRequest httpRequest;

	private List<String> validUrls;

	private MethodResolver methodResolver;

	public HttpResponseBuilder(HttpRequest httpRequest, List<String> validUrls) {
		this.httpRequest = httpRequest;
		this.validUrls = validUrls;
		methodResolver = RequestMethodFactory.createMethodResolver(HttpMethod.valueOf(httpRequest.getMethod()));
	}

	public HttpResponse createHttpResponse() {
		HttpResponse httpResponse = new HttpResponseImpl();
		setHttpResponseHeaders(httpResponse, httpRequest.getHeaders());
		methodResolver.setState(httpResponse, httpRequest, validUrls);
		
		return httpResponse;
	}

	private void setHttpResponseHeaders(HttpResponse httpResponse, Map<String, String> headers) {
		headers.forEach((header, value) -> {
			if (header.equals(HeaderConstants.DATE) || header.equals(HeaderConstants.CONTENT_TYPE)
					|| header.equals(HeaderConstants.HOST)) {
				httpResponse.addHeader(header, value);
			}
		});
	}
}

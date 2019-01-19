package app.http_additions;

import java.util.List;

import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;

public class GetMethodResolver extends MethodResolver {

	@Override
	public void setState(HttpResponse httpResponse, HttpRequest httpRequest, List<String> validUrls) {
		if (!validUrls.contains(httpRequest.getRequestUrl())) {
			setNotFound(httpResponse);
			httpResponse.setContent(Messages.NOT_FOUND.getBytes());
		} else if (httpRequest.getHeaders().get(HeaderConstants.AUTHORIZATION) == null) {
			setUnathorized(httpResponse);
			httpResponse.setContent(Messages.UNAUTHORIZED.getBytes());
		} else {
			setOk(httpResponse);
			httpResponse.setContent(
					String.format("Greetings %s!", decodeName(getAuthorizationName(httpRequest))).getBytes());
		}
	}
}

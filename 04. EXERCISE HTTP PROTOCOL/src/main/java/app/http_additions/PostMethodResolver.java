package app.http_additions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;

public class PostMethodResolver extends MethodResolver {

	@Override
	public void setState(HttpResponse httpResponse, HttpRequest httpRequest, List<String> validUrls) {
		if (!validUrls.contains(httpRequest.getRequestUrl())) {
			setNotFound(httpResponse);
			httpResponse.setContent(Messages.NOT_FOUND.getBytes());
		} else if (httpRequest.getHeaders().get(HeaderConstants.AUTHORIZATION) == null) {
			setUnathorized(httpResponse);
			httpResponse.setContent(Messages.UNAUTHORIZED.getBytes());
		} else if (httpRequest.getBodyParameters().isEmpty()) {
			setBadRequest(httpResponse);
			httpResponse.setContent(Messages.BAD_REQUEST.getBytes());
		} else {
			setOk(httpResponse);
			httpResponse.setContent(String
					.format("Greetings %s! %s", decodeName(getAuthorizationName(httpRequest)),
							String.format("You have successfully created %s.", getResolvedBodyParameters(httpRequest)))
					.getBytes());
		}
	}

	private String getResolvedBodyParameters(HttpRequest httpRequest) {
		List<Map.Entry<String, String>> parameters = new ArrayList<>();
		for (Map.Entry<String, String> bodyParameter : httpRequest.getBodyParameters().entrySet()) {
			parameters.add(bodyParameter);
		}

		return String.format("%s with %s - %s, %s - %s", parameters.get(0).getValue(), parameters.get(1).getKey(),
				parameters.get(1).getValue(), parameters.get(2).getKey(), parameters.get(2).getValue());
	}

}

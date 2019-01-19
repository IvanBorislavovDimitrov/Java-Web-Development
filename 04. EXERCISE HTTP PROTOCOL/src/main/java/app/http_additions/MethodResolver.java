package app.http_additions;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;

public abstract class MethodResolver {

	public abstract void setState(HttpResponse httpResponse, HttpRequest httpRequest, List<String> validUrls);

	protected void setOk(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HttpStatusCode.OK);
	}

	protected void setBadRequest(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HttpStatusCode.BAD_REQUEST);
	}

	protected void setUnathorized(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HttpStatusCode.UNAUTHORIZED);
	}

	protected void setNotFound(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HttpStatusCode.NOT_FOUND);
	}
	
	protected String decodeName(String name) {
		Decoder decoder = Base64.getDecoder();

		return new String(decoder.decode(name));
	}

	protected String getAuthorizationName(HttpRequest httpRequest) {
		String authorization = httpRequest.getHeaders().get(HeaderConstants.AUTHORIZATION);

		return authorization.split("\\s+")[1];
	}
}

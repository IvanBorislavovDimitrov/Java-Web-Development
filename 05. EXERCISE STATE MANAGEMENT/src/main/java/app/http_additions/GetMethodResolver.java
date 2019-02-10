package app.http_additions;

import java.util.List;

import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;

public class GetMethodResolver extends MethodResolver {

    @Override
    public void setState(HttpResponse httpResponse, HttpRequest httpRequest) {
        if (httpRequest.getHeaders().get(HeaderConstants.AUTHORIZATION) == null) {
            setUnathorized(httpResponse);
            httpResponse.setContent(Messages.UNAUTHORIZED.getBytes());
        } else {
            setOk(httpResponse);
            httpResponse.setContent(
                    String.format("Greetings %s!", decodeName(getAuthorizationName(httpRequest))).getBytes());
        }
    }
}

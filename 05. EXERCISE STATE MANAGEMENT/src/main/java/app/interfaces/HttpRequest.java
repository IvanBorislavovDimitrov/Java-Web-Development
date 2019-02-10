package app.interfaces;

import app.http_additions.HttpCookie;

import java.util.List;
import java.util.Map;

public interface HttpRequest {

	Map<String, String> getHeaders();
	
	Map<String, String> getBodyParameters();
	
	String getMethod();
	
	String getRequestUrl();

	List<HttpCookie> getHttpCookies();

	void addHttpCookie(HttpCookie httpCookie);
	
	void setMethod(String method);
	
	void setRequestUrl(String requestUrl);
	
	void addHeader(String header, String value);
	
	void addBodyParameter(String parameter, String value);
	
	boolean isResource();
}

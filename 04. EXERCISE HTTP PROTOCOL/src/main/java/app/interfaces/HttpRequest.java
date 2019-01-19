package app.interfaces;

import java.util.Map;

public interface HttpRequest {

	Map<String, String> getHeaders();
	
	Map<String, String> getBodyParameters();
	
	String getMethod();
	
	String getRequestUrl();
	
	void setMethod(String method);
	
	void setRequsetUrl(String requestUrl);
	
	void addHeader(String header, String value);
	
	void addBodyParameter(String parameter, String value);
	
	boolean isResource();
}

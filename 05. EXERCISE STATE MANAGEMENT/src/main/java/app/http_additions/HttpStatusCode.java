package app.http_additions;

public final class HttpStatusCode {

	private HttpStatusCode() {
		
	}
	
	public static final int OK = 200;
	
	public static final int MULTIPLE_CHOICES = 300;
	
	public static final int MOVED_PERMANENTLY = 301;
	
	public static final int FOUND = 302;
	
	public static final int NOT_MODIFIED = 304;
	
	public static final int TEMPORARY_REDIRECT = 307;
	
	public static final int BAD_REQUEST = 400;
	
	public static final int UNAUTHORIZED = 401;
	
	public static final int FORBIDDEN = 403;
	
	public static final int NOT_FOUND = 404;
	
	public static final int INTERNAL_SERVER_ERROR = 500;
}

package app.http_additions;

public final class RequestMethodFactory {

	private RequestMethodFactory() {

	}

	public static MethodResolver createMethodResolver(HttpMethod httpMethod) {
		switch (httpMethod) {
		case GET:
			return new GetMethodResolver();
		case POST:
			return new PostMethodResolver();
		}
		
		throw new IllegalArgumentException("Invalid Http Method!");
	}
}

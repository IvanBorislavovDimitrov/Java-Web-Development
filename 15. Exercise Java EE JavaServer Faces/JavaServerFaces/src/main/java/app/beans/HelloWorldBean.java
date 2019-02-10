package app.beans;

import javax.inject.Named;

@Named
public class HelloWorldBean {

    private String message;

    public HelloWorldBean() {
        message = "Hello world bean";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.flowergarden.exception;

public class FlowerNotFoundException extends Exception{
    private String someString;

    public FlowerNotFoundException(String string) {
    	super(string);
        this.someString = string;
        System.out.println("Exception ExcClass");
    }

    public void myOwnExceptionMsg() {
        System.err.println("This is exception massage for srting: " + someString);
    }
}

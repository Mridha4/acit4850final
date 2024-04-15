package com.mycompany.app;

/**
 * Hello world!
 */
public class App
{

    private final String message = "ACIT 4850 Hello World!";

    public App() {}

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(args[0]);
        } else {
            System.out.println("Not enough arguments!");
            System.exit(0);
        }
        System.out.println(new App().getMessage());
    }

    private final String getMessage() {
        return message;
    }

}

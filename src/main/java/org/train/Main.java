package org.train;


import io.javalin.Javalin;

public class Main {
	static final String HELLO_MESSAGE = "Hello from Comment?";
	static final int PORT = 7000;
	static Javalin server;

	public static void main(String[] args) {
		// server = setupApp().start(PORT);

		System.out.println("Javalin crash course - server started !");

		server.after(ctx -> System.out.println("Quitting server..."));
	}

	// Separated method to easily test the server
	public static Javalin setupApp() {
		// Create the Javalin server and start of given port
		Javalin app = null;// TODO

		// Define a GET route on / to send the HELLO_MESSAGE
		// Defines routes for users


		return app;
	}
}

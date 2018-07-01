package serverLogic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Webserver {
	BufferedReader in;
	PrintWriter pW;
	String message = "";
	String header = "";
	
	public Webserver() {
		
		ServerSocket serverSocket;
		try {
			Properties config = new Properties();
			//load config file
			config.load(new FileInputStream("config.properties"));
			//make server listen to port 8080 
			//TODO port from config file
			serverSocket = new ServerSocket(8080);
			
			//main loop
			while (true) {
				System.out.println("Warte auf Requests...");
				//wait for client request
				Socket clientSocket = serverSocket.accept();
				System.out.println("Verarbeite neuen Request");
				//create a new instance of request to handle the request
				new Request(clientSocket, config);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

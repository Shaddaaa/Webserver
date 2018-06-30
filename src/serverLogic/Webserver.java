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
			config.load(new FileInputStream("config.properties"));
			serverSocket = new ServerSocket(8080);
			while (true) {
				System.out.println("Warte auf Requests...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Verarbeite neuen Request");
				new Request(clientSocket, config);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

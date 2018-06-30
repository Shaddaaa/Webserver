package serverLogic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

public class Request {
	
	public Request(Socket clientSocket, Properties config) {
		String root = config.getProperty("root");
		try {
			//InputStream inputStream = clientSocket.getInputStream();
			OutputStream outputStream = clientSocket.getOutputStream();
			String file = "/Response.html";
			
			//RequestHeader rH = new RequestHeader(inputStream);
			new FileResponse(outputStream, root + file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

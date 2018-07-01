package serverLogic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

public class Request {
	
	public Request(Socket clientSocket, Properties config) {
		//get root directory from config file
		String root = config.getProperty("root");
		try {
			InputStream inputStream = clientSocket.getInputStream();
			OutputStream outputStream = clientSocket.getOutputStream();
			
			//create a request header to read the request
			RequestHeader rH = new RequestHeader(inputStream);
			if (rH.getAction().equals("GET")) {
				//get the path to the file to load
				String filepath = rH.getPath();
				//set the filepath to the standard file if there is no path given
				if (filepath.equals("/")) {
					filepath = "/response.html";
				}
				//if the requested file exists:
				//create a new Response according to the requested file
				//else respond with an error 404
				if (new File(root + filepath).exists()) {
					new FileResponse(outputStream, root + filepath);
				} else {
					new ErrorResponse(outputStream, 404);
				}
			} else {
				new ErrorResponse(outputStream, 400);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

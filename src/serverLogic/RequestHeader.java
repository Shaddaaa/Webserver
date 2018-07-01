package serverLogic;

import java.io.IOException;
import java.io.InputStream;

public class RequestHeader {
	
	String action = "";
	String path = "";
	
	public RequestHeader(InputStream inputStream) throws IOException {
		//buffer to read from the inputStream
		byte[] buffer = new byte[1024];
		//boolean to save if there is a space character
		boolean b = true;
		while (b) {
			//position of the next byte to read
			int i = 0;
			//fill the buffer
			int inputAmount = inputStream.read(buffer);
			//read until the next space character(Unicode: 32) and save it in action
			for (;(b = (buffer[i] != 32) && i < inputAmount); i++) {
				action = action + (char)buffer[i];
			}
			//read until the next space character(Unicode: 32) and save it in path
			for (;(b = (buffer[i+1] != 32) && i+1 < inputAmount); i++) {
				path = path + (char)buffer[i+1];
			}
		}
	}
	
	//return the requested action
	public String getAction() {
		return action;
	}
	
	//return the requested path
	public String getPath() {
		return path;
	}
	
	
}

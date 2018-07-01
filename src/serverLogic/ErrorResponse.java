package serverLogic;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorResponse {
	public ErrorResponse(OutputStream outputStream, int ErrorCode) throws IOException {
		//create the actual error message the user gets to see
		String content = getContent(ErrorCode);
		//create the error header
		String header = getHeader(ErrorCode, content);
		//write & flush everything
		outputStream.write(header.getBytes());
		outputStream.write(content.getBytes());
		outputStream.flush();
		outputStream.close();
	}
	
	public String getHeader(int ErrorCode, String content) {
		//create the fitting header
		String header = "HTTP/1.1 " + ErrorCode + "\n" +
						"Content-Length: " + content.length() + "\n" +
						"Content-Type: " + "text/html\n" +
						"\n";
		return header;
	}
	
	public String getContent(int ErrorCode) {
		//html tags to format the error message
		String htmlStart = "<center><h1>";
		String htmlStop = "</center></h1>";
		
		//return the fitting error message+
		//if there is no fitting error message, just return the error code
		String error = "";
		switch (ErrorCode) {
		case 404:
			error = "File not found!";
			break;
		case 400:
			error = "Bad Request!";
			break;
		}
		return htmlStart + ErrorCode + " " + error + htmlStop;
	}
	
}

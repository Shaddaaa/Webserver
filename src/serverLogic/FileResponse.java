package serverLogic;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;

public class FileResponse {
	public FileResponse(OutputStream outputStream, String pathToFile) throws IOException {
		//get the content of the file
		byte[] content = getContent(pathToFile);
		//create a fitting header for the content
		String header = getHeader(content, pathToFile);
		//write the header & content into the output stream
		//and flush it
		outputStream.write(header.getBytes());
		outputStream.write(content);
		outputStream.flush();
		outputStream.close();
	}

	private byte[] getContent(String pathToFile) throws IOException {
		//temporary resizable byte array
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		//create a buffered input stream for the file
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(pathToFile));
		//create a buffer of 1024 bytes
		byte[] buffer = new byte[1024];
		
		//read the next 1024 bytes
		//if there were any next bytes, save them in result
		while ((inputStream.read(buffer)) > 0) {
			result.write(buffer);
		}
		
		inputStream.close();
		
		//create a size fixed ByteArray and return it
		return result.toByteArray();
	}
	
	private String getHeader(byte[] content, String pathToFile) {
		//get the mimeType of the file
		String mimeType = URLConnection.guessContentTypeFromName(pathToFile);
		//create a header with:
		//fitting mime type
		//fitting content length
		String header = "HTTP/1.1 200\n" +
						"Content-Length: " + content.length + "\n" +
						"Content-Type: " + mimeType + "\n" +
						"\n";
		return header;
	}
}

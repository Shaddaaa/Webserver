package serverLogic;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;

public class FileResponse {
	public FileResponse(OutputStream outputStream, String pathToFile) throws IOException {
		byte[] content = getContent(pathToFile);
		String header = getHeader(content, pathToFile);
		outputStream.write(header.getBytes());
		outputStream.write(content);
		outputStream.flush();
	}

	private byte[] getContent(String pathToFile) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(pathToFile));
		byte[] buffer = new byte[1024];
		while ((inputStream.read(buffer))>=0) {
			result.write(buffer);
		}
		inputStream.close();
		return result.toByteArray();
	}

	private String getHeader(byte[] content, String pathToFile) {
		String mimeType = URLConnection.guessContentTypeFromName(pathToFile);
		String header = "HTTP/1.1 200 OK\n" +
						"Content-Length: " + content.length + "\n" +
						"Content-Type: " + mimeType + "\n" +
						"\n";
		return header;
	}
}

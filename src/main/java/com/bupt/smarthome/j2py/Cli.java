package com.bupt.smarthome.j2py;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Cli {
	private Socket socket = null;
	private InputStream in = null;
	private OutputStream out = null;

	public Cli (String host, int port) {
		try {
			socket = new Socket(host, port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest (String req) throws IOException {
		out.write(req.getBytes(StandardCharsets.UTF_8));
	}

	public String recv () throws IOException {
		byte[] b = new byte[1024 * 4];
		int len = in.read(b);
		return new String(b, 0, len);
	}

	public void close () throws IOException {
		socket.close();
	}
}

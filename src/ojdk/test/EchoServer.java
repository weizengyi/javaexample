package ojdk.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
	
	public static void main(String[] args) throws IOException{
		try(ServerSocket s = new ServerSocket(8198)){
			try(Socket incoming = s.accept()){
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				
				try(Scanner in = new Scanner(inStream)){
					PrintWriter out = new PrintWriter(outStream,true);
					out.println("Hello! Enter BYE to Exit");
					boolean done = false;
					while(!done && in.hasNextLine()){
						String line = in.nextLine();
						out.println("Echo:"+line);
						if(line.trim().equals("BYE")) 
							done = true;
					}
				}
			}
		}
	}

}

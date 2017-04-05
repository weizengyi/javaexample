package ojdk.test;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class InterruptibleSocketTest {

}


class InterruptibleSocektFrame extends JFrame{
	public static final int TEXT_ROWS = 20 ;
	public static final int TEXT_COLUMNS = 60 ;
	
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea message;
	private TestServer server;
	private Thread connectThread;
	
	
	
	public void connectInterruptibly() throws IOException{
		message.append("Interruptibly:\n");
		try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8189))){
			in = new Scanner(channel);
			while(!Thread.currentThread().isInterrupted()){
				message.append("Reading ");
				if(in.hasNextLine()){
					String line = in.nextLine() ;
					message.append(line);
					message.append("\n");
				}
			}
		}finally{
			EventQueue.invokeLater(new Runnable(){
				@Override
				public void run() {
					message.append("Channel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
				
			});
		}
	}
	
	public void connectBlocking() throws IOException{
		message.append("Blocking:\n");
		try(Socket sock = new Socket("localhost",8189)){
			in = new Scanner(sock.getInputStream());
			while(!Thread.currentThread().isInterrupted()){
				message.append("Reading ");
				if(in.hasNextLine()){
					String line = in.nextLine();
					message.append(line);
					message.append("\n");
				}
			}
		}finally{
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					message.append("Socket close\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}	
			});
		}
	}
	
	class TestServer implements Runnable{

		@Override
		public void run() {
			try{
				ServerSocket s = new ServerSocket(8189);
				while(true){
					Socket incoming = s.accept() ;
					Runnable r = new TestServerHandler(incoming);
					Thread t = new Thread(r);
					t.start();
				}
			}catch(IOException e){
				message.append("\nTestServer.run " + e);
			}	
		}	
	}
	
	
	class TestServerHandler implements Runnable{

		private Socket incoming;
		private int counter ;
		
		public TestServerHandler(Socket i){
			incoming = i ;
		}
		
		@Override
		public void run() {
			OutputStream outStream;
			try {
				try{
				outStream = incoming.getOutputStream();
				PrintWriter out = new PrintWriter(outStream,true);
				while(counter < 100){
					counter ++ ;
					if(counter <= 10 )
						out.println(counter);
					Thread.sleep(100);
				}
				}finally{
					incoming.close();
					message.append("Closing server");
				}
					
			} catch (InterruptedException e) {
					message.append("\nTestServerHandler.run " + e);
			} catch (IOException e) {
					message.append("\nTestServerHandler.run " + e);
			}
		}
		
	}
	
	
	
	
}




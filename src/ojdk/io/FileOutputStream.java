package ojdk.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileOutputStream extends java.io.OutputStream{
	
	private final FileDescriptor fd;
	
	private final boolean append ;
	
	private FileChannel channel ;
	
	private final String path ;
	
	private final Object closeLock = new Object();
	
	private volatile boolean closed = false ;
	
	public FileOutputStream(String name) throws java.io.FileNotFoundException{
		this(name != null ? new File(name):null,false);
	}
	
	public FileOutputStream(String name,boolean append) throws java.io.FileNotFoundException{
		this(name != null ? new File(name):null,append);
	}
	
	public FileOutputStream(File file) throws java.io.FileNotFoundException{
		this(file,false);
	}
	
	public FileOutputStream(File file,boolean append) throws java.io.FileNotFoundException{
		String name = (file != null ? file.getName():null);
		SecurityManager security = System.getSecurityManager();
		if(security != null){
			security.checkWrite(name);
		}
		if(name == null){
			throw new NullPointerException();
		}
		if(!file.exists()){
			throw new java.io.FileNotFoundException("Invalid file path");
		}
		this.fd = new FileDescriptor();
		//TODO fd.attach(this);
		this.append = append;
		this.path = name ;
		
		open(name,append);
	}
	
	public FileOutputStream(FileDescriptor fdObj){
		SecurityManager security = System.getSecurityManager();
		if(fdObj == null){
			throw new NullPointerException();
		}
		if(security != null){
			security.checkWrite(fdObj);
		}
		this.fd = fdObj ;
		this.append = false;
		this.path = null;
		//TODO fd.attach(this);
	}
	
	
	private native void open0(String name,boolean append) throws FileNotFoundException ;
	
	private void open(String name,boolean append) throws FileNotFoundException{
		open0(name,append);
	}

	private native void write(int b ,boolean append) throws IOException;
	
	@Override
	public void write(int b) throws IOException {
		write(b,append);		
	}
	
	private native void writeBytes(byte b[],int off,int len,boolean append) throws IOException;
	
	public void write(byte b[]) throws IOException{
		writeBytes(b,0,b.length,append);
	}
	
	public void write(byte b[],int off,int len) throws IOException{
		writeBytes(b,off,len,append);
	}
	
	public void close() throws IOException{
		synchronized(closeLock){
			if(closed){
				return ;
			}
			closed = true ;
		}
		
		if(channel != null){
			channel.close();
		}
//		fd.closeAll(new Closeable(){public void close() throws IOException{ close0();}});
	}
	
	public final FileDescriptor getFD() throws IOException{
		if(fd != null){
			return fd;
		}
		throw new IOException();
	}
	
	public FileChannel getChannel(){
		synchronized(this){
			if(channel == null){
//				TODO channel = FileChannel.open(path, options);
			}
			return channel;
		}
	}
	
	protected void finalize() throws IOException{
		if( fd != null){
			if(fd == FileDescriptor.out || fd == FileDescriptor.in)
				flush();
		}
		else{
			close();
		}
	}

	private native void close0() throws IOException;
	
	
	private static native void initIDs();
	
	static {
		initIDs();
	}
}

package ojdk.io;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

import sun.nio.ch.FileChannelImpl;

public class RandomAccessFile implements DataOutput,DataInput,Closeable{
	
	
	private FileDescriptor fd ;
	private FileChannel channel = null;
	private boolean rw;
	
	private final String path;
	
	private Object closeLock = new Object();
	private volatile boolean closed = false ;
	
	private static final int O_RDONLY = 1 ;
	private static final int O_RDWR = 2 ;
	private static final int O_SYNC = 4;
	private static final int O_DSYNC = 8;
	
	public RandomAccessFile(String name,String mode) throws FileNotFoundException{
		this(name != null ? new File(name):null,mode);
	}
	
	
	public RandomAccessFile(File file,String mode) throws FileNotFoundException{
		String name = (file != null ? file.getPath():null);
		int imode = -1 ;
		if(mode.equals("r"))
			imode = O_RDONLY;
		else if(mode.startsWith("RW")){
			imode = O_RDWR;
			rw = true;
			if(mode.length() > 2){
				if(mode.equals("RWS"))
					imode |= O_SYNC;
				else if(mode.equals("RWD"))
					imode |= O_DSYNC;
				else
					imode = -1;
			}
		}
		if(imode < 0) throw new IllegalArgumentException("Illagal mode \""+mode+"\" must be one of \"r\", \"rw\",\"rws\",\"rwd\" ");
		SecurityManager security = System.getSecurityManager() ;
		if(security != null){
			security.checkRead(name);
			if(rw){
				security.checkWrite(name);
			}
		}
		
		if(name == null){
			throw new NullPointerException();
		}
		if(file.isFile()){
			throw new FileNotFoundException("Invalid file path");
		}
		fd = new FileDescriptor();
		fd.attach(this);
		path = name;
		open(name,imode);
	}
	
	public final FileDescriptor getFD() throws IOException{
		if(fd != null){
			return fd;
		}
		throw new IOException();
	}
	
	public final FileChannel getChannel(){
		synchronized(this){
			if(channel == null){
//				channel = FileChannelImpl.open(fd, path, true, rw, this);
			}
			return channel;
		}
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int skipBytes(int n) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean readBoolean() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte readByte() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readUnsignedByte() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short readShort() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readUnsignedShort() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char readChar() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readInt() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readLong() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float readFloat() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double readDouble() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String readLine() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readUTF() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBoolean(boolean v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeByte(int v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeShort(int v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeChar(int v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeInt(int v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeLong(long v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFloat(float v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeDouble(double v) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBytes(String s) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeChars(String s) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeUTF(String s) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	private native void writeBytes(byte b[],int off,int len) throws IOException;
	
	private native void write0(int b) throws IOException;
	
	private native void seek0(long pos) throws IOException;
	
	private native int readBytes(byte b[],int off,int len) throws IOException;
	
	
	private native int read0() throws IOException;
	
	private void open(String name,int mode) throws FileNotFoundException{
		open0(name,mode);
	}
	
	private native void open0(String name,int mode) throws FileNotFoundException;
	
	private static native void initIDs();
	
	private native void close0() throws IOException;

	static {
		initIDs();
	}
}

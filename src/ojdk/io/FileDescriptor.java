package ojdk.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.List;

import sun.misc.JavaIOFileDescriptorAccess;

public final class FileDescriptor {
	
	private int fd ;
	private Closeable parent;
	private List<Closeable> otherParents;
	private boolean closed;
	
	public FileDescriptor(){
		fd = -1 ;
	}
	
	public FileDescriptor(int fd){
		this.fd = fd;
	}
	
	public static final FileDescriptor in = new FileDescriptor(0);
	
	public static final FileDescriptor out = new FileDescriptor(1);
	
	public static final FileDescriptor err = new FileDescriptor(2);
	
	public boolean valid(){
		return fd != -1 ;
	}
	
	public native void sync() throws SyncFailedException;
	
	private static native void initIDs();
	
	static{
		initIDs();
	}
	
	static {
		sun.misc.SharedSecrets.setJavaIOFileDescriptorAccess(
				new JavaIOFileDescriptorAccess(){
					public void set(FileDescriptor obj,int fd){
						obj.fd = fd ;
					}
					
					public int get(FileDescriptor obj){
						return obj.fd;
					}
					
					public void setHandle(FileDescriptor obj,long han){
						throw new UnsupportedOperationException();
					}
					
					public long getHandle(FileDescriptor obj){
						throw new UnsupportedOperationException();
					}

					@Override
					public int get(java.io.FileDescriptor arg0) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public long getHandle(java.io.FileDescriptor arg0) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public void set(java.io.FileDescriptor arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void setHandle(java.io.FileDescriptor arg0, long arg1) {
						// TODO Auto-generated method stub
						
					}
					
				});
	}
	
	synchronized void attach(Closeable c){
		if(parent == null){
			parent = c ;
		}else if (otherParents == null){
			otherParents = new ArrayList<>();
			otherParents.add(parent);
			otherParents.add(c);
		}else {
			otherParents.add(c);
		}
	}
	
	synchronized void closeAll(Closeable releaser) throws IOException{
		if(!closed){
			closed = true ;
			IOException ioe = null ;
			try(Closeable c = releaser){
				if(otherParents != null){
					for(Closeable referent : otherParents){
						try{
							referent.close();
						}catch(IOException x){
								if(ioe == null){
									ioe = x ;
							}else{
								ioe.addSuppressed(x);
							}
						}
					}
				}
			}catch(IOException ex){
				if(ioe != null)
					ex.addSuppressed(ioe);
				ioe = ex;
			}finally{
				if(ioe != null)
					throw ioe ;
			}
			
			
			
		}
	}
	
	
	
	
	
	
	

}

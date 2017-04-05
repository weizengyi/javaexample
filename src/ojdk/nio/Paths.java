package ojdk.nio;

import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

public final class Paths {
	private Paths(){};
	
	public static Path get(String first,String... strings){
		return FileSystems.getDefault().getPath(first, strings);
	}
	
	public static Path get(URI uri){
		String scheme = uri.getScheme();
		if(scheme == null){
			throw new IllegalArgumentException("Missing scheme");
		}
		if(scheme.equalsIgnoreCase("file")){
			return FileSystems.getDefault().provider().getPath(uri);
		}
		for(FileSystemProvider provider:FileSystemProvider.installedProviders()){
			if(provider.getScheme().equalsIgnoreCase(scheme)){
				return provider.getPath(uri);
			}
		}
		throw new FileSystemNotFoundException("Provider " + scheme+" not installed");
	}

}

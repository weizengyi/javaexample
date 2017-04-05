package ojdk.test;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class URLConnecttionTest {

}
class Base64OutputStream extends FilterOutputStream{
	
	private static char[] toBase64 = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N'};
 
	private int col = 0;
	private int i = 0;
	private int [] inbuf = new int[3];
	
	public Base64OutputStream(OutputStream out) {
		super(out);
	}
	
	public void write(int c) throws IOException{
		inbuf[i] = c ;
		i++;
		if(i == 3){
			if(col >= 76){
				super.write('\n');
				col = 0 ;
			}
			super.write(toBase64[(inbuf[0] & 0xFC >> 2)]);
			
		}
	}
	
}
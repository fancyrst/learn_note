package org.fancy.nio.rw;
// $Id$

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FastCopyFile
{
   public static void main( String args[] ) throws Exception {
	   FileInputStream fin = null;
	   FileOutputStream fout = null;
	   try {
		   String infile = args[0];
		   String outfile = args[1];
		
		   fin = new FileInputStream(infile);
		   fout = new FileOutputStream(outfile);
		
		   FileChannel fcin = fin.getChannel();
		   FileChannel fcout = fout.getChannel();
		
		   ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//直接缓冲区，为加快I/O速度
		
		   while (true) {
			   buffer.clear();
		
			   int r = fcin.read( buffer );
		
			   if (r==-1) {
				   break;
			   }	
			   buffer.flip();
		
			   fcout.write( buffer );
		   }
	   } catch (Exception e) {
			e.printStackTrace();
	   } finally {
		   if (fin != null) fin.close();
		   if (fout != null) fout.close();
	   }	   
  }
}

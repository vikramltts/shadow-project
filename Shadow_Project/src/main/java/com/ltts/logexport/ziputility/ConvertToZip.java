package com.ltts.logexport.ziputility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import com.ltts.logexport.exception.*;
import com.ltts.logexport.fileproperties.*;

public class ConvertToZip {

	public static void zip_one_file() throws IOException, Exception {
    	byte[] buffer = new byte[1024];
    	try{
    		System.out.println("before file output");
    	FileOutputStream fos = new FileOutputStream(Properties.zipFilepath);
    	System.out.println("fos output" + fos);
        ZipOutputStream zos = new ZipOutputStream(fos);
    	ZipEntry ze= new ZipEntry(Properties.logFileName);
    	System.out.println("zip entry " + ze);
    	zos.putNextEntry(ze);
    	FileInputStream in = new FileInputStream(Properties.logFilepath);
    	System.out.println("input stream "  + in);
    	int len;
    	while ((len = in.read(buffer)) > 0) {
    		zos.write(buffer, 0, len);
    	}
    	in.close();
    	zos.closeEntry();
    	zos.close();
    	}catch(IOException ex){
    		throw new logException();
//    	   ex.printStackTrace();
    	}
	}
}

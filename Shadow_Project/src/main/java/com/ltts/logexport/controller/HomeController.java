package com.ltts.logexport.controller;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ltts.logexport.ziputility.*;
import com.ltts.logexport.exception.*;

import com.ltts.logexport.fileproperties.*;
@Controller
public class HomeController {

	@GetMapping("")
	public String view() {

		return "home";
		
	}
	@GetMapping("/download")
	public void download(HttpServletResponse res)throws Exception
	{
		try
		{
			ConvertToZip.zip_one_file();
			System.out.println("File zipped");
			File file = new File(Properties.zipFilepath);
			res.setContentType("application/octet-stream");
			String headerKey ="Content-Disposition";
			String headerValue="attachment;filename="+file.getName();
			res.setHeader(headerKey, headerValue);
			ServletOutputStream outputStream = res.getOutputStream();
			BufferedInputStream inputStream =new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[8192];
			int bytesRead=-1;
			while((bytesRead=inputStream.read(buffer))!=-1)
			{
				outputStream.write(buffer,0,bytesRead);
				
			}
			inputStream.close();
			outputStream.close();
		}
		catch(Exception ex)
		{
			throw new logException();
		}
	}

}

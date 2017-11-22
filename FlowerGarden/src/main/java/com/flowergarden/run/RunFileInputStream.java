package com.flowergarden.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RunFileInputStream {

	public static void main(String[] args) {
		FileInputStream in = null;
		FileOutputStream out = null;
		      try {
		         in = new FileInputStream("file.txt");
		         out = new FileOutputStream("output.txt");         
		         int c;
		         while ((c = in.read()) != -1) {
		        	 System.out.print((char)c);
		            out.write(c);
		         }
		      } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
		         if (in != null) { try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}}
		         if (out != null) { try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}}
		      }



	}

}

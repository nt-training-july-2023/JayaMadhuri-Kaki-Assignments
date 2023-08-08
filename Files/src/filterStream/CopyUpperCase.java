package filterStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;

public class CopyUpperCase {
	public static void main(String args[]) {
		try {
		FileInputStream fInput = new FileInputStream("D:\\Tasks\\Files\\src\\inputOutputStream\\File1.txt");
		FilterInputStream fi = new BufferedInputStream(fInput);
		FileOutputStream fOutput = new FileOutputStream("D:\\Tasks\\Files\\src\\inputOutputStream\\File2.txt");
		FilterOutputStream fo = new FilterOutputStream(fOutput);
		int i;
		while((i=fi.read())!=-1) {
			fo.write(Character.toUpperCase(i));
		}
		System.out.println("Copied Successfully....");
		fi.close();
		fo.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

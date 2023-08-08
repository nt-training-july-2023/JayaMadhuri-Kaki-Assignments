package inputOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFile {
	public static void main(String args[]) {
		try {
		FileInputStream fInput = new FileInputStream("D:\\Tasks\\Files\\src\\inputOutputStream\\File1.txt");
		FileOutputStream fOutput = new FileOutputStream("D:\\Tasks\\Files\\src\\inputOutputStream\\File2.txt");
		int i;
		while((i=fInput.read())!=-1) {
			fOutput.write((char)i);
		}
		System.out.println("Copied Successfully");
		fInput.close();
		fOutput.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

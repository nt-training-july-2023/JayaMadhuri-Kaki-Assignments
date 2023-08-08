package customException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	public static void main(String args[]) {
		try {
		File f = new File("D:\\Exercises\\Exceptions\\customException\\Hell.txt");
		Scanner obj = new Scanner(f);
		while(obj.hasNextLine()) {
			String s = obj.nextLine();
			System.out.println(s);
		}
		obj.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File doesn't found in specified location");
		}
	}
}
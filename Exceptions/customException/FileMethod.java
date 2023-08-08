package customException;
import java.io.File;
import java.util.Scanner;
public class FileMethod
{
	public static void readFile(Scanner sc) {
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			System.out.println(s);
		}
	}
	public static void main(String args[])throws Exception
	{
		File f = new File("D:\\Exercises\\Exceptions\\customException\\Hii.txt");
		Scanner sc = new Scanner(f);
		try {
			readFile(sc);
		}
		catch(Exception e) {
			System.out.println("File not found");
		}
		finally {
			sc.close();
		}
	}
}
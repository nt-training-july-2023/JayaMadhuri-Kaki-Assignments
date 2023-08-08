package customException;
import java.io.*;
public class CopyFile
{
	public static void main(String args[])throws IOException
	{
	FileReader fr=new FileReader("D:\\Exercises\\Exceptions\\customException\\Hii.txt");
	FileWriter fw=new FileWriter("D:\\Exercises\\Exceptions\\customException\\Hello.txt");
	int i;
	try
	{
		while((i=fr.read())!=-1)
		fw.write(i);
		System.out.println("Copied");
	}
	catch(IOException e)
	{
		System.out.println("Copying is not done");
	}
	fr.close();
	fw.close();
	}
}
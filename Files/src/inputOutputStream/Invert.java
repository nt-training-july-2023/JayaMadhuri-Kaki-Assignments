package inputOutputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Stack;

public class Invert {
	public static void main(String args[]) {
		try {
			FileInputStream inputStream = new FileInputStream("D:\\Tasks\\File1.txt");
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  //reading a file
		    Stack<String> lines = new Stack<String>();
            String line = null;
            while((line = br.readLine()) != null) {
               lines.push(line);                          //adding lines to stack
            }
            File f = new File("D:\\Tasks\\Files\\src\\inputOutputStream\\File1.txt");
            f.createNewFile();                           //creating a file
	        FileWriter fw = new FileWriter(f);
	        while(! lines.empty()) {
	        	String s = lines.pop();
	            fw.write(s);                   //writing stack contents to file
	            fw.write("\n"); //next line
	            System.out.println(s);
	        }
	        System.out.println(".........Copied inverted lines successfully.........");
            fw.close();
            br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
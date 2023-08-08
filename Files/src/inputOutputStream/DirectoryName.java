package inputOutputStream;

import java.io.File;

public class DirectoryName {
	public static void main(String[] args) {
		File f = new File("D:\\Tasks");
		File contents[] = f.listFiles();
		for(int i=0;i<contents.length;i++) {
			if(contents[i].isFile()) {
				System.out.println("Files: "+contents[i]);
			}
			else if(contents[i].isDirectory()) {
				System.out.println("Directories: "+contents[i]);
			}
		}
	}
}

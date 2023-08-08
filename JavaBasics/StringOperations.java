public class StringOperations {
	public static void main(String args[]) {
		String s1 = "Jaya";
		String s2 = " Madhuri kaki";
		System.out.println("String length: "+(s1.length()));
		System.out.println("String Concatenation: "+(s1.concat(s2)));
		System.out.println("Getting character in 3rd position of s1: "+s1.charAt(2));
		System.out.println("Check whether the string starting with J or not: "+s1.startsWith("J"));
		System.out.println("Find index of y in string s1: "+s1.indexOf("y"));
		System.out.println("Replace y with v on string s1: "+s1.replace('y', 'v'));
		System.out.println("Comparing two strings: "+s2.compareTo(s2));
		System.out.println("String in lowercase: "+s1.toLowerCase());
		System.out.println("Substring of string 2 from index 3: "+s2.substring(3));
		System.out.println("check whether it contains character sequence: "+s2.contains(s2));
		System.out.println("Trim it removes starting and last spaces: "+s2.trim());
	}
}
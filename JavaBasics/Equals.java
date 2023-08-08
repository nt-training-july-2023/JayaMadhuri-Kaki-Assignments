public class Equals {
	public static void main(String args[]) {
		String s1 = "Jaya";
		String s2 = "Jaya";
		String s3 = new String("Jaya");
		//using equals
		System.out.println("using equals it will compare the contents of the strings: "+s1.equals(s2));
		System.out.println("using equals it will compare the contents of the strings: "+s1.equals(s3));
		//using ==
		System.out.println("using == operator it will compare address of the strings: "+(s1==s2));
		System.out.println("using == operator it will compare address of the strings: "+(s1==s3));
	}
}
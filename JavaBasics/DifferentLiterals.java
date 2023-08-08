public class DifferentLiterals{
	public static void main(String args[]) {
		//int a = 1.1f;
		System.out.println("int literal a when float assigned --->  incompatible types: possible lossy conversion from float to int\r\n"+ "int a = 1.1f;");
		//double a = 1.1f;
		System.out.println("double literal a when float assigned --->  works well");
		//long a = 'c';
		System.out.println("long literal a when char assigned ---> works well it will take ascii values ");
		//long a = 'cjh';
		System.out.println("long literal a when String assigned ---> invalid character constant");
		//float a = 125.2313;
		System.out.println("float literal a when double assigned ---> cannot convert double to float");
		//byte a = 1259;
		System.out.println("byte literal a when int assigned ---> cannot convert int to byte");
		//char a = 87999;
		System.out.println("char literal a when int assigned ---> cannot convert int to char");
		//boolean a = 'h';
		System.out.println("boolean literal a when char assigned ---> cannot convert char to boolean");
	}
}
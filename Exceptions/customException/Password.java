package customException;

import java.util.Scanner;

public class Password {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter password for validation");
			String password = sc.next();
			if(password.length()<8) {
				throw new InvalidPasswordException("Password must contain 8 character or above");
			}
			else if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
				throw new InvalidPasswordException("Password must contain alphabets and Numbers");
			}
			else {
				System.out.println("Valid password");
			}
		}
		catch(InvalidPasswordException e) {
			System.out.println(e);
		}
		finally {
			sc.close();
		}
		
	}
}
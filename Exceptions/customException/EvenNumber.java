package customException;

import java.util.Scanner;

public class EvenNumber{
	public static void validate(int n) throws NotAnEvenNumberException{
		if(n<=0) {
			throw new NotAnEvenNumberException("Number is negative or zero");
		}
		else if(n%2!=0) {
			throw new NotAnEvenNumberException("Number is odd");
		}
		else {
			System.out.println("It's an Even Number");
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number to validate: ");
		int Number = sc.nextInt();
		try {
			validate(Number);
		}
		catch(NotAnEvenNumberException e) {
			System.out.println(e);
		}
		sc.close();
	}
}
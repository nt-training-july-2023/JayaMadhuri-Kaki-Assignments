package customException;

import java.util.Scanner;

public class Atm {
	private static String accountBalance;
	private static String withDrawAmount;
	 static void validateAccountBalance(String accountBalance) throws InvalidInputException {
		if(!accountBalance.matches("-?\\d+")){
			throw new InvalidInputException("Account Balance should not contain Alphabets..");
		}else {
			double value = Double.parseDouble(accountBalance);
			 if(value<=0) {
				throw new InvalidInputException("Account Balance can't be negative or Zero..");
			}
		}
	}
	 static void validateWithDrawAmount(String withDrawAmount) throws InvalidInputException {
		if(!withDrawAmount.matches("-?\\d+")){
				throw new InvalidInputException("Amount should not contain Alphabets..");
		}
		else {
			double value = Double.parseDouble(withDrawAmount);
			if(value<=0) {
				throw new InvalidInputException("Amount can't be negative or Zero..");
			}
			else if(value>Integer.parseInt(accountBalance)) {
				throw new InvalidInputException("InSufficient Balance..");
			}
			else {
				System.out.println("your withdraw is in processing.....");
				System.out.println("Please collect your Amount..");
				System.out.println("Your balance: "+(Integer.parseInt(accountBalance)-value ));
			}
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("....welcome....");
		try {
		System.out.print("Enter your AccountBalance: ");
		accountBalance = sc.nextLine();
		validateAccountBalance(accountBalance);
			try {
				System.out.print("Enter withdraw Amount: ");
				withDrawAmount = sc.nextLine();
				validateWithDrawAmount(withDrawAmount);
			}
			catch(InvalidInputException e) {
				System.out.println(e);
			}
		}
		catch(InvalidInputException e) {
			System.out.println(e);
		}
		sc.close();
	}
}
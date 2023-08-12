package bank;

import java.util.Scanner;

class Variables{
	public static double balance=0;
	public static double getBalance() {
		return balance;
	}

	public static void setBalance(double balance) {
		Variables.balance = balance;
	}
}
class Deposit extends Thread{
	private double d;
	Deposit(double d){
		this.d = d;
	}
	public void run() {
		if(d>0) {
		Variables.balance = Variables.balance+d;
		Variables.setBalance(Variables.balance);
		System.out.println("In processing.........");
		try {
			Deposit.sleep(2000);
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Amount deposited successfully"+"\nAvailable Balance: "+Variables.getBalance());
		System.out.println("Enter Option:");
		}
		else {
			System.out.println("Amount Entered Is Invalid");
			System.out.println("Enter Option:");
		}
	}
}
class WithDraw extends Thread{
	private double d;
	double bal = Variables.getBalance();
	WithDraw(double d){
		this.d = d;
	}
	public void run() {
		if(d<=bal) {
		bal = bal-d;
		Variables.setBalance(bal);
		System.out.println("In processing.........");
		try {
			WithDraw.sleep(2000);
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Collect your Amount"+"\nAvailable Balance: "+Variables.getBalance());
		System.out.println("Enter Option:");
		}
		else {
			System.out.println("Insufficient Balance");
			System.out.println("Enter Option:");
		}
	}
}
public class Bank {
	public static void main(String args[]) {
	Scanner sc = new Scanner(System.in);
	System.out.println("......Welcome......"+"\n.......Select Correct Option........."+"\n1.Deposit\n2.Withdraw");
	int n;
	System.out.println("Enter Option:");
	do {
		n = sc.nextInt();
		switch(n) {
		case 1:
			System.out.println(".....Deposit......."+"\nEnter amount to be deposited");
			double a = sc.nextDouble();
			Deposit d = new Deposit(a);
			d.start();
			break;
		case 2:
			System.out.println(".....WithDraw......."+"\nEnter amount to Withdraw");
			double b = sc.nextDouble();
			WithDraw w = new WithDraw(b);
			w.start();
			break;
		default:
			System.out.println("Invalid Option....Exit");
			System.exit(0);
		}
	}while(n<=2);
	sc.close();
	}
}

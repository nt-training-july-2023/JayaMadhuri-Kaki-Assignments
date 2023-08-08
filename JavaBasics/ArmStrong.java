import java.util.Scanner;
public class ArmStrong {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number to find Armstrong or not: ");
		int Number = sc.nextInt();
		int remainder,armstrong=0, temp = Number;
		while(Number > 0) {
			remainder = Number % 10;
			int n = remainder*remainder*remainder; 
			armstrong = armstrong + n;
			Number = Number/10;
		}
		if(temp == armstrong) {
			System.out.println(temp+" is an ArmStrong");
		}
		else {
			System.out.println(temp+" is not an ArmStrong");
		}
		sc.close();
	}
}
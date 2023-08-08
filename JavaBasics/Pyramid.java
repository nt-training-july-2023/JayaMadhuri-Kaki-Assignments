import java.util.Scanner;
public class Pyramid {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num = sc.nextInt();
		for(int i=1;i<=num;i++) {
			for(int j=0;j<i;j++) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		sc.close();
	}
}
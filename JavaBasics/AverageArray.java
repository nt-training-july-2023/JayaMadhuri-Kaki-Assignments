import java.util.Scanner;
public class AverageArray {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter size of the Array: ");
		int size = sc.nextInt();
		int arr[]=new int[size];
		for(int i=0;i<size;i++) {
			arr[i]= sc.nextInt();
		}
		float avg = 0;
		for(int i=0;i<arr.length;i++) {
			avg = avg+arr[i];
		}
		System.out.println("Average of the array of integers: "+(avg/2));
		sc.close();
	}
}
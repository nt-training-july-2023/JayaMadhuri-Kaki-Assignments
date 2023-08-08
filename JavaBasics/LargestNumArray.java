import java.util.Scanner;
public class LargestNumArray {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter size of the Array:");
		int size = sc.nextInt();
		System.out.println("Insert numbers in array:");
		int arr[] = new int[size];
		for(int i=0;i<size;i++) {
			arr[i] = sc.nextInt();
		}
		int max=arr[0];
		for(int i=1;i<=arr.length-1;i++) {
			if(arr[i]>max) {
				max = arr[i];
			}
		}
		System.out.println("Largest Number in Array:"+max);
		sc.close();
	}
}
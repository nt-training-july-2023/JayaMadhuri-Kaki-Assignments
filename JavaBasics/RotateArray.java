import java.util.Scanner;
public class RotateArray {
	public void add(int[] arr,int rotation,int size) {
		int temp[] = new int[size];
		int j=0;
		for(int i=rotation;i<size;i++) {
			temp[j] = arr[i];
			j++;
		}
		for(int i=0;i<rotation;i++) {
			temp[j] = arr[i];
			j++;
		}
		for(int i=0;i<temp.length;i++) {
			System.out.print(temp[i]+" ");
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		RotateArray r = new RotateArray();
		System.out.println("Enter size of the array:");
		int size = sc.nextInt();
		int arr[] = new int[size];
		System.out.println("Insert Elements Into Array:");
		for(int i=0;i<size;i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println("Rotate Array by How many positions");
		int rotation = sc.nextInt();
		System.out.println("Final Array");
		r.add(arr,rotation,size);
		sc.close();
	}
}
package customException;

import java.util.Scanner;

public class Array{
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int arr[] = null;
		//int arr[] = {1,2,3,4};
		System.out.println("Enter index of the array: ");
		try {
		int index = sc.nextInt();
		System.out.println(arr[index]);
		}
		catch(NullPointerException e) {
			System.out.println("Array is Empty");
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Array Index is out of bound");
		}
		sc.close();
	}
}
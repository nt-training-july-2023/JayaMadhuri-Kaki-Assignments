public class AddTwoMatrices {
	public static void main(String args[]) {
		int arr[][] = new int[2][2];
		int arr1[][] = {{1,2},{2,3}};
		int arr2[][] = {{3,4},{2,5}};
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				arr[i][j] = arr1[i][j]+arr2[i][j];
			}
		}
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
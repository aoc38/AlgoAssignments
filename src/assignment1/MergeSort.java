package assignment1;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {
	public static int counter = 0;

	public static void main(String[] args) {
		Random rand = new Random();
		int testCaseCount = 15;
		for (int size = 1; size <= testCaseCount; size++) {
			System.out.println("TestCase " + size);
			int[] numbers = new int[size * 15];
			for (int i = 0; i < size * 15; i++) {
				numbers[i] = rand.nextInt(500) + 1;
			}
			int len = numbers.length;
			double worstCaseCount = len * (Math.log(len) / Math.log(2));
			System.out.println("Size of the array is " + numbers.length);
			System.out.println("Unsorted Array Elements " + Arrays.toString(numbers));
			sort(numbers, 0, numbers.length - 1);
			System.out.println("---------- Merge Comparisons Count " + counter + " ----------");
			System.out.println("---------- Merge Worst Case Count " + worstCaseCount + " ----------");
			System.out.println("Sorted Array Elements " + Arrays.toString(numbers));
			System.out.println();
		}
	}

	private static void sort(int arr[], int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			sort(arr, l, m);
			counter++;
			sort(arr, m + 1, r);
			counter++;
			merge(arr, l, m, r);
			counter++;
		}
	}

	private static void merge(int[] arr, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		int L[] = new int[n1];
		int R[] = new int[n2];
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];

			j++;
			k++;
		}
	}
}
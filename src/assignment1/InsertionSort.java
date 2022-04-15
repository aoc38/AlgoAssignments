package assignment1;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {
	public static void main(String[] args) {
		Random rand = new Random();
		int testCaseCount = 12;
		for (int size = 1; size <= testCaseCount; size++) {
			System.out.println("TestCase " + size);
			int[] numbers = new int[size * 15];
			for (int i = 0; i < size * 15; i++) {
				numbers[i] = rand.nextInt(500) + 1;
			}
			System.out.println("Size of the array is " + numbers.length);
			System.out.println("Unsorted Array Elements " + Arrays.toString(numbers));
			sortNumbers(numbers);
			System.out.println("Sorted Array Elements " + Arrays.toString(numbers));
			System.out.println();
		}
	}

	private static int[] sortNumbers(int[] numbers) {
		int counter = 0;
		int worstCaseCount = 0;
		int len = numbers.length;
		for (int i = 0; i < len; i++) {
			int j = i;
			while ((j > 0) && numbers[j - 1] > numbers[j]) {
				counter++;
				int temp = numbers[j - 1];
				numbers[j - 1] = numbers[j];
				numbers[j] = temp;
				j--;
			}
		}
		worstCaseCount = len * len;
		System.out.println("---------- Comparisons Count " + counter + " ----------");
		System.out.println("---------- Worst Case Count " + worstCaseCount + " ----------");
		return numbers;
	}
}
package assignment3;

import java.util.Arrays;
import java.util.Random;

public class QuickSortAlgo {
	private static int count = 0;
	public static void main(String[] args) {
		Random rand = new Random();
		int testCaseCount = 2;
		for (int size = 1; size <= testCaseCount; size++) {
			System.out.println("TestCase : " + size);
			int[] numbers = new int[size * 11];
			for (int i = 0; i < size * 11; i++) {
				numbers[i] = rand.nextInt(500) + 1;
			}
			System.out.println("Size of the array is : " + numbers.length);
			System.out.println("Array Elements " + Arrays.toString(numbers));
			int len = numbers.length;
			int worstCaseCount = len * len;
			quickSort(numbers, 0, len - 1);
			System.out.println("Sorted Array Elements :	" + Arrays.toString(numbers) + System.lineSeparator());
			System.out.println("Actual Count : " + count);
			System.out.println("Worst Case Count : " + worstCaseCount + System.lineSeparator());
		}

	}

	private static void quickSort(int[] numbers, int beg, int end) {
		if (beg < end) {
			count++;
			int pIndex = partition(numbers, beg, end);
			quickSort(numbers, beg, pIndex - 1);
			quickSort(numbers, pIndex + 1, end);

		}
	}

	private static int partition(int[] numbers, int beg, int end) {
		int pivot = numbers[end];
		int i = (beg - 1);

		for (int j = beg; j <= end - 1; j++) {
			if (numbers[j] < pivot) {
				i++;
				count++;
				int temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
			}
		}
		int temp = numbers[i + 1];
		numbers[i + 1] = numbers[end];
		numbers[end] = temp;
		System.out.println(
				"Array Elements after partition function: " + Arrays.toString(numbers) + System.lineSeparator());
		return (i + 1);
	}
}

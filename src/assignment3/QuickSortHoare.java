package assignment3;

import java.util.Arrays;
import java.util.Random;

public class QuickSortHoare {
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
			double worstCaseCount = len * (Math.log(len) / Math.log(2));
			quickSort(numbers, 0, len - 1);
			System.out.println("Sorted Array Elements :	" + Arrays.toString(numbers) + System.lineSeparator());
			System.out.println("Actual Count : " + count);
			System.out.println("Worst Case Count : " + worstCaseCount + System.lineSeparator());
		}
	}

	private static void quickSort(int[] numbers, int beg, int end) {
		if (beg < end) {
			count++;
			int pIndex = hoarePartition(numbers, beg, end);
			System.out.println(
					"Array Elements after partition function: " + Arrays.toString(numbers) + System.lineSeparator());
			quickSort(numbers, beg, pIndex - 1);
			quickSort(numbers, pIndex + 1, end);

		}
	}

	private static int hoarePartition(int[] numbers, int beg, int end) {
		int x = numbers[beg];
		int i = beg - 1;
		int j = end + 1;
		while (true) {
			j--;
			if (numbers[j] > x) {
				count++;
				j--;
			}
			i++;
			if (numbers[i] < x) {
				count++;
				i = i + 1;
			}
			if (i < j) {
				count++;
				int temp = numbers[j];
				numbers[j] = numbers[i];
				numbers[i] = temp;
			} else {
				return j;
			}
		}
	}
}

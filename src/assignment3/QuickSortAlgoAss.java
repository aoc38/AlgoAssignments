package assignment3;

import java.util.Arrays;
import java.util.Random;

public class QuickSortAlgoAss {
	private static int count = 0;
	private static int i, j;

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
		if (end <= beg) {
			count++;
			return;
		}
		i = 0;
		j = 0;
		quickSortPartition(numbers, beg, end);
		quickSort(numbers, beg, j);
		quickSort(numbers, i, end);
	}

	private static void quickSortPartition(int[] numbers, int l, int r) {
		i = l - 1;
		j = r;
		int p = l - 1, q = r;
		int v = numbers[r];

		while (true) {
			count++;
			while (numbers[++i] < v)
				;
			while (v < numbers[--j])
				
				if (j == l)
					break;
			if (i >= j)
				break;
			int temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
			if (numbers[i] == v) {
				p++;
				temp = numbers[i];
				numbers[i] = numbers[p];
				numbers[p] = temp;
			}
		}
		int temp = numbers[i];
		numbers[i] = numbers[r];
		numbers[r] = temp;
		j = i - 1;
		for (int k = l; k < p; k++, j--) {
			count++;
			temp = numbers[k];
			numbers[k] = numbers[j];
			numbers[j] = temp;
		}
		i = i + 1;
		for (int k = r - 1; k > q; k--, i++) {
			count++;
			temp = numbers[i];
			numbers[i] = numbers[k];
			numbers[k] = temp;
		}
		System.out.println(
				"Array Elements after partition function: " + Arrays.toString(numbers) + System.lineSeparator());

	}
}

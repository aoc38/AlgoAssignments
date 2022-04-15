package assignment2;

import java.util.Arrays;
import java.util.Random;

public class MaxSubArray {
	private static int lastIndex, firstIndex;
	private static int lefteIndex, leftbIndex, leftmaxSum;
	private static int righteIndex, rightbIndex, rightmaxSum;
	private static int crosshIndex, crosslIndex, crossmaxSum;
	private static int maxSum, counter = 0;

	public static void main(String[] args) {

		Random rand = new Random();
		int testCaseCount = 3;
		for (int size = 1; size <= testCaseCount; size++) {
			System.out.println("TestCase " + size);
			int[] numbers = new int[size * 5];
			for (int i = 0; i < size * 5; i++) {
				numbers[i] = (int) (rand.nextInt(100) * Math.pow(-1, rand.nextInt()));
			}
			System.out.println("Size of the array is " + numbers.length);
			System.out.println("Array Elements " + Arrays.toString(numbers));
			int maxSubArrSum = maxSubArr(numbers, 0, numbers.length - 1);
			System.out.println("max sub arr	sum " + maxSubArrSum);
			System.out.println("Starting interval 	"+firstIndex);
			System.out.println("Ending interval  	"+lastIndex);
			System.out.println("Counter val "+counter);

		}
	}

	private static int maxSubArr(int[] numbers, int b, int e) {
		if (b == e) {
			firstIndex = b;
			lastIndex = e;
			maxSum = numbers[e];
			return maxSum;
		}
		counter++;
		int m = (b + e) / 2;
		maxSubArr(numbers, b, m);
		System.out.println("first "+lastIndex);
		leftbIndex = firstIndex;
		lefteIndex = lastIndex;
		leftmaxSum = maxSum;

		maxSubArr(numbers, m + 1, e);
		rightbIndex = firstIndex;
		righteIndex = lastIndex;
		rightmaxSum = maxSum;

		maxCrossingSubArray(numbers, b, m, e);
		
		if (leftmaxSum >= rightmaxSum && leftmaxSum >= crossmaxSum) {
			lastIndex = lefteIndex;
			firstIndex = leftbIndex;
			maxSum = leftmaxSum;

		} else if (rightmaxSum >= leftmaxSum && rightmaxSum >= crossmaxSum) {
			lastIndex = righteIndex;
			firstIndex = rightbIndex;
			maxSum = rightmaxSum;
		} else if (crossmaxSum >= leftmaxSum && crossmaxSum >= rightmaxSum) {
			lastIndex = crosshIndex;
			firstIndex = crosslIndex;
			maxSum = crossmaxSum;
		}

		return m;

	}

	private static void maxCrossingSubArray(int[] numbers, int b, int mid, int e) {
		int left = mid, right = mid;
		int left_sum = -1000000;
		int sum = 0;
		int i;

		for (i = mid; i >= b; i--) {
			sum = sum + numbers[i];
			if (sum > left_sum) {
				left_sum = sum;
				left = i;
			}

		}

		int right_sum = -1000000;
		sum = 0;

		for (i = mid + 1; i <= e; i++) {
			sum = sum + numbers[i];
			if (sum > right_sum) {
				right_sum = sum;
				right = i;
			}

		}

		crosslIndex = left;
		crosshIndex = right;
		crossmaxSum = left_sum + right_sum;

	}

}

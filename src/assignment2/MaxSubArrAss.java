package assignment2;

import java.util.Arrays;
import java.util.Random;

public class MaxSubArrAss {
	public static void main(String[] args) {
		Random rand = new Random();
		int testCaseCount = 10;
		for (int size = 1; size <= testCaseCount; size++) {
			System.out.println("TestCase : " + size);
			int[] numbers = new int[size * 8];
			for (int i = 0; i < size * 8; i++) {
				numbers[i] = (int) (rand.nextInt(100) * Math.pow(-1, rand.nextInt()));
			}
			System.out.println("Size of the array is : " + numbers.length);
			System.out.println("Array Elements " + Arrays.toString(numbers));
			int len = numbers.length;
			int maxSubArrSum = maxSubArr(numbers, len - 1, 0);
			int worstCaseCount = len * len;
			System.out.println("Worst Case Count : " + worstCaseCount);
			System.out.println("Maximum Sub Array Sum	: " + maxSubArrSum);
			System.out.println();

		}
	}

	private static int maxSubArr(int[] numbers, int len, int counter) {
		int maximumSubArraySum = Integer.MIN_VALUE;
		int start = 0;
		int end = 0;

		for (int i = 0; i < len; i++) {
			int runningWindowSum = 0;
			counter++;
			for (int j = i; j < len; j++) {
				runningWindowSum += numbers[j];
				counter++;
				if (runningWindowSum > maximumSubArraySum) {
					maximumSubArraySum = runningWindowSum;
					start = i;
					end = j;
				}
			}
		}
		System.out.println("Starting interval 	: " + start);
		System.out.println("Ending interval  	: " + end);
		System.out.println("Count		: " + counter);
		return maximumSubArraySum;
	}

}

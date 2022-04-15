package assignment2;

import java.util.Arrays;
import java.util.Random;

public class MaxSubArrayDNC {

	private static int[] leftArrSol = new int[3];
	private static int[] rightArrSol = new int[3];
	private static int[] crossArrSol = new int[3];
	private static int counter = 0;

	public static void main(String[] args) {
		Random rand = new Random();
		int testCaseCount = 10;
		for (int size = 1; size <= testCaseCount; size++) {
			System.out.println("TestCase : " + size);
			int[] numbers = new int[size * 9];
			for (int i = 0; i < size * 9; i++) {
				numbers[i] = (int) (rand.nextInt(100) * Math.pow(-1, rand.nextInt()));
			}
			System.out.println("Size of the array is : " + numbers.length);
			System.out.println("Array Elements " + Arrays.toString(numbers));
			int len = numbers.length;
			int[] maxSubArrSum = maxSubArr(numbers, 0, len - 1);
			double worstCaseCount = len * (Math.log(len) / Math.log(2));
			System.out.println("Actual Count : " + counter);
			System.out.println("Worst Case Count : " + worstCaseCount);
			System.out.println("Maximum Sub Array Sum	: " + maxSubArrSum[2]);
			System.out.println("Starting interval 	: " + maxSubArrSum[0]);
			System.out.println("Ending interval  	: " + maxSubArrSum[1]);
			System.out.println();
		}		
	}

	private static int[] maxSubArr(int[] numbers, int low, int high) {

		if (high == low) {
			leftArrSol[0] = low;
			leftArrSol[1] = high;
			leftArrSol[2] = numbers[low];
			return leftArrSol;
		}
		counter++;
		int mid = (low + high) / 2;
		leftArrSol = maxSubArr(numbers, low, mid);
		rightArrSol = maxSubArr(numbers, mid + 1, high);
		crossArrSol = maxCrossSubArr(numbers, low, mid, high);

		if ((leftArrSol[2] >= rightArrSol[2]) && (leftArrSol[2] >= crossArrSol[2])) {
			return leftArrSol;
		} else if ((rightArrSol[2] >= leftArrSol[2]) && (rightArrSol[2] >= crossArrSol[2])) {
			return rightArrSol;
		} else {
			return crossArrSol;
		}

	}

	private static int[] maxCrossSubArr(int[] numbers, int low, int mid, int high) {
		int left_sum = Integer.MIN_VALUE;
		int sum = 0;
		int maxleft = 0;
		int maxright = 0;
		int[] sol = new int[3];
		for (int i = mid; i >= low; i--) {
			sum = sum + numbers[i];
			if (sum > left_sum) {
				left_sum = sum;
				maxleft = i;
			}

		}
		sum = 0;
		int right_sum = Integer.MIN_VALUE;
		for (int j = mid + 1; j <= high; j++) {
			sum = sum + numbers[j];
			if (sum > right_sum) {
				right_sum = sum;
				maxright = j;
			}

		}

		sol[0] = maxleft;
		sol[1] = maxright;
		sol[2] = left_sum + right_sum;

		return sol;
	}

}

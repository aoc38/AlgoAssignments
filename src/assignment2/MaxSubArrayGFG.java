package assignment2;

public class MaxSubArrayGFG {
	

	static int maxCrossingSum(int arr[], int l, int m, int h) {
		int sum = 0;
		int left_sum = Integer.MIN_VALUE;
		for (int i = m; i >= l; i--) {
			sum = sum + arr[i];
			if (sum > left_sum) {
				left_sum = sum;
			}
		}
		sum = 0;
		int right_sum = Integer.MIN_VALUE;
		for (int i = m + 1; i <= h; i++) {
			sum = sum + arr[i];
			if (sum > right_sum) {
				right_sum = sum;
			}
		}

		return Math.max(left_sum + right_sum, Math.max(left_sum, right_sum));
	}

	static int maxSubArraySum(int arr[], int l, int h) {
		if (l == h) {
			return arr[l];
		}

		int m = (l + h) / 2;
		int leftSum = maxSubArraySum(arr, l, m);
		//System.out.println("leftSum"+leftSum);
		int rightSum = maxSubArraySum(arr, m + 1, h);
		//System.out.println("rightSum"+rightSum);
		
		return Math.max(Math.max(leftSum,rightSum),
				maxCrossingSum(arr, l, m, h));
	}

	public static void main(String[] args) {
		int arr[] = {27,16,4,4,-15,-2,-23,-4,10,-12,12,18};
		int n = arr.length;
		int max_sum = maxSubArraySum(arr, 0, n - 1);

		System.out.println("Maximum contiguous sum is " + max_sum);
	}
}

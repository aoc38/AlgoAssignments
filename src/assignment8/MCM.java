package assignment8;

import java.util.*;

public class MCM {

	public static void matrixChainMultiplication(int[] array) {
		int[][] m = new int[array.length - 1][array.length - 1];
		int[][] s = new int[array.length - 1][array.length - 1];
		int[][] t = new int[array.length - 1][array.length - 1];

		for (int g = 0; g < m.length; g++) {
			for (int i = 0, j = g; j < m.length; i++, j++) {
				if (g == 0) {
					m[i][j] = 0;
					s[i][j] = 0;
					t[i][j] = 0;
				} else if (g == 1) {
					m[i][j] = array[i] * array[j] * array[j + 1];
					t[i][j] = m[i][j];
					s[i][j] = i + 1;
				} else {
					int min = Integer.MAX_VALUE;
					t[i][j] = t[i + 1][j] + (array[i] * array[i + 1] * array[j + 1]);

					for (int k = i; k < j; k++) {
						int lc = m[i][k];
						int rc = m[k + 1][j];
						int mc = array[i] * array[k + 1] * array[j + 1];
						int tc = lc + rc + mc;
						if (tc < min) {
							min = tc;
							s[i][j] = k + 1;
						}
					}

					m[i][j] = min;
				}
			}
		}

		System.out.println("Dimensions of arrays m[][] : " + m.length + " x " + m.length + System.lineSeparator());
		System.out.println("Matrix m[][] : ");
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (i > j) {
					System.out.print("## ");
				} else {
					System.out.print(m[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println(System.lineSeparator()+"Matrix s[][] : ");
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if (i > j) {
					System.out.print("0 ");
				} else {
					System.out.print(s[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(
				"The total number of scalar multiplications if dynamic programming is not used : " + t[0][t.length - 1]);
		System.out.println(
				"The total number of scalar multiplications if dynamic programming is used : " + m[0][t.length - 1]);
		System.out.println(
				"-----------------------------------------------------------------------------------------------");

	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			int[] arr = new int[10];
			System.out.println("Array elements");
			for (int j = 0; j < 10; j++) {
				Random random = new Random();
				arr[j] = random.nextInt(100 - 1) + 1;
				System.out.print(arr[j] + " ");
			}
			System.out.println();
			matrixChainMultiplication(arr);
		}
	}
}
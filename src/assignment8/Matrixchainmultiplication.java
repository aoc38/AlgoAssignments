package assignment8;

public class Matrixchainmultiplication {
	protected static int m[][];
	protected static int s[][];
	protected static int z[][];
	protected static int n;

	public static void main(String[] args) {
		int[] p1 = { 30, 35, 15, 5, 10, 20, 25 }; // text book input
		calculateMatrix(p1);
		int[] p2 = { 10, 20, 30, 40, 30 };
		calculateMatrix(p2);
		int[] p3 = { 30, 40, 50, 60, 70 };
		calculateMatrix(p3);
		int[] p4 = { 10, 15, 25, 30, 35 };
		calculateMatrix(p4);
		int[] p5 = { 19, 29, 39, 40, 5 };
		calculateMatrix(p5);
	}

	public static void calculateMatrix(int[] p) {
		System.out.println("The array of matrices is: ");
		System.out.print("{");
		for (int i = 0; i < p.length; i++) {
			System.out.print(p[i] + " ");
		}
		System.out.print("}");
		System.out.println("");
		System.out.println("");
		System.out.println("The optimal parenthesization matrices costs: " + matrixChainOrder(p));
		System.out.println();
		System.out.println("\nThe optimal cost is m[i][j]: ");
		printUpperTriangular(m);
		System.out.println("\nThe index of k is s[i][j]: ");
		printUpperTriangular_k(s);
		System.out.print("The optimal matrices parenthesized form is:");
		printOptimalParens(s, 0, n - 1);
		System.out.println("\nThe non dynamic multiplication cost is: " + recurrenceMatrixMulti(p, 1, n));
	}

	public static int recurrenceMatrixMulti(int[] p, int i, int j) {
		int cost = 0;
		if (i == j) {
			cost = 0;
		} else {
			for (int k = i; k < j; k++) {// calculate cost recursively
				cost = recurrenceMatrixMulti(p, i, k) + recurrenceMatrixMulti(p, k + 1, j) + p[i - 1] * p[k] * p[j];
			}
		}
		return cost;
	}

	public static int matrixChainOrder(int[] p) {
		n = p.length - 1;
		m = new int[n][n];
		s = new int[n][n];
		for (int i = 0; i < n; i++) {
			m[i][i] = 0;
		}
		for (int row = 1; row < n; row++) {
			for (int i = 0; i < n - row; i++) { // values per row
				int j = i + row;
				m[i][j] = Integer.MAX_VALUE; // set to maximum value possible
				for (int k = i; k < j; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if (k == i) {
						m[i][j] = q;
						s[i][j] = k;
					} else if (k == i + 1) {
						if (m[i][j] > q) {
							m[i][j] = q;
							s[i][j] = k;
						}
					} else {
						if (q < m[i][j]) {
							m[i][j] = q;
							s[i][j] = k;
						}
					}
				}
				if (row == p.length - 2) {
					return m[i][j];// cost
				}
			}
		}
		return -1;
	}

	public static void printUpperTriangular(int ar[][]) {
		int len = ar.length;
		for (int a = 0; a < len; a++) {
			for (int b = 0; b < len; b++) {
				if (a < b) {
					System.out.print(ar[a][b] + "\t");
				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}

	public static void printUpperTriangular_k(int ar[][]) {
		int len = ar.length;
		for (int a = 0; a < len; a++) {
			for (int b = 0; b < len; b++) {
				if (a < b) {
					System.out.print(ar[a][b] + 1 + "  ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
		}
	}

	public static void printOptimalParens(int[][] s, int i, int j) {
		if (i == j) {
			System.out.print("A" + (i + 1));
		} else {
			System.out.print("(");
			printOptimalParens(s, i, s[i][j]);
			printOptimalParens(s, s[i][j] + 1, j);
			System.out.print(")");
		}
	}
}

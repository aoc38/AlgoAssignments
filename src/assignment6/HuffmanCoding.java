package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HuffmanNode {
	int dataValue;
	char c;
	HuffmanNode left;
	HuffmanNode right;
}

public class HuffmanCoding {
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter atleast 3 lines of text : ");
			String str = br.readLine();
			System.out.println("--------- INPUT TEXT ---------" + System.lineSeparator() + str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

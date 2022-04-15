package assignment5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class SearchID {
	boolean isPresent;
	int count;

	SearchID(boolean isPresent, int count) {
		this.isPresent = isPresent;
		this.count = count;
	}
}

public class Hash {
	private static int size = 151;
	private static int[] studentIDArr = new int[size];
	private static List<Integer>[] list;

	private static void insertItem(int x) {
		List<Integer> newlist = list[hashFunction(x)];
		if (!newlist.contains(x)) {
			newlist.add(x);
		}
	}

	public static SearchID search(int item) {
		int count = 0;
		List<Integer> newList = list[hashFunction(item)];
		for (int i = 0; i < newList.size(); i++) {
			count++;
			if (newList.get(i) == item) {
				return new SearchID(true, count);
			}
		}
		return new SearchID(false, count);
	}

	private static SearchID sequentialSearch(int[] searchArr, int item) {
		int count = 0;
		for (int i = 0; i < searchArr.length; i++) {
			count++;
			if (searchArr[i] == item)
				return new SearchID(true, count);
		}
		return new SearchID(false, count);
	}

	private static void searchHashTable(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			SearchID node = search(arr[i]);
			if (node.isPresent == true)
				System.out.println("ID is present in HashTable and computation time is " + node.count +" for "+ arr[i]);
			else
				System.out.println("ID is not present in HashTable and computation time is " + node.count+" for "+ arr[i]);
		}
	}

	private static int hashFunction(int x) {
		return (x % size);
	}

	private static void printHashTableID() {
		for (int i = 0; i < size; i++) {
			List<Integer> currentlist = list[i];
			System.out.print(i + ": ");
			for (int j = 0; j < currentlist.size(); j++)
				System.out.print(currentlist.get(j) + "-->");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		list = new List[size];
		for (int i = 0; i < list.length; i++)
			list[i] = new LinkedList<Integer>();
		for (int i = 0; i < size; i++) {
			studentIDArr[i] = rand.nextInt((9999 - 100) + 1) + 10;
		}
		System.out.println("--------- DISPLAYING INITIAL STUDENT ID ARRAY ------");
		System.out.println(Arrays.toString(studentIDArr) + System.lineSeparator());
		for (int i = 0; i < size; i++) {
			insertItem(studentIDArr[i]);
		}
		System.out.println("--------- DISPLAYING HASH TABLE ------");
		printHashTableID();

		int[] searchArr = new int[20];
		for (int i = 0; i < 20; i++) {
			searchArr[i] = studentIDArr[i];
		}
		for (int i = 0; i < 17; i++) {
			insertItem(searchArr[i]);
		}
		int[] elementsNotInArray = { 1555, 1999, 1777 };
		searchHashTable(searchArr);
		searchHashTable(elementsNotInArray);

		System.out.println("--------- SEQUENTIAL SEARCH RESULTS ------");
		for (int i = 0; i < searchArr.length; i++) {
			SearchID sequentialnode = sequentialSearch(studentIDArr, searchArr[i]);
			if (sequentialnode.isPresent == true)
				System.out.println("ID is present in HashTable and computation time is " + sequentialnode.count +" for "+ searchArr[i]);
			else
				System.out.println("ID is not present in HashTable and computation time is " + sequentialnode.count+" for "+ searchArr[i]);
		}
		for (int j = 0; j < elementsNotInArray.length; j++) {
			SearchID sequentialnode = sequentialSearch(studentIDArr, elementsNotInArray[j]);
			if (sequentialnode.isPresent == true)
				System.out.println("ID is present in HashTable and computation time is " + sequentialnode.count+" for "+ elementsNotInArray[j]);
			else
				System.out.println("ID is not present in HashTable and computation time is " + sequentialnode.count+" for "+ elementsNotInArray[j]);
		}
	}
}

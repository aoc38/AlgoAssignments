package assignment5;
import java.util.*;



public class DivisionHashing {

	// manually calculated the hash table size(nearest prime)

	private static int tableSize = 151;



	// class to store the details of the search

	class containNode {

		boolean isPresent;

		int count;



		private containNode(boolean isPresent, int count) {

			this.isPresent = isPresent;

			this.count = count;

		}

	}



	// List of Linked lists

	private static List<Integer>[] hashLists;



	public DivisionHashing(int size) {

		hashLists = new List[size];

		for (int i = 0; i < hashLists.length; i++)

			hashLists[i] = new LinkedList<Integer>();

	}



	// Function to insert item into the hash Table

	public void insert(int item) {

		List<Integer> correctList = hashLists[hashValue(item)];

		if (!correctList.contains(item)) {

			correctList.add(item);

		}

	}



//Function to remove an item from the hash table

	public void remove(int item) {

		List<Integer> correctList = hashLists[hashValue(item)];

		if (correctList.contains(item)) {

			correctList.remove(item);

		}

	}



//Search for a given element in the Hash Table

	public containNode contains(int item) {

		int counter = 0;

		List<Integer> whichList = hashLists[hashValue(item)];

		for (int i = 0; i < whichList.size(); i++) {

			counter++;

			if (whichList.get(i) == item) {

				return new containNode(true, counter);

			}

		}

		return new containNode(false, counter);

	}



//Compute the hash value of an item

	public int hashValue(int item) {

		int hash = item % tableSize;

		return hash;

	}



	static void printArray(int arr[]) {

		for (int i = 0; i < arr.length; i++)

			System.out.print(arr[i] + " ");

		System.out.println();

	}



//Function to print the Has Table

	static void printHashTable() {

		for (int i = 0; i < tableSize; i++) {

			List<Integer> currentlist = hashLists[i];

			System.out.print(i + ": ");

			for (int j = 0; j < currentlist.size(); j++)

				System.out.print(currentlist.get(j) + "->");

			System.out.println();

		}

	}



//create random array of sizes between min to max

	private int[] randomArrayCreator(int arraysize, int min, int max) {

		int finalArray[] = new int[arraysize];

		for (int i = 0; i < arraysize; i++) {

//A randomly generated size array

			finalArray[i] = (int) (Math.random() * ((max - min) + 1)) + min;

		}

		return finalArray;

	}



//Search for an item using sequential search

	private containNode sequentialSearch(int[] arr, int item) {

		int counter = 0;

		for (int i = 0; i < arr.length; i++) {

			counter++;

			if (arr[i] == item)

				return new containNode(true, counter);

		}

		return new containNode(false, counter);

	}



	private void searchHashTable(int[] arr) {

		for (int i = 0; i < arr.length; i++) {

			containNode hashnode = contains(arr[i]);

			if (hashnode.isPresent == true)

				System.out.println("ID is present in HashTable and computation time is " + hashnode.count +" for "+ arr[i]);

			else

				System.out.println("ID is not present in HashTable and computation time is " + hashnode.count +" for "+ arr[i]);

		}

	}



//Main function

	public static void main(String[] args) {

		DivisionHashing hash = new DivisionHashing(151);

		int[] itemArray = hash.randomArrayCreator(500, 1000, 10000);

		printArray(itemArray);

		for (int i = 0; i < itemArray.length; i++)

			hash.insert(itemArray[i]);

		printHashTable();

		int[] searchIndexArray = hash.randomArrayCreator(20, 0, 500);

		int[] searchArray = new int[searchIndexArray.length];

		for (int i = 0; i < searchIndexArray.length; i++) {

			searchArray[i] = itemArray[searchIndexArray[i]];

		}

		int[] itemNotFoundArray = { 3000, 9999, 1000 };

		hash.searchHashTable(searchArray);

		hash.searchHashTable(itemNotFoundArray);

		System.out.println("Sequential search results");

		for (int i = 0; i < searchIndexArray.length; i++) {

			containNode sequentialnode = hash.sequentialSearch(itemArray, searchArray[i]);

			if (sequentialnode.isPresent == true)

				System.out.println("ID is present in HashTable and computation time is " + sequentialnode.count +" for "+ searchArray[i]);

			else

				System.out.println("ID is not present in HashTable and computation time is " + sequentialnode.count +" for "+ searchArray[i]);

		}

		for (int j = 0; j < itemNotFoundArray.length; j++) {

			containNode sequentialnode = hash.sequentialSearch(itemArray, itemNotFoundArray[j]);

			if (sequentialnode.isPresent == true)

				System.out.println("ID is present in HashTable and computation time is " + sequentialnode.count +" for "+ itemNotFoundArray[j]);

			else

				System.out.println("ID is not present in HashTable and computation time is " + sequentialnode.count +" for "+ itemNotFoundArray[j]);

		}

	}

}
package assignment4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class JobData {
	int jobNumber;
	String jobName;
	String submitterName;
	int jobPriortity;

	JobData() {
		jobNumber = -1;
		jobPriortity = -1;
		jobName = "";
		submitterName = "";
	}

	JobData(int number, int priority, String name, String submitter) {
		this.jobNumber = number;
		this.jobName = name;
		this.submitterName = submitter;
		this.jobPriortity = priority;
	}

	JobData(String[] arr) {
		this.jobNumber = Integer.parseInt(arr[0]);
		this.jobName = arr[1];
		this.submitterName = arr[2];
		this.jobPriortity = Integer.parseInt(arr[3]);
	}

	@Override
	public String toString() {
		return " [jobNumber=" + jobNumber + ", jobName=" + jobName + ", submitterName=" + submitterName
				+ ", jobPriortity=" + jobPriortity + "]";
	}
}

class AlgoAssPriorityQueue {
	private static ArrayList<JobData> waitQueue = new ArrayList<>();
	private static ArrayList<JobData> readyQueue = new ArrayList<>();

	public static void heapSort(ArrayList<JobData> jobQueue) {
		int n = jobQueue.size();
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(jobQueue, n, i);
		for (int i = n - 1; i >= 0; i--) {
			Collections.swap(jobQueue, 0, i);
			/*
			 * int temp = jobQueue.get(0).jobPriortity; jobQueue.get(0).jobPriortity =
			 * jobQueue.get(i).jobPriortity; jobQueue.get(i).jobPriortity = temp;
			 */
			heapify(jobQueue, i, 0);
		}
	}

	public static void heapify(ArrayList<JobData> jobArr, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		if ((l < n && (jobArr.get(l).jobPriortity > jobArr.get(largest).jobPriortity)))
			largest = l;
		if ((r < n && (jobArr.get(r).jobPriortity > jobArr.get(largest).jobPriortity)))
			largest = r;
		if (largest != i) {
			/*
			 * int temp = jobArr.get(i).jobPriortity; jobArr.get(i).jobPriortity =
			 * jobArr.get(largest).jobPriortity; jobArr.get(largest).jobPriortity = temp;
			 */
			Collections.swap(jobArr, i, largest);
			heapify(jobArr, n, largest);
		}
	}

	private static void displayQueue(ArrayList<JobData> queue) {
		queue.stream().forEach(System.out::println);
	}

	private static void addJobsToReadyQueue(ArrayList<JobData> queue, int numberofJobs) {
		int size = queue.size();
		for (int i = 1; i <= numberofJobs; i++) {
			JobData job = queue.get(size - i);
			queue.remove(job);
			heapSort(queue);

			readyQueue.add(job);
			heapSort(readyQueue);

		}
	}

	private static void deleteJobs(ArrayList<JobData> queue, int numberofJobs) {
		int size = queue.size();
		for (int i = 1; i <= numberofJobs; i++) {
			JobData job = queue.get(size - i);
			queue.remove(job);
			heapSort(queue);
		}
	}

	public static void main(String[] args) {

		Random rand = new Random();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get("C:\\Users\\kkldi\\Desktop\\jobdata.txt"), StandardCharsets.UTF_8);
			for (String e : lines) {
				String[] arr = e.split(" ");
				JobData obj = new JobData(arr);
				waitQueue.add(obj);
			}
			System.out.println(
					"------------------------------------ INITIAL WAIT QUEUE ------------------------------------");
			displayQueue(waitQueue);
			heapSort(waitQueue);
			System.out.println(
					"------------------------------------ SORTED WAIT QUEUE ------------------------------------");
			displayQueue(waitQueue);

			addJobsToReadyQueue(waitQueue, 4);
			System.out.println(
					"------------------------------------ SORTED WAIT QUEUE AFTER 4 MOVES ------------------------------------");
			displayQueue(waitQueue);
			System.out.println(
					"------------------------------------ SORTED READY QUEUE AFTER 4 MOVES ------------------------------------");
			displayQueue(readyQueue);

			deleteJobs(readyQueue, 2);
			System.out.println(
					"------------------------------------ SORTED READY QUEUE AFTER DELETING 2 JOBS ------------------------------------");
			displayQueue(readyQueue);

			System.out.println(
					"------------------------------------ ADD 2 JOBS TO WAIT QUEUE , MOVE 3 JOBS TO WAIT QUEUE TO READY QUEUE, DELETE 1 JOB FROM READY QUEUE ------------------------------------");

			waitQueue.add(new JobData(234, 17, "task17", "Jake"));
			waitQueue.add(new JobData(345, 15, "task15", "Jackson"));
			heapSort(waitQueue);

			addJobsToReadyQueue(waitQueue, 3);
			deleteJobs(readyQueue, 1);

			System.out.println("------------------------------------ READY QUEUE ------------------------------------");
			displayQueue(readyQueue);
			System.out.println("------------------------------------ WAIT QUEUE ------------------------------------");
			displayQueue(waitQueue);

			System.out.println(
					"------------------------------------  WAITING QUEUE AFTER CHANGING PRIORITY FOR 2 JOBS------------------------------------");

			waitQueue.get(0).jobPriortity = rand.nextInt(50) + 1;
			waitQueue.get(1).jobPriortity = rand.nextInt(50) + 1;
			heapSort(waitQueue);
			displayQueue(waitQueue);

			System.out.println(
					"------------------------------------  WAITING QUEUE AFTER MOVING TO READY QUEUE------------------------------------");
			addJobsToReadyQueue(waitQueue, waitQueue.size());
			displayQueue(waitQueue);

			System.out.println(
					"------------------------------------  READY QUEUE AFTER MOVING FROM WAITING QUEUE------------------------------------");
			displayQueue(readyQueue);

			System.out.println(
					"------------------------------------  READY QUEUE AFTER DELETING ALL JOBS------------------------------------");
			deleteJobs(readyQueue, readyQueue.size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
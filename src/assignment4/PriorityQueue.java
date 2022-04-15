package assignment4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Job {
	int jobNumber;
	String jobName;
	String submitterName;
	int jobPriortity;

	Job() {
		jobNumber = -1;
		jobPriortity = -1;
		jobName = "";
		submitterName = "";
	}

	Job(int number, int priority, String name, String submitter) {
		this.jobNumber = number;
		this.jobName = name;
		this.submitterName = submitter;
		this.jobPriortity = priority;
	}

	Job(String[] arr) {
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

class PriorityQueue {
	private static ArrayList<Job> waitQueue = new ArrayList<>();
	private static ArrayList<Job> readyQueue = new ArrayList<>();

	public static void heapSort(ArrayList<Job> jobQueue) {
		int n = jobQueue.size();
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(jobQueue, n, i);
		for (int i = n - 1; i >= 0; i--) {
			Collections.swap(jobQueue, 0, i);
			heapify(jobQueue, i, 0);
		}
	}

	public static void heapify(ArrayList<Job> jobArr, int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if ((l < n && (jobArr.get(l).jobPriortity > jobArr.get(largest).jobPriortity)))
			largest = l;
		if ((r < n && (jobArr.get(r).jobPriortity > jobArr.get(largest).jobPriortity)))
			largest = r;
		if (largest != i) {
			Collections.swap(jobArr, i, largest);
			heapify(jobArr, n, largest);
		}
	}

	private static void displayQueueJobs(ArrayList<Job> queue) {
		queue.stream().forEach(System.out::println);
	}

	private static void addJobsToReadyQueue(ArrayList<Job> queue, int numberofJobs) {
		int size = queue.size();
		for (int i = 1; i <= numberofJobs; i++) {
			Job job = queue.get(size - i);
			queue.remove(job);
			heapSort(queue);

			readyQueue.add(job);
			heapSort(readyQueue);
		}
	}

	private static void deleteJobs(ArrayList<Job> queue, int numberofJobs) {
		int size = queue.size();
		for (int i = 1; i <= numberofJobs; i++) {
			Job job = queue.get(size - i);
			queue.remove(job);
			heapSort(queue);
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get("C:\\Users\\kkldi\\Desktop\\inputData.txt"), StandardCharsets.UTF_8);
			for (String ele : lines) {
				String[] arr = ele.split(" ");
				Job obj = new Job(arr);
				waitQueue.add(obj);
			}
			System.out.println("------ INITIAL WAIT QUEUE ------");
			displayQueueJobs(waitQueue);
			heapSort(waitQueue);
			System.out.println("------ SORTED WAIT QUEUE ------");
			displayQueueJobs(waitQueue);

			addJobsToReadyQueue(waitQueue, 4);
			System.out.println("------ SORTED WAIT QUEUE AFTER 4 MOVES ------");
			displayQueueJobs(waitQueue);
			System.out.println("------ SORTED READY QUEUE AFTER 4 MOVES ------");
			displayQueueJobs(readyQueue);

			deleteJobs(readyQueue, 2);
			System.out.println("------ SORTED READY QUEUE AFTER DELETING 2 JOBS ------");
			displayQueueJobs(readyQueue);

			System.out.println(
					"------ ADD 2 JOBS TO WAIT QUEUE , MOVE 3 JOBS TO WAIT QUEUE TO READY QUEUE, DELETE 1 JOB FROM READY QUEUE ------");

			waitQueue.add(new Job(234, 17, "task17", "Emily"));
			waitQueue.add(new Job(345, 15, "task15", "Denver"));
			heapSort(waitQueue);

			addJobsToReadyQueue(waitQueue, 3);
			deleteJobs(readyQueue, 1);

			System.out.println("------ READY QUEUE AFTER ABOVE OPERATIONS ------");
			displayQueueJobs(readyQueue);
			System.out.println("------ WAIT QUEUE AFTER ABOVE OPERATIONS ------");
			displayQueueJobs(waitQueue);

			System.out.println("------  WAIT QUEUE AFTER CHANGING PRIORITY FOR 2 JOBS ------");
			waitQueue.get(3).jobPriortity = rand.nextInt(50) + 1;
			waitQueue.get(2).jobPriortity = rand.nextInt(50) + 1;
			heapSort(waitQueue);
			displayQueueJobs(waitQueue);

			System.out.println("------ WAITING QUEUE AFTER MOVING TO READY QUEUE ------");
			addJobsToReadyQueue(waitQueue, waitQueue.size());
			displayQueueJobs(waitQueue);

			System.out.println("------ READY QUEUE AFTER MOVING FROM WAITING QUEUE ------");
			displayQueueJobs(readyQueue);

			System.out.println("------  READY QUEUE AFTER DELETING ALL JOBS ------");
			deleteJobs(readyQueue, readyQueue.size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

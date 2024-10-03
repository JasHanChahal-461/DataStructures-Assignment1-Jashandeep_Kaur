import java.util.ArrayList;
import java.util.Scanner;

class ExplorerQueue {
    private Explorer[] queue;
    private int front, rear, size, capacity;
    private int explorerCount; // Keeps track of total explorers added
    private ExpeditionLog expeditionLog; // Keeps track of explorers who enter the temple

    // Constructor to initialize the circular queue
    public ExplorerQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Explorer[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
        this.explorerCount = 0;
        this.expeditionLog = new ExpeditionLog();
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Enqueue a new explorer
    public void enqueueExplorer(Explorer explorer) {
        if (isFull()) {
            System.out.println("Queue is full! Resizing queue to add more explorers.");
            resizeQueue();
        }

        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        queue[rear] = explorer;
        size++;
        explorerCount++;
        System.out.println("Explorer " + explorer.name + " added to the queue.");
    }

    // Dequeue explorer when they enter the temple
    public Explorer dequeueExplorer() {
        if (isEmpty()) {
            System.out.println("No explorers in the queue.");
            return null;
        }

        Explorer explorer = queue[front];
        queue[front] = null;

        if (front == rear) { // Only one element in the queue
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        size--;
        expeditionLog.logExpedition(explorer); // Log explorer entry
        System.out.println("Explorer " + explorer.name + " has entered the temple.");
        return explorer;
    }

    // Display the next explorer in line
    public void displayNextExplorer() {
        if (isEmpty()) {
            System.out.println("No explorers in line.");
        } else {
            System.out.println("Next explorer in line: " + queue[front].name);
        }
    }

    // Resize the queue when it is full
    private void resizeQueue() {
        int newCapacity = capacity * 2;
        Explorer[] newQueue = new Explorer[newCapacity];

        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }

        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        queue = newQueue;
        System.out.println("Queue resized to capacity: " + newCapacity);
    }

    // Display all explorers in the queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No explorers in the queue.");
        } else {
            System.out.println("Current explorers in the queue:");
            for (int i = 0; i < size; i++) {
                Explorer explorer = queue[(front + i) % capacity];
                System.out.println(explorer);
            }
        }
    }

    // Display the expedition log
    public void displayExpeditionLog() {
        expeditionLog.displayLog();
    }

    // Display queue statistics
    public void displayQueueStats() {
        System.out.println("Total explorers added: " + explorerCount);
        System.out.println("Current queue size: " + size);
        System.out.println("Average experience in queue: " + calculateAverageExperience());
    }

    // Calculate the average experience of explorers in the queue
    private double calculateAverageExperience() {
        if (isEmpty()) return 0;

        double totalExperience = 0;
        for (int i = 0; i < size; i++) {
            totalExperience += queue[(front + i) % capacity].experience;
        }
        return totalExperience / size;
    }

    // Main method for user input and interaction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExplorerQueue explorerQueue = new ExplorerQueue(3); // Initial capacity of 3 explorers

        while (true) {
            try {
                System.out.println("\nChoose an action: enqueue, dequeue, next, display, log, stats, or exit:");
                String action = scanner.nextLine().toLowerCase();

                switch (action) {
                    case "enqueue":
                        System.out.println("Enter explorer name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter explorer rank:");
                        String rank = scanner.nextLine();
                        System.out.println("Enter explorer experience (years):");
                        int experience = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        Explorer newExplorer = new Explorer(name, rank, experience);
                        explorerQueue.enqueueExplorer(newExplorer);
                        break;

                    case "dequeue":
                        explorerQueue.dequeueExplorer();
                        break;

                    case "next":
                        explorerQueue.displayNextExplorer();
                        break;

                    case "display":
                        explorerQueue.displayQueue();
                        break;

                    case "log":
                        explorerQueue.displayExpeditionLog();
                        break;

                    case "stats":
                        explorerQueue.displayQueueStats();
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid action. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }
}

// Class to represent an explorer
class Explorer {
    String name;
    String rank;
    int experience; // Years of experience

    public Explorer(String name, String rank, int experience) {
        this.name = name;
        this.rank = rank;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Explorer{" + "name='" + name + '\'' + ", rank='" + rank + '\'' + ", experience=" + experience + " years}";
    }
}

// Class to maintain a log of expeditions
class ExpeditionLog {
    private ArrayList<String> log;

    public ExpeditionLog() {
        this.log = new ArrayList<>();
    }

    public void logExpedition(Explorer explorer) {
        log.add("Explorer " + explorer.name + " (Rank: " + explorer.rank + ") entered the temple.");
    }

    public void displayLog() {
        if (log.isEmpty()) {
            System.out.println("No expedition logs available.");
        } else {
            System.out.println("Expedition Log:");
            for (String entry : log) {
                System.out.println(entry);
            }
        }
    }
}

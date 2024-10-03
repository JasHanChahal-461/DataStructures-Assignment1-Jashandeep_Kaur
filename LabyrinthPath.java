import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

class LabyrinthPath {
    // Node class for linked list
    private static class Node {
        String location;
        Node next;

        Node(String location) {
            this.location = location;
            this.next = null;
        }
    }

    private Node head;

    // Add a new location to the path
    public void addLocation(String location) {
        Node newNode = new Node(location);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Location added: " + location);
    }

    // Remove the last visited location
    public void removeLastLocation() {
        if (head == null) {
            System.out.println("The path is empty. No locations to remove.");
            return;
        }
        if (head.next == null) {
            System.out.println("Removed location: " + head.location);
            head = null;
            return;
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        System.out.println("Removed location: " + temp.next.location);
        temp.next = null;
    }

    // Check if the path contains a loop (trap)
    public boolean hasLoop() {
        HashSet<Node> visitedNodes = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (visitedNodes.contains(temp)) {
                return true;
            }
            visitedNodes.add(temp);
            temp = temp.next;
        }
        return false;
    }

    // Print the entire path
    public void printPath() {
        if (head == null) {
            System.out.println("The path is empty.");
            return;
        }
        Node temp = head;
        System.out.print("Path: ");
        while (temp != null) {
            System.out.print(temp.location + " -> ");
            temp = temp.next;
        }
        System.out.println("End");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LabyrinthPath path = new LabyrinthPath();

        while (true) {
            try {
                System.out.println("\nChoose an action: add, remove, checkloop, print, or exit:");
                String action = scanner.nextLine().toLowerCase();

                switch (action) {
                    case "add":
                        System.out.println("Enter location name to add:");
                        String location = scanner.nextLine();
                        path.addLocation(location);
                        break;

                    case "remove":
                        path.removeLastLocation();
                        break;

                    case "checkloop":
                        if (path.hasLoop()) {
                            System.out.println("The path contains a loop (trap).");
                        } else {
                            System.out.println("The path does not contain a loop.");
                        }
                        break;

                    case "print":
                        path.printPath();
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid action. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid action.");
                scanner.next(); // Clear the scanner buffer
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}

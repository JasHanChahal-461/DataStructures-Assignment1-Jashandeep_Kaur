import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

class ScrollStack {
    private Stack<String> stack;
    private ArrayList<String> history;
    private int capacity;

    public ScrollStack(int capacity) {
        this.stack = new Stack<>();
        this.history = new ArrayList<>();
        this.capacity = capacity;
    }

    // Push a new scroll onto the stack
    public void pushScroll(String scroll) {
        if (stack.size() < capacity) {
            if (!stack.contains(scroll)) {
                stack.push(scroll);
                history.add("Pushed: " + scroll);
                System.out.println("Scroll added: " + scroll);
            } else {
                System.out.println("Scroll with the title '" + scroll + "' already exists. Duplicates are not allowed.");
            }
        } else {
            System.out.println("Stack is full! Cannot add more scrolls.");
        }
    }

    // Pop the top scroll off the stack
    public void popScroll() {
        if (!stack.isEmpty()) {
            String scroll = stack.pop();
            history.add("Popped: " + scroll);
            System.out.println("Removed scroll: " + scroll);
        } else {
            System.out.println("The stack is empty. No scrolls to remove.");
        }
    }

    // Peek at the top scroll without removing it
    public void peekScroll() {
        if (!stack.isEmpty()) {
            System.out.println("Top scroll is: " + stack.peek());
        } else {
            System.out.println("The stack is empty. No scrolls to peek at.");
        }
    }

    // Check if a specific scroll title exists in the stack
    public void searchScroll(String scroll) {
        if (stack.contains(scroll)) {
            System.out.println("The scroll '" + scroll + "' exists in the stack.");
        } else {
            System.out.println("The scroll '" + scroll + "' does not exist in the stack.");
        }
    }

    // Search for scrolls containing a specific substring
    public void searchScrollByPartialTitle(String partialTitle) {
        boolean found = false;
        for (String scroll : stack) {
            if (scroll.contains(partialTitle)) {
                System.out.println("Scroll found with partial title '" + partialTitle + "': " + scroll);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No scrolls found with the partial title '" + partialTitle + "'.");
        }
    }

    // Display the history of actions (push and pop)
    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No history to display.");
        } else {
            System.out.println("Scroll Action History:");
            for (String action : history) {
                System.out.println(action);
            }
        }
    }

    // Display all scrolls in the stack
    public void displayAllScrolls() {
        if (stack.isEmpty()) {
            System.out.println("The stack is empty.");
        } else {
            System.out.println("Scrolls in the stack: " + stack);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = 0;

        while (true) {
            try {
                System.out.println("Enter the capacity of the scroll stack:");
                capacity = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for capacity.");
                scanner.next(); // Clear the scanner buffer
            }
        }

        ScrollStack scrollStack = new ScrollStack(capacity);

        while (true) {
            try {
                System.out.println("\nChoose an action: push, pop, peek, search, searchPartial, history, display, or exit:");
                String action = scanner.nextLine().toLowerCase();

                switch (action) {
                    case "push":
                        System.out.println("Enter the scroll title to add:");
                        String scroll = scanner.nextLine();
                        scrollStack.pushScroll(scroll);
                        break;

                    case "pop":
                        scrollStack.popScroll();
                        break;

                    case "peek":
                        scrollStack.peekScroll();
                        break;

                    case "search":
                        System.out.println("Enter the scroll title to search for:");
                        String searchScroll = scanner.nextLine();
                        scrollStack.searchScroll(searchScroll);
                        break;

                    case "searchpartial":
                        System.out.println("Enter part of the scroll title to search for:");
                        String partialTitle = scanner.nextLine();
                        scrollStack.searchScrollByPartialTitle(partialTitle);
                        break;

                    case "history":
                        scrollStack.displayHistory();
                        break;

                    case "display":
                        scrollStack.displayAllScrolls();
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

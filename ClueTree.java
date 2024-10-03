import java.util.Scanner;

class ClueTree {
    private ClueNode root;

    // Class representing a node in the clue binary tree
    private class ClueNode {
        String clue;
        ClueNode left, right;

        ClueNode(String clue) {
            this.clue = clue;
            left = right = null;
        }
    }

    // Method to insert a new clue into the binary tree
    public void insert(String clue) {
        root = insertRec(root, clue);
    }

    private ClueNode insertRec(ClueNode root, String clue) {
        if (root == null) {
            root = new ClueNode(clue);
            return root;
        }

        if (clue.compareTo(root.clue) < 0) {
            root.left = insertRec(root.left, clue);
        } else if (clue.compareTo(root.clue) > 0) {
            root.right = insertRec(root.right, clue);
        }
        return root;
    }

    // In-order traversal
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(ClueNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.clue + " ");
            inOrderRec(root.right);
        }
    }

    // Pre-order traversal
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(ClueNode root) {
        if (root != null) {
            System.out.print(root.clue + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Post-order traversal
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(ClueNode root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.clue + " ");
        }
    }

    // Find a specific clue in the tree
    public boolean findClue(String clue) {
        return findClueRec(root, clue);
    }

    private boolean findClueRec(ClueNode root, String clue) {
        if (root == null) {
            return false;
        }

        if (root.clue.equals(clue)) {
            return true;
        }

        return clue.compareTo(root.clue) < 0 
            ? findClueRec(root.left, clue) 
            : findClueRec(root.right, clue);
    }

    // Count the total number of clues in the tree
    public int countClues() {
        return countCluesRec(root);
    }

    private int countCluesRec(ClueNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countCluesRec(root.left) + countCluesRec(root.right);
    }

    // Main method for user interaction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClueTree clueTree = new ClueTree();

        while (true) {
            System.out.println("\nChoose an action: insert, inOrder, preOrder, postOrder, find, count, exit:");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "insert":
                    System.out.println("Enter a clue to insert:");
                    String clue = scanner.nextLine();
                    clueTree.insert(clue);
                    System.out.println("Clue inserted: " + clue);
                    break;

                case "inorder":
                    System.out.println("In-order Traversal:");
                    clueTree.inOrder();
                    break;

                case "preorder":
                    System.out.println("Pre-order Traversal:");
                    clueTree.preOrder();
                    break;

                case "postorder":
                    System.out.println("Post-order Traversal:");
                    clueTree.postOrder();
                    break;

                case "find":
                    System.out.println("Enter a clue to find:");
                    String searchClue = scanner.nextLine();
                    boolean found = clueTree.findClue(searchClue);
                    if (found) {
                        System.out.println("Clue found: " + searchClue);
                    } else {
                        System.out.println("Clue not found: " + searchClue);
                    }
                    break;

                case "count":
                    int totalClues = clueTree.countClues();
                    System.out.println("Total clues in the tree: " + totalClues);
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}

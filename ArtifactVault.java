import java.util.Arrays;
import java.util.Scanner;

public class ArtifactVault {
    private String[] artifacts;
    private int size;

    public ArtifactVault(int capacity) {
        this.artifacts = new String[capacity];
        this.size = 0;
    }

    public void addArtifact(String artifact) {
        if (size < artifacts.length) {
            artifacts[size++] = artifact;
        } else {
            System.out.println("Vault is full!");
        }
    }

    public void removeArtifact(String artifact) {
        for (int i = 0; i < size; i++) {
            if (artifacts[i].equals(artifact)) {
                artifacts[i] = artifacts[size - 1];
                artifacts[size - 1] = null;
                size--;
                System.out.println("Removed: " + artifact);
                return;
            }
        }
        System.out.println(artifact + " not found.");
    }

    public int findArtifactLinear(String artifact) {
        for (int i = 0; i < size; i++) {
            if (artifacts[i].equals(artifact)) {
                return i;
            }
        }
        return -1;
    }

    public int findArtifactBinary(String artifact) {
        Arrays.sort(artifacts, 0, size);
        return Arrays.binarySearch(artifacts, 0, size, artifact);
    }

    public void displayArtifacts() {
        if (size == 0) {
            System.out.println("Vault is empty.");
        } else {
            System.out.println(Arrays.toString(Arrays.copyOf(artifacts, size)));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the capacity of the vault:");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        ArtifactVault vault = new ArtifactVault(capacity);

        while (true) {
            System.out.println("\nChoose an action: add, remove, search, display, or exit:");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "add":
                    System.out.println("Enter artifact name to add:");
                    String artifactToAdd = scanner.nextLine();
                    vault.addArtifact(artifactToAdd);
                    break;

                case "remove":
                    System.out.println("Enter artifact name to remove:");
                    String artifactToRemove = scanner.nextLine();
                    vault.removeArtifact(artifactToRemove);
                    break;

                case "search":
                    System.out.println("Enter artifact name to search:");
                    String artifactToSearch = scanner.nextLine();
                    System.out.println("Choose search method: linear or binary:");
                    String searchMethod = scanner.nextLine().toLowerCase();

                    int index;
                    if (searchMethod.equals("linear")) {
                        index = vault.findArtifactLinear(artifactToSearch);
                    } else if (searchMethod.equals("binary")) {
                        index = vault.findArtifactBinary(artifactToSearch);
                    } else {
                        System.out.println("Invalid search method.");
                        continue;
                    }

                    if (index >= 0) {
                        System.out.println(artifactToSearch + " found at index " + index);
                    } else {
                        System.out.println(artifactToSearch + " not found.");
                    }
                    break;

                case "display":
                    vault.displayArtifacts();
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid action. Try again.");
            }
        }
    }
}

import java.util.*;

public class TopKFrequentNumbers {
    // Static variable to store the input array
    static int[] arr;

    // Static method to find the top K frequent numbers
    public static void findTopKFrequent(int K) {
        // Step 1: Create a HashMap to store the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a list from elements of HashMap4
        List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());

        // Step 3: Sort the list by frequency, and by value if frequencies are the same
        frequencyList.sort((a, b) -> {
            if (b.getValue().equals(a.getValue())) {
                return b.getKey() - a.getKey(); // Sort by number in descending order if frequencies are equal
            }
            return b.getValue() - a.getValue(); // Sort by frequency in descending order
        });

        // Step 4: Output the top K frequent numbers
        System.out.println("Top " + K + " numbers with highest frequency:");
        for (int i = 0; i < K; i++) {
            System.out.print(frequencyList.get(i).getKey() + " ");
        }
    }

    // Main method to accept user input and call the static method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accept array size from the user
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Initialize the array and accept elements from the user
        arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Accept the value of K
        System.out.print("Enter the value of K: ");
        int K = scanner.nextInt();

        // Check if K is valid
        if (K > n || K <= 0) {
            System.out.println("K must be a positive integer less than or equal to the number of elements in the array.");
        } else {
            // Call the static method to find the top K frequent numbers
            findTopKFrequent(K);
        }

        // Close the scanner
        scanner.close();
    }
}

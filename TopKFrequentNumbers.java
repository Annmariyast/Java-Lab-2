import java.util.Scanner;

public class TopKFrequency {

    
    static int[] nums;

    // Static method to find the top K frequent numbers
    public static void findTopKFrequent(int K) {
        int n = nums.length;
        int[][] freq = new int[n][2]; // 2D array to store numbers and their frequencies

        // Step 1: Count the frequency of each number
        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (freq[j][0] == nums[i]) {
                    freq[j][1]++; // Increment frequency if the number is already counted
                    found = true;
                    break;
                }
            }
            if (!found) {
                for (int j = 0; j < n; j++) {
                    if (freq[j][0] == 0 && freq[j][1] == 0) { // Empty spot in freq array
                        freq[j][0] = nums[i]; // Store the number
                        freq[j][1] = 1; // Set frequency to 1
                        break;
                    }
                }
            }
        }

        // Step 2: Sort the numbers based on their frequency and value
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Sort by frequency in descending order, and by value in case of tie
                if (freq[i][1] < freq[j][1] || (freq[i][1] == freq[j][1] && freq[i][0] < freq[j][0])) {
                    // Swap the elements
                    int tempNum = freq[i][0];
                    int tempFreq = freq[i][1];
                    freq[i][0] = freq[j][0];
                    freq[i][1] = freq[j][1];
                    freq[j][0] = tempNum;
                    freq[j][1] = tempFreq;
                }
            }
        }

        // Step 3: Output the top K numbers with the highest frequency
        int printed = 0;
        for (int i = 0; i < n && printed < K; i++) {
            if (freq[i][1] > 0) { // Only print numbers with non-zero frequency
                System.out.print(freq[i][0] + " ");
                printed++;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Enter the number of elements (N)
        System.out.println("Enter the number of elements (N):");
        int N = sc.nextInt();
        nums = new int[N]; // Initialize the static array

        // Input: Enter the array elements
        System.out.println("Enter the array elements:");
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        // Input: Enter the value of K
        System.out.println("Enter the value of K:");
        int K = sc.nextInt();

        // Call the method to find and print the top K frequent numbers
        findTopKFrequent(K);
    }
}

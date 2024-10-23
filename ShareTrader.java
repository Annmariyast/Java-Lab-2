import java.util.Scanner;

public class ShareTrader {

    // Static variable to store the maximum profit
    static int maxProfit = 0;

    // Static method to find the maximum profit with at most 2 transactions
    public static void findMaxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            System.out.println("Invalid input, prices array must have at least 2 values.");
            return;
        }

        int n = prices.length;

        // Create two arrays to store the maximum profit from left and right
        int[] leftProfit = new int[n];  // max profit we can get by doing 1 transaction from 0 to i
        int[] rightProfit = new int[n]; // max profit we can get by doing 1 transaction from i to n-1

        // Fill the leftProfit array
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        // Fill the rightProfit array
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
            maxPrice = Math.max(maxPrice, prices[i]);
        }

        // Calculate the maximum profit by combining leftProfit and rightProfit
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, leftProfit[i] + rightProfit[i]);
        }

        // Print detailed transactions
        printTransactions(prices, leftProfit, rightProfit);
    }

    // Method to print the detailed transactions
    public static void printTransactions(int[] prices, int[] leftProfit, int[] rightProfit) {
        int n = prices.length;
        int buy1 = 0, sell1 = 0, buy2 = 0, sell2 = 0;
        int minPrice = prices[0];
        int maxProfit1 = 0;

        // Find the first transaction (buy and sell)
        for (int i = 1; i < n; i++) {
            if (prices[i] - minPrice > maxProfit1) {
                maxProfit1 = prices[i] - minPrice;
                buy1 = minPrice;
                sell1 = prices[i];
            }
            minPrice = Math.min(minPrice, prices[i]);
        }

        // Find the second transaction (buy and sell)
        int maxPrice = prices[n - 1];
        int maxProfit2 = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (maxPrice - prices[i] > maxProfit2) {
                maxProfit2 = maxPrice - prices[i];
                buy2 = prices[i];
                sell2 = maxPrice;
            }
            maxPrice = Math.max(maxPrice, prices[i]);
        }

        // Output the results
        System.out.println("Maximum Profit: " + maxProfit);
        System.out.println("Trader earns " + maxProfit + " as sum of " + (sell1 - buy1) + ", " + (sell2 - buy2));
        System.out.println("Buy at " + buy1 + ", sell at " + sell1);
        System.out.println("Buy at " + buy2 + ", sell at " + sell2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of stock prices
        System.out.println("Enter the number of stock prices:");
        int n = scanner.nextInt();

        // Input the stock prices from the user
        int[] prices = new int[n];
        System.out.println("Enter the stock prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        // Find and print the maximum profit
        findMaxProfit(prices);
        
        // Close the scanner
        scanner.close();
    }
}

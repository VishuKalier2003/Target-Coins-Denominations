/* You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money... Return the fewest number of coins that you need to make up that amount... If that amount of money cannot be made up by any combination of the coins, return -1... You may assume that you have an infinite number of each kind of coin...
 * Eg 1: coins = [1,2,5]     target = 11     Output = 3 coins = 5 + 5 + 1
 * Eg 2: coins = [2]         target = 3      Output = -1 (not possible)
 * Eg 3: coins = [1]         target = 0      Output = 0 coins
 * Eg 4: coins = [2,3,5]     target = 13     Output = 3 coins = 5 + 5 + 3
 */
import java.util.*;
public class Coins
{
    public int MinimumCoinsTarget(int coins[], int target)
    {
        if(target == 0)
            return 0;
        int dp[][] = new int[coins.length][target];    // Creating the Dynamic Programming 2d array...
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();    // Hashmap to store the unique coins...
        for(int i = 0; i < dp.length; i++)   // Row length...
        {
            map.put(coins[i], 0);    // Using the coin value as the Key...
            for(int j = 1; j <= dp[i].length; j++)     // Column length...
            {
                if(j % coins[i] == 0)    // If the current target is divisible by the coin...
                    dp[i][j-1] = j/coins[i];
                else if(map.containsKey(j%coins[i]))   // If the current target is not divisible but the difference is present in the HashMap...
                    dp[i][j-1] = (int)(j/coins[i]) + 1;
                else    // Otherwise the current target is impossible to be matched...
                    dp[i][j-1] = -1;
            }
        }
        System.out.println();
        for(int i = 0; i < dp.length; i++)     // Printing the DP array...
        {
            for(int j = 0; j < dp[i].length; j++)
                System.out.print(dp[i][j]+", ");
            System.out.println();
        }
        return dp[dp.length-1][dp[0].length-1];    // Returning the rightmost bottom index...
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the number of types of coins provided : ");
        x = sc.nextInt();
        int arr[] = new int[x];
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print("Enter the value of "+(i+1)+" th coin : ");
            arr[i] = sc.nextInt();
        }
        System.out.print("Enter the target value : ");
        x = sc.nextInt();
        Coins coin = new Coins();    // Object creation...
        System.out.println("The Minimum number of coins required : "+coin.MinimumCoinsTarget(arr, x));
        sc.close();
    }
}

// Time Complexity  - O(n^2) time...       n is the number of coins provided...
// Space Complexity - O(n^2) space...

/* DEDUCTIONS :- 
 * 1. Solving by creating a Knapsack Algorithm solves the purpose...
 * 2. We greedily choose a coin available and make it multiply closest to n, then if the difference is possible to evaluate by the help of other coins we increment it accordingly else it is impossible to get the target value...
*/
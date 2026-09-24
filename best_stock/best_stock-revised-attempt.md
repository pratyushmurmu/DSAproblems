# 121. Best Time to Buy and Sell Stock
## My Approach (Brute Force):
```
class Solution {
    public int maxProfit(int[] prices) {
        for(int i=0; i< prices.length; i++){
            for(int j =i+1; j< prices.length; j++){
                if(prices[j]<prices[i]){
                    int profit= Math.max(prices[j]-prices[i]);
                }else{
                    return 0;
                }
            }
        }
        return profit;
    }
}
```

It shows these errors:
```
Line 6: error: no suitable method found for max(int)
                    int profit= Math.max(prices[j]-prices[i]);
                                    ^
    method Math.max(int,int) is not applicable
      (actual and formal argument lists differ in length)
    method Math.max(float,float) is not applicable
      (actual and formal argument lists differ in length)
    method Math.max(double,double) is not applicable
      (actual and formal argument lists differ in length)
    method Math.max(long,long) is not applicable
      (actual and formal argument lists differ in length)
Line 12: error: cannot find symbol
        return profit;
               ^
  symbol:   variable profit
  location: class Solution
2 errors
```
## The Errors:
- `Math.max` **requires TWO parameters**: `Math.max(a, b)` compares two values. Passing a single expression like `Math.max(prices[j] - prices[i])` triggers a syntax error.

- **Variable Scope Error:** You declared `int profit` inside the inner `for` loop. It cannot be accessed outside the loops on `return profit;`.

- **Flawed Condition & Early `return 0`:**

* `if (prices[j] < prices[i])` calculates profit when price drops, which yields a negative number. Profit happens when selling price is higher: `prices[j] > prices[i]`.

* The `else { return 0; }` instantly terminates the entire program on the very first pair where `prices[j] >= prices[i]`.

## Correct Approach (Time Limit Exceeded):
````
class Solution {
    public int maxProfit(int[] prices) {
        int MaxProfit= 0;
        for(int i=0; i< prices.length; i++){
            for(int j =i+1; j< prices.length; j++){
                int currProfit= prices[j] - prices[i];
                MaxProfit= Math.max(currProfit, MaxProfit);
            }
        }
        return MaxProfit;
    }
}
````
#### Time Complexity: $O(N^2)$ — Will get Time Limit Exceeded (TLE) on LeetCode for large inputs.

#### Space Complexity: $O(1)$ (constant space)

## Correct Approach (Sliding Window):
```
class Solution {
    public int maxProfit(int[] prices) {
        int l=0; // Buy pointer (left)
        int r=1; // Sell pointer (right)
        int maxProfit= 0;
        while(r<prices.length){
            // Check if the trade is profitable
            if(prices[r] > prices[l]){
                int profit = prices[r] - prices[l];
                maxProfit = Math.max(maxProfit, profit);
            }else{
                // We found a price cheaper than our buy price.
                // Slide 'l' directly to 'r' because 'r' is now our best buy day!
                l = r;
            }
            r++; // Keep expanding the window to check future sell days
        }
        return maxProfit;
    }
}
```
#### Time Complexity: $O(N)$
#### Space Complexity: $O(1)$

The time complexity is $O(N)$ because each pointer (`l` and `r`) traverses the array at most once, moving strictly from left to right without ever stepping backward.

#### Step-by-Step Breakdown
1. The `r` (Sell) Pointer:
- Starts at index `1` and increments by $1$ in every single iteration of the `while` loop (`r++`).
- It moves from index `1` to `prices.length - 1`.
- Therefore, the `while` loop executes at most $N - 1$ times.
2. The `l` (Buy) Pointer:
- Starts at index `0`.
- When `prices[r] <= prices[l]`, `l` jumps directly to `r` (`l = r`).
- Because `r` is moving forward, `l` only ever moves forward. It **never backtracks**.
3. Operations Inside the Loop:
- Comparing `prices[r]` with `prices[l]` takes $O(1)$ time.
- Updating `maxProfit` takes $O(1)$ time.
- Reassigning `l` or incrementing `r` takes $O(1)$ time.

In the $O(N)$ Sliding Window: There is only one loop. Even when `l` jumps to `r`, no previous elements are ever re-visited. 
**Total operations:** $N - 1 \approx O(N)$.
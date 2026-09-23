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

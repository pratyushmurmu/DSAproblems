# Best Time to Buy and Sell Stock
You are given an array `prices` where `prices[i]` is the price of a given stock on the `ith` day.

You want to maximize your profit by choosing a **single day** to buy one stock and choosing a **different day in the future** to sell that stock.

Return *the maximum profit you can achieve from this transaction*. If you cannot achieve any profit, return `0`.

* **Example 1:**

**Input:** `prices` = [`7`,`1`,`5`,`3`,`6`,`4`]

**Output:** `5`

**Explanation:** Buy on `day 2` (`price = 1`) and sell on `day 5` (`price = 6`), `profit` = `6-1` = `5`.
Note that buying on `day 2` and selling on `day 1` is `not allowed` because you must buy before you sell.

* **Example 2:**

**Input:** `prices` = [`7`,`6`,`4`,`3`,`1`]

**Output:** `0`

**Explanation:** In this case, no transactions are done and the `max profit` = `0`.

**Constraints:**

* `1 <= prices.length <= 10^5`
* `0 <= prices[i] <= 10^4`

### My answer (attempted Two-Pointer Approach):
````
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit= 0;
        int l= 0;
        int r=1;
        for(int i=0; i< prices.length; i++){
            while(l < r){
                int profit = prices[r] - prices[l];
                maxProfit= Math.max(profit);
                if(maxProfit= Math.max(profit){
                    return maxProfit;
                    l++;
                }else{
                    r--;
                }
            }
        }
        return maxProfit;
    }
}

````

#### My Mistakes:
1. **Compiler Errors ❌**

- **Missing Parenthesis:** As the error message states, `if(maxProfit= Math.max(profit){` is missing a closing parenthesis `)` for the `if` condition.

- **Invalid Method Signature for `Math.max()`:** `Math.max()` requires two arguments to compare (e.g., `Math.max(maxProfit, profit)`), but you passed only one (`profit`).

- **Using Assignment (`=`) Instead of Comparison (`==`):** Inside the `if` statement, you used the assignment operator `=` instead of `==`.

- **Unreachable Code:** Moving `l++` after `return maxProfit`; means `l++` can never be executed because the method exits immediately upon hitting `return`.

2. **Logical Flaws & Runtime Risks**

 - **Flaw A: `ArrayIndexOutOfBoundsException`**
 
 I initialized `r = 1` static at the top. When the inner `while` loop executes and hits `r--`, `r` becomes `0`. On subsequent passes or iterations, `r` will go negative (`-1`), causing an out-of-bounds error when evaluating `prices[r]`.
 
 - **Flaw B: Unnecessary Outer `for` Loop** 
 
 Using both an outer `for (int i = 0; ...)` loop and an inner `while (l < r)` loop creates redundant iteration overhead. For this problem, a single pass ($\mathcal{O}(n)$) with two pointers or a single sliding tracking variable is sufficient.
 
 - **Flaw C: Premature Early Return** 
 
 Returning `maxProfit` as soon as a profit calculation runs exits the method after checking just one pair of days, missing potentially higher profits later in the array.


### Corrected answer(Two-Pointer Approach):

**Core Intuition:**

- `l` represents **the buying day** (we want the minimum price).

- `r` represents **the selling day** (we check future days).

- If `prices[l] < prices[r]`, it's a profitable transaction! Calculate profit, update `maxProfit`, and advance `r++`.

- If `prices[l] >= prices[r]`, we found a lower buying price! Shift our buy pointer to `l = r` and advance `r++`.


```
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit= 0;
        int l= 0;
        int r=1;
        
        while(r < prices.length){
            if(prices[l] < prices[r]){
                int profit= prices[r] - prices[l];
                maxProfit= Math.max(maxProfit, profit);
            }else{
                l=r;
            }
            r++;
        }
        return maxProfit;
    }
}
```
- **Time Complexity:** ($\mathcal{O}(n)$)
#### Because
- **Single Traversal:** The `while` loop is controlled entirely by the right pointer `r`.
- **Deterministic Pointer Steps:** 
- `r` starts at index `1`.
- In every single iteration of the loop, r increments by `1` (`r++`).
- `r` never moves backward or resets to `0`. 
- $\mathcal{O}(1)$ **Operations Inside:** Inside the loop, all operations are simple arithmetic subtraction (`prices[r] - prices[l]`), comparisons (`<`), and pointer assignments (`l = r`). Each takes constant time, $\mathcal{O}(1)$.
- **Total Steps:** The loop executes exactly $n - 1$ times, where $n$ is `prices.length`.
$$\text{Total Time} = (n - 1) \times \mathcal{O}(1) = \mathcal{O}(n)$$

- **Space Complexity:** ($\mathcal{O}(1)$)

Uses a fixed amount of extra memory (only tracking `l`, `r`, and `maxProfit` variables).
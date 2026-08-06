# Find Subarrays With Equal Sum

Given a **0-indexed integer** array `nums`, determine whether there exist **two** subarrays of length `2` with **equal** sum. Note that the two subarrays must begin at **different** indices.

Return `true` *if these subarrays exist, and* `false` *otherwise*.

A **subarray** is a contiguous non-empty sequence of elements within an array.

**Example 1:**
- **Input:** nums = [4, 2, 4]
- **Output:** true
- **Explanation:** The subarrays with elements [4,2] and [2,4] have the same sum of 6.

**Example 2:**

- **Input:** nums = [1,2,3,4,5]

- **Output:** false
- **Explanation:** No two subarrays of size 2 have the same sum.

**Example 3:**

- **Input:** nums = [0,0,0]
- **Output:** true
- **Explanation:** The subarrays [nums[0],nums[1]] and [nums[1],nums[2]] have the same sum of 0. 
Note that even though the subarrays have the same content, the two subarrays are considered different because they are in different positions in the original array.

**Constraints:**

- `2 <= nums.length <= 1000`

- `-10^9 <= nums[i] <= 10^9`

### My Approach:
I tried solving this problem by taking:  if the array `nums` has all **equal integers**, then return `true`. Then by **two pointers** approach if any two subarrays `sum` are **equal** then return `true`, or else `false`. 
Couldn't able to write the code for this.
### My Mistake:
1. ### Why the Logic Fails ❌
**Problem Definition**

Given a **0-indexed integer** array `nums`, determine if there exist two subarrays of length 2 (indices (`i, i+1`) and (`j, j+1`) with `i != j`) such that `nums[i] + nums[i+1] == nums[j] + nums[j+1]`.

- **Flaw 1: "If all elements are equal, return true"**

While an array like `[2, 2, 2]` does return `true` (since `2+2 == 2+2`), checking only this condition misses almost all valid test cases where elements are not equal (e.g., `[4, 2, 3, 3]`, where `4+2 = 6` and `3+3 = 6`).

- **Flaw 2: Two-Pointer Fallacy**

Two-pointer approaches (`left` at start, `right` at end) rely on the array being sorted or having a monotonic property to decide whether to move `left++` or `right--`.

1. **You cannot sort the array:** The problem requires checking consecutive adjacent pairs in their original contiguous order. Sorting destroys the original subarray adjacencies.

2. **Unsorted Two-Pointers fail:** If the array is unsorted, comparing two distant pairs gives no direction on which pointer to advance to find matching sums.

### Corrected Approach(Brute Force or Nested Loops):

````
class Solution {
    public boolean findSubarrays(int[] nums) {
        int n= nums.length;
         for(int i=0; i<n-1;i++){
            int sum1= nums[i]+nums[i+1];
            for(int j=i+1; j<n-1; j++){
                int sum2= nums[j] + nums[j+1];
                if(sum1 == sum2){
                    return true;
                }
            }
         }
         return false;
    }
}
````


```
// Outer loop selects the first pair starting at index i
        for (int i = 0; i < n - 1; i++) {
            int sum1 = nums[i] + nums[i + 1];

            // Inner loop compares with every pair starting after i
            for (int j = i + 1; j < n - 1; j++) {
                int sum2 = nums[j] + nums[j + 1];

                // If any two pair sums match, we found a duplicate!
                if (sum1 == sum2) {
                    return true;
                }
            }
        }
```
**Step-by-Step Dry Run**(`nums = [4, 2, 3, 3]`)
- `n = 4`

- **Outer Loop `i = 0`:**
**Pair 1:** `nums[0] + nums[1]` $\rightarrow 4 + 2 = 6$ (`sum1 = 6`)
- **Inner Loop j = 1:**
- **Pair 2** is `nums[1] + nums[2]` $\rightarrow 2 + 3 = 5$ (`sum2 = 5`).
 **Is 6 == 5? ❌**

- **Inner Loop j = 2:** **Pair 2** is `nums[2] + nums[3]` $\rightarrow 3 + 3 = 6$ (`sum2 = 6`). 
**Is 6 == 6? ✅ Match found!** Return `true`.

 - **Complexity Analysis:**
 - **Time Complexity:** $\mathcal{O}(n^2)$ — You check $\frac{(n-1)(n-2)}{2}$ pair combinations in the worst case using nested loops.
 - **Space Complexity:** $\mathcal{O}(1)$ — No extra data structures are allocated; only primitive integer variables are used.

 ### Corrected Approach(HashSet Approach):
 ```
 class Solution {
    public boolean findSubarrays(int[] nums) {
        int n= nums.length;
        Set<Integer> equalSum = new HashSet<>();
        
        for(int i=0; i<n-1;i++){
            int currSum= nums[i] + nums[i+1];
             if(equalSum.contains(currSum)){
                return true;
             }else{
                equalSum.add(currSum);
             }
        }
        return false;
    }
}
 ```
 
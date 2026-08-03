# Number of Distinct Averages:

You are given a **0-indexed** integer array `nums` of **even** length.

As long as `nums` is **not** empty, you must repetitively:

* Find the minimum number in `nums` and remove it.
* Find the maximum number in `nums` and remove it.
* Calculate the average of the two removed numbers.

The **average** of two numbers `a` and `b` is `(a + b) / 2`.

For example, the average of `2` and `3` is `(2 + 3) / 2 = 2.5`.

***Return the number of **distinct** averages calculated using the above process.***

**Note** that when there is a tie for a minimum or maximum number, any can be removed.

### My answer: 
```
class Solution {
    public int distinctAverages(int[] nums) {
        if(nums==null){
            return 0;
        }
        Set<Integer> res= new HashSet<>();
        Arrays.sort(nums);
        int i=0;
        int l=0;
        int r= nums.length -1;
        while(i<nums.length){
            if(l<r){
                float avg = (l + r)/2;
                l++;
                i++;
            }
        }
        return avg;
    }
}
```
#### My mistakes: 
#### 1. Using Index Values Instead of Array Elements
```
float avg = (l + r) / 2;
```
You are adding the index positions l and r together instead of the actual numbers inside the array at those indices (nums[l] and nums[r]).

#### 2. Infinite Loop & Unused Variables
* **Infinite Loop:** The loop condition is `while(i < nums.length)`. However, `i` is never incremented inside the loop, so `i < nums.length` stays `true` forever!

* `r` never decrements: You increment `l++`, but `r` stays fixed at its initial value, breaking the two-pointer approach.

#### 3. Scope Error (return avg; outside method)
You declared `float avg` inside the `while` loop block:

* Variable `avg` only exists inside that loop.

* Trying to return `avg` outside the loop (and outside the method body due to mismatched closing braces) causes a `cannot find symbol` compiler error.

* The method signature specifies an `int` return type `(public int distinctAverages)`, but `avg` is a `float`.

#### 4. Braces Misplacement
Your closing brace `}` on line 15 closes the `distinctAverages` method before the `return` statement on line 16, resulting in dangling code outside any method.
### Corrected answer:
```
class Solution {
    public int distinctAverages(int[] nums) {
        if(nums==null || nums.length == 0){
            return 0;
        }
        Set<Integer> res= new HashSet<>();
        Arrays.sort(nums);
        
        int l=0;
        int r= nums.length -1;
        while(l < r){
            
            int sum = (nums[l] + nums[r]);
            res.add(sum);
            l++;
            r--;
        }
        return res.size();
    }
}
```

# 4Sum Problem
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
* 0 <= a, b, c, d < n
* a, b, c, and d are distinct.
* nums[a] + nums[b] + nums[c] + nums[d] == target

You may return the answer in any order.

<b>Example 1:
Input:</b>
nums: [1,0,-1,0,-2,2] , target= 0
<b>Output:</b>[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

### Brute Force Approach: (4 Loops + HashSet)

**💡Intuition:** Check every possible combination of four numbers using four nested loops. To handle duplicates, store the valid quadruplets in a `HashSet`.

```
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>>res= new HashSet<>();
        Arrays.sort(nums);

        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j< nums.length;j++){
                for(int k=j+1; k<nums.length;k++){
                    for(int l= k+1; l<nums.length;l++){
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target){
                            res.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        }
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
```
**Time Complexity:** $\mathcal{O} (n^4)$

Due to 4 for loops

**Space Complexity:** $\mathcal{O}(n)$

Storing duplicate quadruplets in a HashSet

### Two-Pointer Approach:
**💡Intuition:**
* Sorting the array first to allow us to predict whether moving pointers inward increases or decreases the target sum.
* Fix the first two numbers (i and j) with outer loops, then use left and right pointers to search the remaining subarray in $\mathcal{O}(n)$ time instead of $\mathcal{O}(n^2)$.

* **Integer Overflow Guard:** Explicitly cast sums to long before evaluation to prevent arithmetic wrap-around.

```
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>>res= new HashSet<>();
        Arrays.sort(nums);
        int n= nums.length;

        for(int i=0; i<n-3; i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1; j< n-2;j++){
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }

                int l=j+1; 
                int r=n-1;
                while(l<r){
                    long sum= (long) nums[i] + nums[j]+ nums[l] +nums[r];
                    if(sum==target){
                        res.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while(l<r && nums[l]==nums[l+1]){
                            l++;
                        }
                        while(l<r && nums[r]==nums[r-1]){
                            r--;
                        }
                        l++;
                        r--;
                    }
                    else if(sum<target){
                            l++;
                        }else{
                            r--;
                        }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
```

**Time Complexity:** $\mathcal{O}(n^3)$

 two nested loops $\mathcal{O}(n^2)$ with an $\mathcal{O}(n)$ two-pointer traversal inside.

**Space Complexity:** $\mathcal{O}(1)$

auxiliary space (excluding space needed for the output list)


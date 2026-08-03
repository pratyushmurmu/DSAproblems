# Count Pairs Whose Sum is Less than Target
Given a **0-indexed** integer array `nums` of length `n` and an integer `target`, return the number of pairs `(i, j)` where `0 <= i < j < n` and `nums[i] + nums[j] < target`.
 
 **Example 1:**
 * **Input:** nums = [-1, 1, 2, 3, 1], target = 2
 * **Output:** 3
 * **Explanation:** 

 There are 3 pairs of indices that satisfy the conditions in the statement:
- (0, 1) since 0 < 1 and nums[0] + nums[1] = 0 < target
- (0, 2) since 0 < 2 and nums[0] + nums[2] = 1 < target 
- (0, 4) since 0 < 4 and nums[0] + nums[4] = 0 < target
Note that (0, 3) is not counted since nums[0] + nums[3] is not strictly less than the target.

**Example 2:**
* **Input:** nums = [-6,2,5,-2,-7,-1,3], target = -2
* **Output:** 10
* **Explanation:** 

There are 10 pairs of indices that satisfy the conditions in the statement:
- (0, 1) since 0 < 1 and nums[0] + nums[1] = -4 < target
- (0, 3) since 0 < 3 and nums[0] + nums[3] = -8 < target
- (0, 4) since 0 < 4 and nums[0] + nums[4] = -13 < target
- (0, 5) since 0 < 5 and nums[0] + nums[5] = -7 < target
- (0, 6) since 0 < 6 and nums[0] + nums[6] = -3 < target
- (1, 4) since 1 < 4 and nums[1] + nums[4] = -5 < target
- (3, 4) since 3 < 4 and nums[3] + nums[4] = -9 < target
- (3, 5) since 3 < 5 and nums[3] + nums[5] = -3 < target
- (4, 5) since 4 < 5 and nums[4] + nums[5] = -8 < target
- (4, 6) since 4 < 6 and nums[4] + nums[6] = -4 < target

**Constraints:**

- `1 <= nums.length == n <= 50`
- `-50 <= nums[i], target <= 50`

### My answer:
* **Brute Force Approach:**
```
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int n= nums.length;
        
        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                int sum= nums[i]+nums[j];
                if(sum < target){
                    res.add(sum);
                }
            }
        }
        return res.size();
    }
}

```
#### My Mistakes:
1. **`List<Integer>` Syntax vs. Primitive Array `int[]` Syntax**

The input parameter is a `List<Integer>` (`nums`), not a raw Java array (`int[]`).

* **Size:** Use `nums.size()`, not `nums.length`.

* **Accessing Elements:** Use `nums.get(i)` and `nums.get(j)`, not `nums[i]` or `nums[j]`.

2. **Undefined Variable `res`**

You used `res.add(sum)` and `res.size()`, but you never declared or initialized `res` (like `List<Integer> res = new ArrayList<>();`).

3. **Logical Flaw: Incorrect Pair Indexing & Duplicate Counting**

* **Index Constraint:** The problem specifies finding pairs where `0 <= i < j < n`.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
- Starting `j` at `0` means you are comparing elements against themselves `(i == j)` and counting pairs twice `((i, j) and (j, i))`.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
- The inner loop should start at `j = i + 1`.

* **Counting Unique Pairs vs. Total Valid Pairs:**
You don't need a list or set to collect sums. You simply need a `count` variable that increments whenever `nums.get(i) + nums.get(j) < target`.

* **Two-Pointer Approach:**
```
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int n= nums.size();
        int count=0;
        int l=0;
        int r=n-1;
        for(int i=0; i<n; i++){
            if(nums.get(l)+nums.get(r) < target){
                count++;
            }
        }
        return count;
    }
}
```
#### My Mistakes:
1. **Missing `Collections.sort(nums)`**

The two-pointer technique relies on the array/list being sorted first.

Without sorting, comparing the left boundary and right boundary gives no guarantee about whether moving `l` or `r` will increase or decrease the sum.

2. **Static Pointers (Infinite Repeat Check)**

In my code,

```
int l = 0;
int r = n - 1;
for (int i = 0; i < n; i++) {
    if (nums.get(l) + nums.get(r) < target) {
        count++;
    }
}
```
- `l` is set to `0` and `r` is set to `n - 1`, but they are never updated inside the loop!
- The loop runs $n$ times checking the exact same pair (`nums[0]` + `nums[n-1]`) over and over.

**In Case 1:**

`nums` = [`-1`, `1`, `2`, `3`, `1`], `n` = 5, `target` = 2

`nums[0] + nums[4]` **$\rightarrow -1 + 1 = 0 < 2$ (True)** 

Since `l` and `r` never change, it checks this **5** times and outputs **5** (instead of the expected **3**).

3. **Counting Logic: `count++` vs. `count += (r - l)`**

Even if you move `l++` or `r--`, doing `count++` only counts **1 pair** at a time.

When the list is sorted, if `nums[l] + nums[r] < target`, then `nums[l]` added to any element between `l` and `r` will also be strictly less than `target`!

Instead of moving `l` one by one to count them individually, you can add `r - l` directly to `count` in a single step.

#### Correct and Submitted Answer:
* **Brute Force Approach:**

```
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int n= nums.size(); // Fix 1: Use .size() for List
        int count = 0;
        
        for(int i=0; i<n;i++){
            for(int j=i+1; j<n; j++){ // Fix 2: j starts from i + 1
                int sum= nums.get(i) + nums.get(j);
                if(sum < target){ // Fix 3: Use .get() method
                    count++;
                }
            }
        }
        return count;
    }
}
```
- **Time Complexity:** $\mathcal{O}(n^2)$
- **Space Complexity:** $\mathcal{O}(1)$

* **Two-Pointer Approach:**

```
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);// Sort the list (Crucial for Two-Pointer!)

        int n= nums.size();
        int count=0;
        int l=0;
        int r=n-1;
        
        // Shrink the window until pointers meet
        while(l<r){
            if(nums.get(l)+nums.get(r) < target){
            // All pairs between l and r starting at nums[l] are valid

                count+=(r-l);
                l++; // Move left pointer inward to check next element
            }else{
                r--; // Sum is >= target, shrink right pointer to reduce sum
            }
        }
        return count;
    }
}

```
**Step-by-Step Dry Run (Case 1)**

- **Input:** `nums = [-1, 1, 2, 3, 1]`, `target = 2`

- **Sort:** `nums = [-1, 1, 1, 2, 3]`

- **Init:** `l = 0` (`-1`), `r = 4` (`3`), `count = 0`

| **Iteration** | | **nums[l]** | | **nums[r]** | | **Sum** | | **Condition < target** | | **Count Update** | | **Pointer Update** |

| `1` | | `-1` | | `3` | | `2` | | `2 < 2` ❌ | |`count = 0` | | `r--` (`r = 3`) |

| `2` |  | `-1` | | `2` | | `1` | | `1 < 2` ✅ | | `count += (3 - 0)` **$\rightarrow$** `3` | | `l++` (`l = 1`) |

| `3` | | `1` | | `2` | | `3` | | `3 < 2` ❌ | | `count = 3` | | `r--` (`r = 2`) |

| `4` | | `1` | | `1` | | `2` | | `2 < 2` ❌| | `count = 3` | | `r--` (`r = 1`) |

**Termination:** `l < r` (`1 < 1`) is `false`.

**Final Output:** `3` (`Matches Expected!`).


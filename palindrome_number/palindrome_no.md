# Palindrome Number
Given an integer `x`, return `true` if `x` is a `palindrome`, and `false` otherwise.

**Example 1:** 
* **Input:** x = 121
* **Output:** true
* **Explanation:** 121 reads as 121 from left to right and from right to left.

**Example 2:**
* **Input:** x = -121
* **Output:** false
* **Explanation:** From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

**Example 3:**
* **Input:** x = 10
* **Output:** false
* **Explanation:** Reads 01 from right to left. Therefore it is not a palindrome.

## My first answer: (errors)
```
class Solution {
    public boolean isPalindrome(int x) {
        char[] res= Integer.toString(x).toCharArray();

        int left=0;
        int right= res.length-1;

        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        if(res == temp){
            return true;
        }else{
            return false;
        }
    }
}
```
1. **Compiler Errors ❌**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If you try to run this code in Java, it will throw multiple errors:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Undefined variable `arr`: You named your array `res`, but inside the while loop, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;you used `arr[left]` and `arr[right]`.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Undefined variable `temp` outside the loop: temp was declared inside the `while` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;loop as a `char`. In the `if (res == temp)` check at the end, Java will complain &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;that `temp` doesn't exist in that scope.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Comparing Array to Primitive: `res` is a `char[]` array, whereas `temp` was a &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`char`. You cannot compare an array to a single character using `==`.

2. **Conceptual & Logical Flaws 🧠**

**Flaw A: Comparing Arrays in Java (==)**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
In Java, comparing arrays using `==` checks whether they point to the same memory location, not whether their contents are equal. Even if you created a copy array, `array1` == `array2` would return `false`.

**Flaw B: Reversing In-Place Loses the Original Reference**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
In your loop, you are swapping characters directly inside `res`. If you reverse `res` in-place, by the time the loop finishes, you no longer have the original string array to compare against—you've mutated the original array!

**Flaw C: You Don't Actually Need to Reverse the Array!**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Since you are using two pointers (left moving right, right moving left), you just need to compare the characters at those pointers. If at any point `res[left]` != `res[right]`, it is `not a palindrome`. If left and right meet without any mismatch, it is a `palindrome`.

## Correct answer: (Two-Pointer Approach)
```
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        char[] res= Integer.toString(x).toCharArray();

        int left=0;
        int right= res.length-1;

        while(left < right){
            if(res[left] != res[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```
### Key intuition 🔑:
* **Negative Number Rule:** Any negative integer (e.g., `-121`) ends with a `-` sign when converted to a string (`"121-"`). Since it can't start with a `-`, negative numbers are automatically not palindromes.

* **Two-Pointer Traversal:** By initializing `left = 0` (start) and `right = length - 1` (end), you can check symmetry moving inward toward the center.

* **Early Exit:** The moment `res[left] != res[right]`, the method returns `false` immediately without checking the rest of the array.

* **Early Termination:** The loop runs while `left < right`. For odd-length strings, the middle character doesn't need comparison (it always equals itself). For even-length strings, the pointers cross cleanly.

### 🧪 Step-by-Step Dry Runs:
**Test Case 1:** `x = 121` (Valid Palindrome)
Initial Checks & Conversion:

* `x < 0` is `false`.

* `res = ['1', '2', '1']`

* `left = 0`, `right = 2`

**Loop Iterations:** **Iteration 1:** 
*  `res[0]` (`'1'`) `==` `res[2]` (`'1'`) $\rightarrow$ Match! ✅

* `left` becomes `1`, `right` becomes `1`.

**Loop Condition Check:** `left < right` (`1 < 1`) is `false` $\rightarrow$ Loop exits!

**Return:**

* Returns `true`.

**Test Case 2:** 
`x = -121` (Negative Number)
* **Initial Checks:** `x < 0` (`-121 < 0`) is `true`.
* Immediately returns `false` without allocating memory for string conversion.

**Test Case 3: x = 10 (Invalid Palindrome)**

* **Initial Checks & Conversion:** 
* `x < 0` is `false`.
* `res = ['1', '0']`
* `left = 0`, `right = 1`

* **Loop Iterations:** 
* **Iteration 1:** `res[0]` (`'1'`) `!=` `res[1]` (`'0'`) $\rightarrow$ Mismatch! ❌
* Triggers early exit: `return false`.
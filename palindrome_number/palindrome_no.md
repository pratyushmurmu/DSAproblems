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

**Flaw A: Comparing Arrays in Java (==)**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
In Java, comparing arrays using `==` checks whether they point to the same memory location, not whether their contents are equal. Even if you created a copy array, `array1` == `array2` would return `false`.

**Flaw B: Reversing In-Place Loses the Original Reference**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
In your loop, you are swapping characters directly inside `res`. If you reverse `res` in-place, by the time the loop finishes, you no longer have the original string array to compare against—you've mutated the original array!

**Flaw C: You Don't Actually Need to Reverse the Array!**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Since you are using two pointers (left moving right, right moving left), you just need to compare the characters at those pointers. If at any point `res[left]` != `res[right]`, it is `not a palindrome`. If left and right meet without any mismatch, it is a `palindrome`.

## Correct answer: ()
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
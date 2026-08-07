# Longest Substring Without Repeating Characters
Given a string `s`, find the length of the **longest substring** without duplicate characters.

**Example 1:**

- **Input:** s = "abcabcbb"
- **Output:** 3
- **Explanation:** The answer is `"abc"`, with the length of 3. Note that `"bca"` and `"cab"` are also correct answers.

**Example 2:**

- **Input:** s = "bbbbb"
- **Output:** 1
- **Explanation:** The answer is `"b"`, with the length of 1.

**Example 3:**

- **Input:** s = "pwwkew"
- **Output:** 3
- **Explanation:** The answer is `"wke"`, with the length of 3.
Notice that the answer must be a substring, `"pwke"` is a **subsequence** and not a **substring**.

**Constraints:**

- `0 <= s.length <= 10^5`
- `s` consists of English letters, digits, symbols and spaces.

### My Answer:
```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length=0;
        char[] str= s.toCharArray();
        Set<Character> res= new HashSet<>();

        for(int i=0; i<str.length; i++){
            if(str[i] != str[i+1]){
                length.add(str);
            }else{
                length++;
            }
        }
        return length;
    }
}
```
### My Mistakes:
1. **Compiler Errors ❌**

- **Primitive Dereferencing:**

 `length` is declared as an `int` (`int length = 0;`), but `int` is a primitive type in Java. It does not have methods, so calling `length.add(str)` causes `error: int cannot be dereferenced`. You likely meant to call `res.add(str[i])`.

- **`StringIndexOutOfBoundsException` / `ArrayIndexOutOfBoundsException`:**

 In `if (str[i] != str[i+1])`, when `i` reaches `str.length - 1`, accessing `str[i+1]` tries to access an index past the end of the array.

 2. **Why the Logic Fails 🧠**

- **Adjacent Comparisons Only (str[i] != str[i+1]):** Checking only adjacent characters finds repeating adjacent letters (like `"aa"`), but it completely misses duplicates separated by other characters (e.g., in "abcda", the 'a' at index 0 and 'a' at index 4 are not adjacent, but they are duplicates!).

- **Substrings vs. Subsequences:** A **substring** must be contiguous (unbroken). Checking individual adjacent elements or just inserting non-adjacent characters into a `HashSet` breaks contiguity.

- **Unused `HashSet`:** You initialized `Set<Character> res = new HashSet<>()`, but never added elements to it or queried it to shrink your window when duplicates appeared.

### Corrected Answer(Variable-Length Sliding Window Approach):

```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l =0;
        int maxLength=0;
        
        Set<Character> res= new HashSet<>();

        for(int r=0; r < s.length(); r++){
            while(res.contains(s.charAt(r))){
                res.remove(s.charAt(l));
                l++;
            }
            res.add(s.charAt(r));

            maxLength = Math.max(maxLength,r-l+1);
        }
        return maxLength;
    }
}
```

To solve this, use two pointers (left and right) to represent a sliding window, along with a HashSet to store unique characters currently inside that window.

**How it works:**

- Move `right` from `0` to `n - 1` to expand the window.

- If `str[right]` is **already in the set**, keep removing `str[left]` from the set and incrementing left++ until the duplicate is gone.

- Add `str[right]` to the set.

- Update `maxLength = Math.max(maxLength, right - left + 1)`

#### Step-by-Step Dry Run (`s = "abcabcbb"`)

- `right = 0` (`'a'`): Not in set. `seen = {'a'}`.
 `maxLength = max(0, 0 - 0 + 1) = 1`.

- `right = 1` (`'b'`): Not in set. `seen = {'a', 'b'}`. `maxLength = max(1, 1 - 0 + 1) = 2`.

- `right = 2` (`'c'`): Not in set. `seen = {'a', 'b', 'c'}`. `maxLength = max(2, 2 - 0 + 1) = 3`.

- `right = 3` (`'a'`): `'a'` is in `seen!`

- `while` loop runs: removes `s[left]` (`'a'`), `left` becomes `1`.

- Add `'a'`. `seen = {'b', 'c', 'a'}`. `maxLength = max(3, 3 - 1 + 1) = 3`.

**Complexity Analysis**

**Time Complexity:** **$\mathcal{O}(n)$** — Every character is visited by `right` once and removed by `left` at most once (at most **$2n$** total operations).

**Space Complexity:** **$\mathcal{O}(\min(n, m))$** — Where **$m$** is the size of the character set (e.g., at most **$26$** for lowercase English or **$128$** for **ASCII** characters stored in the `HashSet`).
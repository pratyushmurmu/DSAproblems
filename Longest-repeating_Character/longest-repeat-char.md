# Longest Repeating Character Replacement

You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

Return *the length of the longest substring containing the same letter you can get after performing the above operations*.

**Example 1:**

- **Input:** s = "ABAB", k = 2
- **Output:** 4
- **Explanation:** Replace the two 'A's with two 'B's or vice versa.
Example 2:

- **Input:** s = "AABABBA", k = 1
- **Output:** 4
- **Explanation:** Replace the one 'A' in the middle with 'B' and form `"AABBBBA"`.
The substring `"BBBB"` has the longest repeating letters, which is `4`. There may exists other ways to achieve this answer too.

- **Constraints:**

- `1 <= s.length <= 10^5`

- `s consists of only uppercase English letters.`

- `0 <= k <= s.length`

### My Answer:
```
class Solution {
    public int characterReplacement(String s, int k) {
        int res=0;
        Set<Character>count= new HashSet<>();
        for(int i=0; i< s.length; i++){
            
        }
    }
}
```

### Corrected Answer(Brute Force Approach) (Time Limit Exceeded):
```
class Solution {
    public int characterReplacement(String s, int k) {
        int res=0;

        for(int i=0; i < s.length(); i++){
            HashMap<Character, Integer>count= new HashMap<>();
            int max=0;
            for(int j=i; j < s.length(); j++){
                count.put(s.charAt(j),count.getOrDefault(s.charAt(j),0)+1);
                max = Math.max(max, count.get(s.charAt(j)));
                if((j-i+1)-max <= k){
                    res = Math.max(res, j-i+1);
                }
            }
        }
        return res;
    }
}
```
#### Explanation:
```
int res = 0;
```
- **Line 3:** Initializes `res` (result) to `0`. This variable will keep track of the maximum length of a valid substring found across all checks.

```
for (int i = 0; i < s.length(); i++) {
```
- **Line 4:** Outer loop: Sets the starting index `i` of the substring. It iterates through every possible starting character in the string.

```
HashMap<Character, Integer> count = new HashMap<>();
            int maxf = 0;
```
- **Lines 5–6:** Resets the tracking structures for each new starting position `i`:

- `count`: A map to store the frequency of each character in the current substring starting at `i`.

- `maxf`: Tracks the frequency of the most frequent character seen *so far* in the substring starting at `i`.

```
for (int j = i; j < s.length(); j++) {
```
- **Line 7:** Inner loop: Sets the ending index `j` of the substring, starting at `i` and expanding rightward to `s.length() - 1`. The pair `(i, j)` defines the current substring `s[i...j]`.

```
count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);
```

- **Line 8:**  Fetches the character at position `j` and increments its count in the `HashMap`. If it hasn't been seen yet in this window, `getOrDefault` returns `0`, setting its initial count to `1`.

```
maxf = Math.max(maxf, count.get(s.charAt(j)));
```
- **Line 9:** Updates `maxf`. It compares the current `maxf` with the updated count of `s.charAt(j)` to ensure `maxf` holds the highest frequency of any single character in `s[i...j]`.

```
if ((j - i + 1) - maxf <= k) {
```
- **Line 10:** The validity check:
- `(j - i + 1)` is the current window length.
- `maxf` is the count of the dominant character.
- `(j - i + 1) - maxf` calculates how many *other* characters need to be replaced to make the entire substring uniform.
- If the needed replacements are $\le k$, this substring is valid!

````
res = Math.max(res, j - i + 1);
                }
            }
        }
````
- **Lines 11–14:** If valid, updates `res` with the maximum length found so far `(Math.max(res, j - i + 1))`. The inner loop continues expanding `j` to test longer substrings from index `i`.

```
return res;
    }
}
```
- **Lines 15–17:** Returns the overall maximum length found after checking all possible pairs of `(i, j)`.

**Time Complexity:** $\mathcal{O}(n^2)$ — The outer loop runs $n$ times and the inner loop runs up to $n$ times, generating $\approx \frac{n^2}{2}$ total substrings.

**Space Complexity:** $\mathcal{O}(m)$ — Where $m$ is the number of unique characters in `s` stored in the `HashMap` (at most $26$ for uppercase English letters, effectively $\mathcal{O}(1)$).

The core condition for a valid substring is:$$\text{Window Length} - \text{Max Frequency} \le k$$

### Corrected Answer(Sliding Window):
```
public class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        
        // 1. Identify all unique characters in the input string
        HashSet<Character> charSet = new HashSet<>();
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        // 2. Iterate through each unique character, treating it as the "target" repeating character
        for (char c : charSet) {
            int count = 0; // Tracks occurrences of character 'c' inside the current window
            int l = 0;     // Left pointer for the sliding window
            
            // 3. Expand the sliding window by moving the right pointer 'r'
            for (int r = 0; r < s.length(); r++) {
                // If current character matches target 'c', increment its frequency
                if (s.charAt(r) == c) {
                    count++;
                }

                // 4. Validity Check: 
                // Window Size = (r - l + 1)
                // Non-matching characters to replace = (r - l + 1) - count
                // If replacements needed > k, shrink window from the left
                while ((r - l + 1) - count > k) {
                    if (s.charAt(l) == c) {
                        count--; // Decrease target count if the left character leaving was 'c'
                    }
                    l++; // Move left pointer forward
                }

                // 5. Update maximum valid window length found so far
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
}
```
**How the Logic Works**

Rather than dynamically tracking all character frequencies at once, this approach breaks the problem into sub-problems:

- For each unique character `c` in `charSet`, find the longest valid substring composed mostly of `c` (allowing up to `k` non-`c`` characters to be converted to `c`).

- Run a standard 2-pointer sliding window where `r` expands the window rightward and l shrinks it whenever `(window size - count of c) > k`.

- Return the maximum window size observed across all unique character passes.
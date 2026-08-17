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

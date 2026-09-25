# Longest Substring Without Repeating Characters

## My Approach(Brute Force):
1.
```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int count=0;
        char[] str = s.toCharArray();

        for(int i = 0; i < str.length; i++){
            HashMap present = new HashMap<>();
            for(int j =i + 1; j < str.length; j++){
                if(present.contains(str[j])){
                    break;
                }
                present.add(str[j]);
                count = Math.max(count,j - i + 1);
            }
        }
        return count;
    }
}
```
2.
```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int count=0;
        char[] str = s.toCharArray();

        for(int i = 0; i < str.length; i++){
            HashMap<Character, Integer> present = new HashMap<>();
            for(int j =i + 1; j < str.length; j++){
                if(present.containsKey(str[j])){
                    break;
                }
                present.put(str[j],1);
                count = Math.max(count,j - i + 1);
            }
        }
        return count;
    }
}
```

## Mistakes:
1.
The errors occur because you are calling `Set` methods (`contains` and `add`) on a `HashMap` object.

HashMap stores key-value pairs and uses methods like `.containsKey()`, `.get()`, and `.put()`. `HashSet`, on the other hand, stores unique single elements and uses `.contains()` and `.add()`.

Besides the method errors, there are **two logical bugs** in your inner loop:

- **Missing Starting Character:** Your inner loop starts at `j = i + 1`, which skips adding the character at `str[i]` to `present`.

- **Inner Loop Indexing:** `j` should start at `i` so single-character substrings are tracked correctly.


2.
- **Inner Loop Indexing:** `j` should start at `i` so single-character substrings are tracked correctly.

## Correct Approach (Brute Force Approach in HashMap):
```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int count=0;
        char[] str = s.toCharArray();

        for(int i = 0; i < str.length; i++){
            HashMap<Character, Integer> present = new HashMap<>();
            for(int j = i; j < str.length; j++){
                if(present.containsKey(str[j])){
                    break;
                }
                present.put(str[j],1);
                count = Math.max(count,j - i + 1);
            }
        }
        return count;
    }
}
```
The `1` is used simply as a dummy value because Java's `HashMap` forces you to provide both a **key** and a **value** when inserting data:

```
Java
map.put(key, value);
```

**Why we do this here**

In this specific brute-force algorithm, **we only care about checking whether a character exists in our current window (`present.containsKey(str[j])`)**. We don't actually need to store or read any associated numeric value.

- The character (`str[j]`) is the actual piece of information we want to track (the key).

- The integer (`1`) serves no computational purpose—it's just a placeholder to satisfy Java's `put(K, V)` method signature. You could put `0`, `999`, or `true` (if it were `HashMap<Character, Boolean>`) and the code would work identically.
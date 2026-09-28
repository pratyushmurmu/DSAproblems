# 4065. Rearrange Array by Removing Distinct Values
You are given an integer array `nums`.

You start with an empty array `ans`. Repeat the following operation until `nums` is **empty**:

- Identify **all distinct** values currently present in `nums`.
- Remove **one** occurrence of every **distinct** value currently in `nums`, and append those values to `ans` in **ascending** order.

Return the array `ans`.

![Screenshot](./images/Screenshot 2026-09-28 230858.png)

## Sumbitted Answer:
```
import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Collect characters inside the current innermost parentheses
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop()); // Popping inherently reverses them
                }
                
                // Remove the matching '('
                if (!stack.isEmpty()) {
                    stack.pop();
                }

                // Push the reversed characters back onto the stack
                for (int i = 0; i < sb.length(); i++) {
                    stack.push(sb.charAt(i));
                }
            } else {
                // Push characters and '(' onto the stack
                stack.push(ch);
            }
        }

        // Build final result from stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // Reverse result since stack pops elements in reverse order
        return result.reverse().toString();
    }
}
```
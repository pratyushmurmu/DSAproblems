# Valid Parenthesis
Given a string s containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Example 1:** Input: s = "( )" \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               Output: `true`

**Example 2:** Input: s = "( )[ ]{ }" \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               Output: `true`
            
**Example 3:** Input: s = "( ]" \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               Output: `false`

**Example 4:** Input: s = "( [ ] )" \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               Output: `true`

**Example 5:** Input: s = "( [ ) ]" \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               Output: `false`

### First Approach: (Wrong Answer)

```
class Solution {
    public boolean isValid(String s) {
        if(s.contains("()") || s.contains("{}") || s.contains("[]")){
            return true;
        }else{
            return false;
        }
    }
}
```
It passes all the test cases no doubt but when the input is something like `"(){}{"` , it fails. The expected output is `false` but it gives `true`.

In s = `"(){}{"`:

The substring `"()"` is present at the very beginning.

Because s.contains`("()")` evaluates to true, your code immediately returns true.

It completely ignores the trailing `{"`, which is an unclosed opening bracket making the overall string invalid!

`s.contains()` only asks "Is there any valid pair sitting inside this string?", whereas the problem demands "Is the entire string completely and correctly balanced?"

## Stack Approach: (The Correct approach)

```
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack= new Stack<>();

        for(char c: s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '{'){
                stack.push('}');
            }else if(c == '['){
                stack.push(']');
            }else if(stack.isEmpty() || stack.pop() !=c){
                return false;
            }
        }
        return stack.isEmpty();
    }
}
```
To ensure every opened bracket is closed in the exact reverse order of opening `(Last-In, First-Out)`, we use a Stack.

Why this passes s = `"(){}{"`:

* Process `'('` $\rightarrow$ Push `')'` to stack. Stack: `[')']`
* Process `')'` $\rightarrow$ Pop `')'` (matches current `')')`. Stack: `[]`
* Process `'{'` $\rightarrow$ Push `'}'` to stack. Stack: `['}']`
* Process '}' $\rightarrow$ Pop `'}'` (matches current `'}')`. Stack: `[]`
* Process `'{'` $\rightarrow$ Push '}' to stack. Stack: `['}']`
* Loop ends.
* `stack.isEmpty()` checks if anything is left over. Because `'}'` is still in the stack waiting to be closed, `stack.isEmpty()` returns `false`, which correctly flags the input as invalid!


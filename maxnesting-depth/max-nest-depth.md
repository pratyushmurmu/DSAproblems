# 1614. Maximum Nesting Depth of the Parentheses

Given a **valid parentheses string** `s`, return the **nesting depth** of `s`. The nesting depth is the **maximum** number of nested parentheses.

 # Submitted Approach:
 ```
class Solution {
    public int maxDepth(String s) {
      Stack<Character> stack = new Stack<>();
      int maxDepth= 0;

      for(char bracket : s.toCharArray()){
        if(bracket =='('){
            stack.push(bracket);
            maxDepth = Math.max(maxDepth,stack.size());

        }else if(bracket ==')'){
            stack.pop();
        }
      }
      return maxDepth;
    }
}
 ```
 
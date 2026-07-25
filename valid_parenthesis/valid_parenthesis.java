//{package valid_parenthesis;

import java.util.Scanner;
import java.util.Stack;

public class valid_parenthesis {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System .out.println("Enter a string to check for valid parentheses: ");
        String s = input.nextLine(); // Read the user's input string
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;

        for(char c: s.toCharArray()){
            if(c == '('){
                stack.push(')');
            } else if(c == '{'){
                stack.push('}');
            } else if(c == '['){
                stack.push(']');
            } else if(stack.isEmpty() || stack.pop() != c){
                isValid= false;
                break;
            }
        }
        if(isValid && stack.isEmpty()){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}

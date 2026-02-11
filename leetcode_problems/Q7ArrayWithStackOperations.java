import java.util.ArrayDeque;
import java.util.Deque;

public class Q8EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(String token : tokens) {
            if (isOperator(token)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(evaluate(num1, num2, token));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.peek();
    }

    private int evaluate(int num1, int num2, String operand) {
        switch (operand) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }

        return 0;
            
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static void main(String[] args) {
        Q8EvaluateReversePolishNotation obj = new Q8EvaluateReversePolishNotation();
        String[] tokens = {"4","13","5","/","+"}; // Output: 4 + (13 / 5) = 4 + 2 = 6
        System.out.println(obj.evalRPN(tokens));

        tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}; // Output: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5) = ((10 * (6 / (12 * -11))) + 17) + 5) = ((10 * (6 / -132)) + 17) + 5) = ((10 * 0) + 17) + 5) = (0 + 17) + 5) = 22
        System.out.println(obj.evalRPN(tokens));

    }
}

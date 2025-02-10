package org.example;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int rule(char ops) {
        if (ops == '+' || ops == '-') return 1;
        else if (ops == '*' || ops == '/') return 2;
        return 0;
    }

    public static int calculationrule(int b, int a, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Number cannot be divided by 0");
                }
                return a / b;
            default: return 0;
        }
    }

    public static int calculation(String exp) {
        Stack<Integer> ans = new Stack<>();
        Stack<Character> operation = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                operation.push(c);
            } else if (c == ' ') {
                continue;
            } else if (c == ')') {
                while (!operation.isEmpty() && operation.peek() != '(') {
                    ans.push(calculationrule(ans.pop(), ans.pop(), operation.pop()));
                }
                operation.pop();
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    num = num * 10 + (exp.charAt(i) - '0');
                    i++;
                }
                i--;
                ans.push(num);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operation.isEmpty() && rule(c) <= rule(operation.peek())) {
                    ans.push(calculationrule(ans.pop(), ans.pop(), operation.pop()));
                }
                operation   .push(c);
            }
        }

        while (!operation.isEmpty()) {
            ans.push(calculationrule(ans.pop(), ans.pop(), operation.pop()));
        }

        return ans.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();
        try {
            System.out.println(calculation(exp));
        } catch (Exception e) {
            System.out.println("invalid Argument " );
        }
    }
}
package org.spbstu.kovshov;

import org.spbstu.kovshov.MyExeptions.EmptyInputException;
import org.spbstu.kovshov.MyExeptions.ExtraOperatorException;
import org.spbstu.kovshov.MyExeptions.InvalidBraketsException;
import org.spbstu.kovshov.MyExeptions.UnknownSymbolException;
import org.spbstu.kovshov.Operations.*;

import java.util.Stack;

class Main {

    private static double xParam;
    private static double yParam;
    private static double zParam;

    static private boolean IsSpace(char c) {
        return c == ' ';
    }

    static private boolean IsOperator(char c) {
        return ("+-/*^()".indexOf(c) != -1);
    }

    static private String deleteSpace(String input) throws EmptyInputException {
        String s = input.replaceAll("\\s+", "");
        if (s.isEmpty()) {
            throw new EmptyInputException();
        }
        return s;
    }

    static private boolean correctBrackets(String input) {
        int value = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                value++;
            if (input.charAt(i) == ')')
                value--;
            if (value < 0)
                return false;
        }
        return value == 0;
    }

    static private String TurningMinusIntoZero(String input) throws UnknownSymbolException {
        //System.out.println("Before turning: " + input);
        input = " " + input + " ";
        boolean operator = true;
        int i = 0;
        while (i < input.length()) {
            if (" 0123456789.+-/*^()xyz".indexOf(input.charAt(i)) == -1) {
                throw new UnknownSymbolException(input.charAt(i));
            } else if (Character.isDigit(input.charAt(i))) {
                operator = false;
            } else if (input.charAt(i) == 'x' || input.charAt(i) == 'y' || input.charAt(i) == 'z') {
                operator = false;
            } else if ("+/*^()".indexOf(input.charAt(i)) != -1) {
                operator = true;
            } else if (input.charAt(i) == '-') {
                if (input.charAt(i + 1) == 'x' || input.charAt(i + 1) == 'y' || input.charAt(i + 1) == 'z') {
                    operator = false;
                } else if (input.charAt(i + 1) == '-') {
                    String help1 = input.substring(0, i);
                    String help2 = input.substring(i + 2, input.length());
                    input = help1 + "+" + help2;
                    i++;
                } else if (input.charAt(i - 1) == ')' && input.charAt(i + 1) == '(') {
                    operator = false;
                } else if (operator) {
                    String help1 = input.substring(0, i);
                    int j = i + 1;
                    int isOpen = 0;
                    if (input.charAt(j) == '(') {
                        isOpen++;
                        j++;
                    }
                    if (j >= input.length()) {
                        break;
                    }
                    while (isOpen > 0 || Character.isDigit(input.charAt(j))) {
                        if (input.charAt(j) == '(') {
                            isOpen++;
                        } else if (input.charAt(j) == ')') {
                            isOpen--;
                        }
                        j++;
                        if (j >= input.length() - 1) {
                            break;
                        }
                    }
                    String help2 = input.substring(i, j);
                    String help3 = input.substring(j, input.length());
                    input = help1 + "(0" + help2 + ")" + help3;
                    //System.out.println("Turning: " + input);
                    operator = false;
                } else {
                    operator = true;
                }
            }
            i++;
        }
        //System.out.println("After turning: " + input + '\n');
        return input;
    }

    static private String GetExpression(String input) {
        //System.out.println("Input1: " + input + '\n');
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            //System.out.println("i=" + i + " Char: " + input.charAt(i));
            if (IsSpace(input.charAt(i)))
                continue;
            if (input.charAt(i) == 'x') {
                output.append("x ");
            } else if (input.charAt(i) == 'y') {
                output.append("y ");
            } else if (input.charAt(i) == 'z') {
                output.append("z ");
            } else if (Character.isDigit(input.charAt(i))) {
                while (!IsSpace(input.charAt(i)) && !IsOperator(input.charAt(i))) {
                    output.append(input.charAt(i));
                    i++;
                    //System.out.println("In while: " + output);
                    if (i == input.length()) break;
                }
                output.append(" ");
                //System.out.println("After while: " + output);
                i--;
            }
            if (IsOperator(input.charAt(i))) {
                if (input.charAt(i) == '(') {
                    //System.out.println("Search: (");
                    stack.push(input.charAt(i));
                    //System.out.println("В Stack: (");
                } else if (input.charAt(i) == ')') {
                    //System.out.println("Search: )");
                    char s = stack.pop();
                    //System.out.println("Из Stack: " + s);
                    while (s != '(') {
                        output.append(s).append(" ");
                        s = stack.pop();
                        //System.out.println("Из Stack: " + s);
                    }
                    //System.out.println("After (): " + output);
                } else {
                    //System.out.println("Operator");
                    if (stack.size() > 0)
                        if (GetPriority(input.charAt(i)) <= GetPriority(stack.peek())) {
                            //System.out.println("Add operation from stack to output");
                            output.append(stack.pop()).append(" ");
                        }
                    stack.push(input.charAt(i));

                }
            }
            //System.out.println("Stack: " + stack.toString());
            //System.out.println("Output: " + output + '\n');
        }
        while (stack.size() > 0)
            output.append(stack.pop()).append(" ");
        //System.out.println("End Input1 \n");
        return output.toString();
    }

    static private double Counting(String input) throws Exception {
        //System.out.println("Input2: " + input + '\n');
        double result = 0;
        Stack<Double> temp = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            //System.out.println("i=" + i + " Char: " + input.charAt(i));
            if (input.charAt(i) == 'x') {
                temp.push(xParam);
            } else if (input.charAt(i) == 'y') {
                temp.push(yParam);
            } else if (input.charAt(i) == 'z') {
                temp.push(zParam);
            } else if (Character.isDigit(input.charAt(i))) {
                StringBuilder builder = new StringBuilder();
                while (!IsSpace(input.charAt(i)) && !IsOperator(input.charAt(i))) {
                    builder.append(input.charAt(i));
                    i++;
                    if (i == input.length()) break;
                }
                //System.out.println("В Stack: " + builder);
                temp.push(Double.parseDouble(builder.toString()));
                i--;
            } else if (IsOperator(input.charAt(i))) {
                if (temp.size() == 0) {
                    throw new ExtraOperatorException();
                }
                double a = temp.pop();
                if (temp.size() == 0) {
                    throw new ExtraOperatorException();
                }
                double b = temp.pop();
                //System.out.println("Из Stack: " + a + " " + b);
                switch (input.charAt(i)) {
                    case '+':
                        result = new Add(b, a).operation();
                        break;
                    case '-':
                        result = new Subtract(b, a).operation();
                        break;
                    case '*':
                        result = new Multiply(b, a).operation();
                        break;
                    case '/':
                        result = new Divide(b, a).operation();
                        break;
                    case '^':
                        result= new Pow(b, a).operation();
                        break;
                }
                //System.out.println("В Stack: " + result);
                temp.push(result);

            }
            //System.out.println("Stack: " + temp.toString());
            //System.out.println("Result: " + result + '\n');
        }
        //System.out.println("End Input2 \n");
        if (temp.size() == 0) {
            throw new ExtraOperatorException();
        }
        return temp.peek();
    }

    static double Calculate(String input) throws Exception {
        return body(input);
    }

    static double Calculate(String input, double x) throws Exception {
        xParam = x;
        return body(input);
    }

    static double Calculate(String input, double x, double y) throws Exception {
        xParam = x;
        yParam = y;
        return body(input);
    }

    static double Calculate(String input, double x, double y, double z) throws Exception {
        xParam = x;
        yParam = y;
        zParam = z;
        return body(input);
    }

    private static double body(String input) throws Exception {
        input = deleteSpace(input);
        if (!correctBrackets(input))
            throw new InvalidBraketsException();
        String goodInput = TurningMinusIntoZero(input);
        String output = GetExpression(goodInput);
        return Counting(output);
    }

    static private byte GetPriority(char s) {
        switch (s) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
                return 2;
            case '-':
                return 3;
            case '*':
                return 4;
            case '/':
                return 4;
            case '^':
                return 5;
            default:
                return 6;
        }
    }
}
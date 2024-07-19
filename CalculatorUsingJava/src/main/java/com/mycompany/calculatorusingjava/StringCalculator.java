/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculatorusingjava;

/**
 *
 * @author Hasheem
 */
import java.util.Stack;
import java.util.ArrayList;
public class StringCalculator {

    public static ArrayList<String> ConvertingExpressionIntoArray(String infix)
    {
        ArrayList<String> list = new ArrayList<String>();
        
        int i = 0;
        while(i < infix.length())
        {
            String str = "";
            while((infix.charAt(i) == '1' ||
             infix.charAt(i) == '2' ||
              infix.charAt(i) == '3' ||
               infix.charAt(i) == '4' ||
                infix.charAt(i) == '5' ||
                 infix.charAt(i) == '6' ||
                  infix.charAt(i) == '7' ||
                   infix.charAt(i) == '8' ||
                    infix.charAt(i) == '9' ||
                     infix.charAt(i) == '0'))
            {
                str += infix.charAt(i);
                ++i;
                if(i == infix.length())
                {
                    --i;
                    break;
                }
            }


            if(!str.equals(""))
            {
                list.add(str);
            }

            if(infix.charAt(i) == '+')
            {
                list.add("+");
                ++i;
            }
            else if(infix.charAt(i) == '-')
            {
                list.add("-");
                ++i;
            }
            else if(infix.charAt(i) == '*')
            {
                list.add("*");
                ++i;
            }
            else if(infix.charAt(i) == '/')
            {
                list.add("/");
                ++i;
            }
            else if(infix.charAt(i) == '(')
            {
                list.add("(");
                ++i;
            }
            else if(infix.charAt(i) == ')')
            {
                list.add(")");
                ++i;
            }
            else if(infix.charAt(i) == ' ')
            {
                ++i;
            }
            else
            {
                i++;
            }
        }

        return list;

    }

    public static ArrayList<String> infixToPostfix(String Expression) {
        int size = 0;
        
        ArrayList<String> infix = ConvertingExpressionIntoArray(Expression);
        ArrayList<String> postfix = new ArrayList<String>();

        Stack<String> st = new Stack<String>();

        for (int i = 0; i < infix.size(); i++) 
        {
            if (infix.get(i) == "(") 
            {
                st.push(infix.get(i));
            } 
            else if (infix.get(i) == "*" || infix.get(i) == "/")
            {
                st.push(infix.get(i));
            } 
            else if (infix.get(i) == "+" || infix.get(i) == "-") 
            {
                if (st.empty()) 
                {
                    st.push(infix.get(i));
                } 
                else if (st.peek() == "(") 
                {
                    st.push(infix.get(i));
                } 
                else 
                {
                    while (!st.empty() && st.peek() != "(") {
                        postfix.add(size, st.pop()) ;
                        size++;
                    }
                    st.push(infix.get(i));
                }
            } 
            else if (infix.get(i) == ")") 
            {
                while (!st.empty() && st.peek() != "(") 
                {
                    postfix.add(size, st.pop()) ;
                    size++;
                }
                st.pop();
            } 
            else 
            {
                postfix.add(size, infix.get(i));
                size++;
            }
        }

        while (!st.empty()) {
            if (st.peek() != "(" && st.peek() != ")") {
                postfix.add(size, st.pop()) ;
                size++;
            }
        }

        return postfix;
    }

    public static String StringCalulate(String Expression)
    {
        ArrayList<String> list = infixToPostfix(Expression);
        boolean valid = true;
        while(valid)
        {
            int index = 0;
            valid = false;
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i) == "+" || list.get(i) == "-" || list.get(i) == "*" || list.get(i) == "/")
                {
                    index = i;
                    valid = true;
                    break;
                }
            }

            if(valid == false)
                break;

            double operand1 = Double.parseDouble(list.get(index - 2));
            double  operand2 = Double.parseDouble(list.get(index - 1));
            double  result = 0;
            String oper = list.get(index);

            switch(oper)
            {
                case "+": result = operand1 + operand2;break;
                case "-": result = operand1 - operand2;break;
                case "*": result = operand1 * operand2;break;
                case "/": result = operand1 / operand2;break;
                default: System.out.println("Invalid operator.");
            }

            list.set(index,String.valueOf(result));
            list.remove(index - 1);
            list.remove(index - 2);
            

        }

        return list.get(0);
    }
}

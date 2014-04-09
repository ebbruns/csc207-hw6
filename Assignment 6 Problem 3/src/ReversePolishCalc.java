import java.io.PrintWriter;


public class ReversePolishCalc
{

  public static void RPN()
    throws Exception
  {
    PrintWriter pen = new PrintWriter (System.out, true);
    pen.println("WELCOME TO THE SUPER RPN CALCULATRON - ENTER INPUT, PUNY MORTAL");

    java.io.BufferedReader eyes;
    java.io.InputStreamReader istream;
    istream = new java.io.InputStreamReader(System.in);
    eyes = new java.io.BufferedReader(istream);
    
    

    boolean printTop = false;
    boolean printStack = false;
    boolean clearStack = false;

    double val1 = 0;
    double val2 = 0;

    char testChar;

    String input = null;
    String[] equation = null;

    ArrayBasedStack<Character> opStack = new ArrayBasedStack<Character>(10000);
    ArrayBasedStack<Double> numStack = new ArrayBasedStack<Double>(10000);

    while (true)
      {
        input = eyes.readLine(); // Idea from Evan's Assignment 3 Calculator
        input.trim();
        equation = input.split(" ");

        for (int i = 0; i < equation.length; i++)
          {
            try
              {
                testChar = equation[i].charAt(0);
              }
            catch (StringIndexOutOfBoundsException e)
              {
                System.err.println("Please don't enter blank lines");
                break;
              }

            if (testChar == 'c' || testChar == 'C')
              {
                clearStack = true;
              }// if
            else if (testChar == 'p' || testChar == 'P')
              {
                printTop = true;
              }// else if
            else if (testChar == 's' || testChar == 'S')
              {
                printStack = true;
              }// else if
            else if (Character.isDigit(testChar)) // if next value is a
                                                  // number
              {
                numStack.put(Double.parseDouble(equation[i]));
              }// else if
            else if ((testChar == '+') || (testChar == '-')
                     || (testChar == '*') || (testChar == '/')
                     || (testChar == '%'))
              {
                opStack.put(testChar);
              }// else if
            else
              {
                System.err.println("Unsupported input! Please enter numbers, operations, s, p or c.");
                break;
              }// else

            while (!opStack.isEmpty() && numStack.size >= 2)
              {
                val1 = numStack.pop();
                val2 = numStack.pop();
                char operation = opStack.pop();

                switch (operation)
                  {
                    case '+':
                      val1 += val2;
                      break;

                    case '-':
                      val1 -= val2;
                      break;

                    case '*':
                      val1 *= val2;
                      break;

                    case '/':
                      val1 /= val2;
                      break;

                    case '%':
                      val1 = val1 % val2;
                      break;

                    default:
                      throw new Exception(
                                          "That's not an operation, please enter only +, -, *, %, or / as operations.");
                  }
                numStack.push(val1);
              }
            if (clearStack || printTop || printStack)
              {
                if (clearStack)
                  {
                    numStack = new ArrayBasedStack<Double>(10000);
                  }

                if (printTop)
                  {
                    if (numStack.size > 0)
                      {
                        pen.println(numStack.peek());
                      }
                    else
                      {
                        System.err.println("[stack is empty]");
                      }
                  }

                if (printStack)
                  {                
                    numStack.stackOut();
                    
                   
                    if (numStack.size == 0)
                      {
                        System.err.println("[stack is empty]");
                      }
                  }
                printStack = false;
                printTop = false;
                clearStack = false;
              }
          }// for

      }// While (true) BIG BOY

  }// RPN(String input)

  public static void main(String[] args)
    throws Exception
  {
    RPN();
  }

}

// Code for getting input found on
// http://www.cs.grinnell.edu/~rebelsky/Courses/CSC207/2014S/readings/io.html

// Try/Catch exceptions knowledge gained from
// http://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html

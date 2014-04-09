import java.util.*;

public class RPNCalc
{
  public static void calcMe(String input)
    throws Exception
  {
    // VARIABLE DELARATIONS

    boolean expectSpace = false;
    boolean printTop = false;
    boolean printStack = false;
    boolean clearStack = false;
    char nextChar = ' ';

    ArrayBasedStack<Character> opStack =
        new ArrayBasedStack<Character>(input.length());
    ArrayBasedStack<Double> numStack =
        new ArrayBasedStack<Double>(input.length());

    int i = 0;
    while (i < input.length())
      {
        nextChar = input.charAt(i);

        if (expectSpace)
          if (nextChar != ' ')
            {
              throw new Exception(
                                  "Space expected after numbers, commands, and operators");
            }
          else
            {
              expectSpace = false;
            }

        else if (nextChar == 'c' || nextChar == 'C')
          {
            clearStack = true;
            expectSpace = true;
            i++;
          }
        else if (nextChar == 'p' || nextChar == 'P')
          {
            printTop = true;
            expectSpace = true;
            i++;
          }
        else if (nextChar == 's' || nextChar == 'S')
          {
            printStack = true;
            expectSpace = true;
            i++;
          }

        else if (48 <= nextChar && nextChar <= 57) // if next value is a number
          {
            String numString = null;

            while (48 <= nextChar && nextChar <= 57)
              {
                numString.concat(Character.toString(nextChar));
                i++;
                nextChar = input.charAt(i);
              }
            // Adds the Double to stack
            numStack.put(Double.parseDouble(numString));
            expectSpace = true;
          }

        else if ((nextChar == '+') || (nextChar == '-') || (nextChar == '*')
                 || (nextChar == '/'))
          {
            opStack.put(nextChar);
            i++;
          }

      }

  }

  // Should throw a invalid input error

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}

package taojava.assignment6.problem2;

public class CheckMatching<S>

{
  static boolean check(String str)
    throws Exception
  {
    ArrayBasedStack<Character> stack =
        new ArrayBasedStack<Character>(str.length());
    
    ArrayBasedStack<Integer> numStack =
        new ArrayBasedStack<Integer>(str.length());
    
    int charNum = 0;
    boolean openQuotes = false;
    
    System.out.println(str);

    for (int i = 0; i < str.length(); i++)
      {
        char ch = str.charAt(i);
        if (ch == '{' || ch == '[' || ch == '(' || ch == '<' || (ch == '\'' && openQuotes == false))
          {
            stack.put(ch);
            numStack.put(charNum);
            if (ch == '\'')
              {
              openQuotes = true;
              }
          }// if
        else if (ch == '}' || ch == ']' || ch == ')' || ch == '>' || (ch == '\'' && openQuotes == true))
          {
            if (ch == '\'')
              {
              openQuotes = false;
              }
            if (stack.isEmpty())
              {
                for (int j = 0; j<charNum; j++)
                  {
                    System.out.print(" ");
                  }
                System.out.println(ch + " <----UNMATCHED");
              }// if
            else
              {
                char stackTop = (char) stack.pop();
                int currentNum = numStack.pop();
                
                for (int j = 0; j<currentNum; j++)
                  {
                    System.out.print(" ");
                  }
                System.out.print(stackTop);
                for (int j = currentNum; j < charNum - 1; j++)
                  {
                    System.out.print("-");
                  }
                // The open bracket is 1 or 2 away from the close bracket in the
                // ASCII table.
                // so, this checks if the top of the stack is a matching open
                // bracket.
                if ((ch == '}' && stackTop != '{')
                    || (ch == ']' && stackTop != '[')
                    || (ch == ')' && stackTop != '(')
                    || (ch == '>' && stackTop != '<')
                    || (ch == '\'' && stackTop != '\''))
                  {  
                    System.out.println(ch + " <------UNMATCHED");
                  }// if
                else
                  {
                    System.out.println(ch);
                  }//else
              }// else
          }// elif
        charNum++;
      }// for
    
    while(!stack.isEmpty())
      {
        int currentNum = numStack.pop();
        for (int j = 0; j < currentNum; j++)
          {
            System.out.print(" ");
          }
        System.out.println(stack.pop() + " <------ UNMATCHED");
      }

    System.out.println(" ");
    return stack.isEmpty();
  }// check(String)

  public static void main(String[] args)
    throws Exception
  {
    check("{oh [boy] (I am having) (<so> much) fun matching 'symbols'}");
  }// main
}// CheckMatching<S>

//BASED ON CODE FROM THE LINEAR STRUCTURES LAB MADE BY EVAN BRUNS AND PHINEAS SCHLOSSBERG

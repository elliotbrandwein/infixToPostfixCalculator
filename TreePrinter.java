import java.util.Iterator;
import java.util.Scanner;

public class TreePrinter {

	public void evaluateAndPrintTree(BinaryTree<String> inputTree)
	{
		Iterator<String> itr = inputTree.iterator();
		LinkedStack<String> stack= new LinkedStack<String>();
		System.out.println();			// this is here for formating purposes
		StringBuffer postfixBuilder = new StringBuffer();
		while(itr.hasNext())
		{
			String temp= itr.next();
			postfixBuilder.append(temp+" ");
			int tempInt;
			try											
			{            // the try will check for an input by trying to convert the current head of queue into an int
				tempInt= Integer.parseInt(temp);
				if(tempInt>0)stack.push(temp);			// I check for positive numbers but they should be here anyway
			}
			catch(Exception e)							// the catch will deal with the variable and operators
			{			
				if(isVariable(temp)){
					System.out.println("what do you want the value of "+ temp + " to be?" +"\n");
					double varReplace= inputTaker();
					stack.push(String.valueOf(varReplace));
				}
				if(isOperand(temp))
				{
					double rightNumber=Double.parseDouble(stack.pop());
					double leftNumber=Double.parseDouble(stack.pop());
					stack.push(String.valueOf(arthimatic(leftNumber,rightNumber,temp)));
				}
					
			}
			
		}
		System.out.println(postfixBuilder); 
		System.out.println("your anwser is "+ stack.pop()); 
	}
	private double arthimatic(double left, double right, String operator){
		double newNumber=0;
		switch(operator)
		{
		case"+":
			newNumber=left+right;			// this will do the arithmatic for the left and right numbers for each the operator
			break;
		case"-":
			newNumber=left-right;
			break;
		case"*":
			newNumber=left*right;
			break;
		case"/":
			newNumber=left/right;
			break;
		}
		return newNumber;
	}
	
	private boolean isOperand(String temp) {
		switch(temp)
		{
			case "-": case "+":
			case " ": case "/":
			case "*":
				return true; 
			
			default: return false;
			
			// this doesnt use breaks because if we get to a case we exist the method
		}
	}

	private boolean isVariable(String temp) {
		switch(temp)
		{
			case "-": case "+":
			case " ": case "/":
			case "*":
				return false; 
			
			default: return true;
			
			// this doesnt use breaks because if we get to a case we exist the method
		}
	}

	private double inputTaker() {
		boolean check = true;
		double input = 0;
		Scanner in = new Scanner(System.in);             // this creates the object that I'll be using to get the user input
		while(check)
		{
			try
			{
				input= Double.parseDouble(in.nextLine());
				check=false;
			}
			catch(Exception e){
				System.out.println("that was not valid input");
			}
		}
		return input;
	}
	
}

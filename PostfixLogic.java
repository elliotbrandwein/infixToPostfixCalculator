
public class PostfixLogic {

	public boolean isOperand(char c)
	{
		if (c == '*' || c=='-' || c == '+' || c == '/' || c == '('|| c == ')'){
			return false;									// if the input was not an operator, we return true, it was an operand
		}
		else return true;
	}
	public boolean isNotParans( char c)
	{
		if (c == '*' || c == '-' || c == '+' || c == '/'){
			return true;									// this only compares to operators that are not parans, but a parans shouldnt get in here so thats fine
		}
		else return false;
	}
	public int operatorCompare(ArrayStack<Character> postfix, char input)
	{
		int outcome;
		if(postfix.isEmpty())outcome=0;													// if the stack is empty, then we are in case 0, which is handled by the if that called this 
		else{
			outcome=ComparePrecedence(postfix, input);									// this returns the outcome of  a operation comparison, which is down in the other compare method
		}
		return outcome;
	}
	private  int ComparePrecedence(ArrayStack<Character> postfix, char input)
	{
		char top=postfix.top();															// this does the comparing of the top and the input, and returns either case 1 or 2, 
		int topValue=0;																	// 2 means the top was higher or equal to the input
		int inputValue = 0;																// and 1 means the top was lower then the input
		if(top== '*'|| top=='/') topValue=2;
		if(top== '+'|| top=='-') topValue=1;
		if(input== '*'|| input=='/') inputValue=2;
		if(input== '+'|| input=='-') inputValue=1;
		if(topValue<inputValue) return 1;
		if(topValue>=inputValue) return 2;
		return 99;													// in case of an error 99 will be returned 
	}
	public boolean isOperator(char c){
		if (c == '*' || c=='-' || c == '+' || c == '/' || c == '('|| c == ')'){
			return true;									// if the input was an operator we return true
		}
		else return false;
	}
	
}


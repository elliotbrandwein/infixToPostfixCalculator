
public class PostFixer2 {
	ArrayStack<Character> postfix = new ArrayStack<Character>();
	PostFixer2() {}
	public LinkedQueue<Character> addAndPrint(String s){
		LinkedQueue<Character> postfixMaker = new LinkedQueue<Character>();	// the entire postfix will be put in the postfixmaker queue
		PostfixLogic methods = new PostfixLogic();		// this class contains all the logic for how to convert to postfix
		if(s==null) System.out.println(s);				// this will skip over blank lines in the text file
		else{											// the else will deal with postfixing expressions
			System.out.println(s);						// we print the original string and then work on printing the postfix version
			char input;									// this will be used to convert the string input to a char and is used for all my methods 
			boolean once=true;							// I will use this to make sure i dont double print a blank when there are more then one ( in a row
			int outcome;								// this will be used later for the 3 possible outcomes when comparing operator precedence 
			for(int i=0;i<s.length();i++){				// this loop will run the length of the input string, note that the method only runs on one string at a time. 
				outcome=99;								// i have to reset the outcome per loops, and I set it to 99 so that in case of an error I'll see 99 in the debugger
				input=s.charAt(i);
				final int Top_Was_Higher=2;
				final int Top_Was_Lower=1;
				final int Stack_Was_Empty=0;
				if(methods.isOperand(input)){				// if the input was an operand, we put it in the queue to be printed later
					postfixMaker.enqueue(input);
					once=true;
				}
				else if(s.charAt(i)=='('){					// if the input was a ( we push it onto the stack and put a space between it and the operand before it
					if(once)postfixMaker.enqueue(' ');
					postfix.push(input);
					once=false;								// this will prevent the multiple ( from printing extra blanks, and is reset when the input is no longer a (
				}
				else if(methods.isNotParans(input))		// the if input was an operator that isnt a ( or ) we go in here
                {
					once=true;
					outcome=methods.operatorCompare(postfix,input);
					if(outcome==Top_Was_Higher){									// if outcome was a 2, the top had a higher or equal value then the input, so we pop and compare until that's not true
						while(outcome==Top_Was_Higher){
							postfixMaker.enqueue(' ');
							postfixMaker.enqueue(postfix.pop());	
							outcome=methods.operatorCompare(postfix, input);
						}
					}
					if(outcome==Stack_Was_Empty || outcome==Top_Was_Lower){
						postfixMaker.enqueue(' ');
						postfix.push(input);	// outcome will be  1 if the top is lower then the input, which can happen naturally or after the loop above
					}
				}														// outcome will 0 zero if the stack is empty, which can happen naturally or after the loop above
				else if(input==')'){
					once=true;
			                 											// if its a right parans, we pop until we see a left parans
					while( postfix.isEmpty()!=true && postfix.top()!='('){					
						postfixMaker.enqueue(' ');                      // we will add all the poped operators into the queue
						postfixMaker.enqueue(postfix.pop());				
					}
					if( postfix.top()=='('){												// we then pop the the left parans at the end
						postfix.pop();
					}
				}	
			}
			while(postfix.isEmpty()!=true){
				postfixMaker.enqueue(' ');
				postfixMaker.enqueue(postfix.pop());					// after the loop we clear the stack and pop all the operators left over
			}
		}
		return postfixMaker;
	}	
}


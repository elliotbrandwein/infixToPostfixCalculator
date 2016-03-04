
public class TreeMaker {

	@SuppressWarnings("unchecked")
	public BinaryTree<String> makeTreeAndPrintQueue(LinkedQueue<Character> postfixQueue) {
		LinkedStack<BinaryTree> inputStack= new LinkedStack<BinaryTree>();
		char currentInput;
		boolean isOperator=true;
		PostfixLogic checkForOperator= new PostfixLogic();
		LinkedQueue<Character> operandBuilder = new LinkedQueue<Character>() ; // this will be used to keep track of what to add to each node when we switch from operand to operator
		while (postfixQueue.isEmpty()!=true)
		{
			currentInput=postfixQueue.dequeue();
			//we see if the input is an operator, part of an operand or a blank space 
			isOperator=checkForOperator.isOperator(currentInput);
			// the first if means we have part of an operand which we dont add the the tree yet, rather we build it to be added later
			if(isOperator!=true && currentInput!=' ')
			{			
			 operandBuilder.enqueue(currentInput);
			}
			// the second if means we are up to a blank, which means we out the operand before it into the tree (if it exists)
			if(currentInput==' ')
			{
				if(operandBuilder.isEmpty()!=true){
					BinaryTree<String> T = new BinaryTree<String>(stringMaker(operandBuilder));
					inputStack.push(T);
				}
			}
			// the third if means we have an operator, 
			if(isOperator)
			{
				String input= String.valueOf(currentInput);
				BinaryTree<String> T = new BinaryTree<String>(input);
				BinaryTree<String> rightChild = inputStack.pop();
				BinaryTree<String> leftChild =  inputStack.pop();
				T.attach(leftChild, rightChild);
				inputStack.push(T);
			}
			
		}
		if(inputStack.isEmpty()) return null;
		return inputStack.pop();
	}

	private String stringMaker(LinkedQueue<Character> operandBuilder) {
		StringBuffer t = new StringBuffer();
		while(operandBuilder.isEmpty()!=true){
			t.append(operandBuilder.dequeue());
		}
		String output=String.valueOf(t);
		return output;
	}

}

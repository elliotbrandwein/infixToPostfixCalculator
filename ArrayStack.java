
public class ArrayStack<AnyType> implements Stack<AnyType>
{
	public static final int DEFAULT_CAPACITY = 1024;
	AnyType[] stack;
	int topOfStack;
	public ArrayStack() { this(DEFAULT_CAPACITY); }
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity)
	{
		topOfStack = -1;
		stack = (AnyType[]) new Object[capacity];
	}
	public int size(){return stack.length;}
	public boolean isEmpty(){ 
		if(topOfStack != -1){
			return false;
		}
		else return true;
	}
	public void push(AnyType e) throws IllegalStateException{
			topOfStack++;
			stack[topOfStack]=e;
	}
	public AnyType top()throws IllegalStateException{
		return stack[topOfStack];
	}
	public AnyType pop()throws IllegalStateException{
		AnyType temp=stack[topOfStack];
		topOfStack--;
		return temp;
	}
}
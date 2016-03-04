import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		File file = new File("project3.txt");
	    String[] lines = new String[50];					//take in at most 50 lines
	    int lineCount=0;									//this will be used to iterate through the lines later
	    try {												
	        FileReader reader = new FileReader(file);
	        BufferedReader buffReader = new BufferedReader(reader);
	        int x = 0;
	        String s;
	        while((s = buffReader.readLine()) != null){
	            lines[x] = s;
	            x++;
	            lineCount++;
	        }
	    }
	    catch(IOException e){System.out.println("did u make sure the text file is up one directory from the src?");}  
	    
	    // note that THIS WILL PRINT WIERD IF THERE ARE WHITE SPACES IN THE INPUT( EX: A + B instead of A+B) 
	    PostFixer2 pF2 = new PostFixer2();		// changes the input (which should be infix) from text file to postfix
	    TreeMaker tM= new TreeMaker();			// this will take the queue of postfix ( from pF2) and put it in a tree
	    TreePrinter tP= new TreePrinter();		// this will take in the tree that is in postfix form and will print and evaluate it
	    
	    LinkedQueue<Character> postfixQueue = new LinkedQueue<Character>();
	    BinaryTree<String> postfixTree= new BinaryTree<String>();
	    for(int i=0;i<lineCount;i++){
	    	postfixQueue=pF2.addAndPrint(lines[i]);			  // takes in the input from txt file and outputs the queue with the postfix
	    	postfixTree=tM.makeTreeAndPrintQueue(postfixQueue);			// takes in the postfix queue and outputs the postfix tree
	    	tP.evaluateAndPrintTree(postfixTree);			// takes in the tree and will evaluate it.
	    	System.out.print("\n"+"\n");
	    }
	  
	}
	
}
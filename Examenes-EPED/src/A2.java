import eped.stack.StackDynamic;
import eped.stack.StackIF;


public class A2 {
	
	
	public A2(){
		
	}
	
	
	public StackIF<Integer> reverse(StackIF<Integer> stack){
		
		StackIF<Integer> oriStack = new StackDynamic<Integer>(stack); 
		
		StackIF<Integer> newStack = new StackDynamic<Integer>(); 
		
		
		while(!oriStack.isEmpty()){
			newStack.push(oriStack.getTop());
			oriStack.pop();
		}
		
		return newStack;
		
		
	}

}

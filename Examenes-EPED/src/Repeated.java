import eped.IteratorIF;
import eped.list.ListDynamic;
import eped.list.ListIF;
import eped.stack.StackDynamic;
import eped.stack.StackIF;


public class Repeated {
	
	/**
	 * Numero de repeticiones que aparece un nœmero en una lista dada.
	 * @param input Lista a revisar
	 * @return devuelve una lista con el nœmero de repeticiones de cada nœmero.
	 */
	public ListIF<Integer> repeated(ListIF<Integer> input){
		
		ListIF<Integer> newList = new ListDynamic();
		
		StackIF<Integer> aux = new StackDynamic();
		
		if(input.getLength()==0) return newList;
		
		else{
				
			//IteratorIF<Integer> itInput = input.getIterator();
						
			//Integer value = itInput.getNext();
			
			Integer value = input.getFirst();
			input = input.getTail();
					
			int count = 1;
			//while(itInput.hasNext()){
			while(!input.isEmpty()){
				
				Integer nextValue = input.getFirst();
				input = input.getTail();
				
				if(nextValue==value){
					count++;
				}
				
				else
					
					aux.push(nextValue);			
			}
			
			while(!aux.isEmpty()){
				newList.insert(aux.getTop());
				aux.pop();
			}
			
			ListIF<Integer> list = repeated(newList);
			
			list.insert(count);
			
			return list;
					
		}
			
	}

}

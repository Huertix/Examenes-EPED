import eped.ComparatorIF;
import eped.IteratorIF;
import eped.list.ListDynamic;
import eped.list.ListIF;


public class A1 extends ListDynamic<Integer> implements ListIF<Integer>{

	ListIF<Integer> oriList;
	
	public A1(ListIF<Integer> l){
		oriList = new ListDynamic(l);
	}
	
	public void insertAtTheEnd(Integer e){
		
		oriList = insertAtTheEnd(oriList, e);
		
	}
	
	public ListIF<Integer> insertAtTheEnd(ListIF<Integer> list,  Integer e){
			
		if(list.isEmpty()){ 
			 ListIF<Integer> newList = new ListDynamic<Integer>();
			 newList.insert(e);
			 return newList;
		}
		Integer intAux = list.getFirst();
		//list = list.getTail();
		ListIF<Integer> l = insertAtTheEnd(list.getTail(), e).insert(intAux);
		//l.insert(intAux);
		return l;
		
	}
	
	public ListIF<Integer> getList(){
		return oriList;
	}

	
	
}

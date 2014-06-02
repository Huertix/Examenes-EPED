/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eped.list;

import eped.ComparatorIF;
import eped.IteratorIF;
import eped.stack.StackDynamic;
import eped.stack.StackIF;
import eped.tree.TreeIF;


/**
 *
 * @author huertix
 */
public class ListDynamic<T> implements ListIF<T> {
    private T first;
    private ListIF<T> tail;

    
    public ListDynamic(){
        first = null;
        tail = null;
    }
   
    
    public ListDynamic(ListIF<T> list){
        this();
        if (list != null){
            if (!list.isEmpty()){
                first = list.getFirst ();
                tail = new ListDynamic<T> (list.getTail());
            }
        }
    }
    
    
    @Override
    public T getFirst(){
        return first;
    }


    @Override
    public ListIF<T> getTail(){
        if (isEmpty ())
            return this;
        else
            return tail;
    }


    @Override
    public ListIF<T> insert (T element){
        if (element != null) {
            ListDynamic<T> next = new ListDynamic<T>();
            next.first = first;
            next.tail = tail;
            first = element;
            tail = next;
        }
        return this;
    }
    
    


    @Override
    public boolean isEmpty (){
        return first == null && tail == null;
    }


    @Override
    public boolean isFull () {
        return false;
    }


    @Override
   public int getLength (){    
        if (isEmpty ()) 
            return 0;
        else 
            return 1 + tail.getLength ();
   }


    public IteratorIF<T> getIterator (){
        ListIF<T> handler = new ListDynamic<T>(this);
        return new ListIterator<T>(handler);
    }

    

    /**
    * Devuelve cierto si la lista contiene el elemento.
    * Metodo Iterativo.
    * @param element El elemento buscado.
    * @return cierto si la lista contiene el elemento.
    */
    
    public boolean containsIter (T element){
        IteratorIF<T> listIt = this.getIterator ();
        boolean found = false;
        while (!found && listIt.hasNext ()) {
            T anElement = listIt.getNext ();
            found = anElement.equals (element);
        }
        return found;
    }
    
    /**
    * Devuelve cierto si la lista contiene el elemento.
    * Metodo Recursivo.
    * @param element El elemento buscado.
    * @return cierto si la lista contiene el elemento.
    */
    @Override
    public boolean contains (T element){
        if (isEmpty ()) return false;
        return first.equals (element) || tail.contains (element);
    }



    /**
    * Ordena los elementos de la lista por inserción.
    * @param comparator El comparador de elementos.
    * @return la lista ordenada.
    */
    @Override
    public ListIF<T> sort (ComparatorIF<T> comparator){
        if (isEmpty ()) return this;
        else return ((ListDynamic<T>) tail.sort (comparator)).
                                                sortInsert(first, comparator);
    }
    
    /**
    * Ordena los elementos de la lista por Mezcla.
    * @param element El comparador de elementos.
    * @return la lista ordenada.
    */
    
    public ListIF<T> sortMx (ComparatorIF<T> comparator){
        if (getLength () <= 1) return this;
        else {
            int middle = (int) (getLength () / 2);
            int index = 0;
            ListIF<T> lLeft = new ListDynamic<T> ();
            ListIF<T> lRight = new ListDynamic<T> ();
            IteratorIF<T> listIt = getIterator ();
            while (listIt.hasNext ()) {
                T element = listIt.getNext ();
                if (index < middle) lLeft.insert (element);
                if (index >= middle) lRight.insert (element);
                index = index + 1;
            }
            lLeft = lLeft.sortMx (comparator);
            lRight = lRight.sortMx (comparator);
            return sortMerge (lLeft, lRight, comparator);
        }
    }

    
    /**
    * Inserta un elemento en orden en una lista ordenada.
    * @param element El elemento a insertar
    * @param comparator El comparador de elementos.
    * @return la lista ordenada.
    */
    private ListIF<T> sortInsert (T element, ComparatorIF<T> comparator){
        if (isEmpty ()) return this.insert (element);
        else if (comparator.isLess (element, first))
            return this.insert(element);
        else return ((ListDynamic<T>) tail).sortInsert (element, comparator).
                                                                insert (first);
    }
    
    
    private ListIF<T> sortMerge (ListIF<T> lLeft, ListIF<T> lRight,
                                                    ComparatorIF<T> comparator){
        
        ListDynamic<T> result = new ListDynamic<T> ();
        while (lLeft.getLength () > 0 || lRight.getLength () > 0) {
            if (lLeft.getLength () > 0 && lRight.getLength () > 0) {
                T eLeft = lLeft.getFirst ();
                T eRight = lRight.getFirst ();
                if (comparator.isGreater (eLeft, eRight)) {
                    result.insert (eLeft);
                    lLeft = lLeft.getTail ();
                } else {
                    result.insert (eRight);
                    lRight = lRight.getTail ();
                }
            } else if (lLeft.getLength () > 0) {
                T eLeft = lLeft.getFirst ();
                result.insert (eLeft);
                lLeft = lLeft.getTail ();
            } else if (lRight.getLength () > 0) {
                T eRight = lRight.getFirst ();
                result.insert (eRight);
                lRight = lRight.getTail ();
            }
        }
        return result;
    }


	@Override
	public void insertAtTheEnd(T e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isPalindrome() {
		
		return isPalindrome(this);
	}
	
	public boolean isPalindrome(ListIF<T> list){
		
		if(list==null) return false;
		
		StackIF<T> auxStack = new StackDynamic<T>();
		
		ListIF<T> auxList = new ListDynamic<T>(list);
		
		IteratorIF<T> it = list.getIterator();
		
		while(it.hasNext()){
			auxStack.push(it.getNext());
		}
		
		boolean middleReached = false;
		boolean areEquals = true;
		
		while(areEquals && !middleReached){
			areEquals = auxStack.getTop().equals(auxList.getFirst());
			middleReached = auxList.getLength() < list.getLength()/2;
			auxStack.pop();
			auxList = auxList.getTail();
			
		}
		
		return areEquals;
		
		
		

	}
	
	
	@Override
	public void removeRepeated(){
		
		ListIF<T> list = new ListDynamic<T>(this);
		ListIF<T> result = removeRepeated(list);
		
		this.first = result.getFirst();
		this.tail = result.getTail();
				
	}
	
	public ListIF<T> removeRepeated(ListIF<T> list){
		
		if(list.getLength()==1) return list;
		
		else{
			T element = list.getFirst();
			ListIF<T> newList = removeRepeated(list.getTail());
			
			if(!newList.contains(element)){
				newList.insert(element);
				
			}
			
			return newList;
		}
		
	}
	
	public int dosRepes(ListIF<T> listOri){
		
		ListIF<T> list = new ListDynamic<T>(listOri);
		
		int count = 0;

		while(!list.isEmpty()){
			int repeated = 0;
			
			T element = list.getFirst();
			list = list.getTail();
			
			if(element.equals(list.getFirst())){
				repeated++;
				
				if(repeated < 2 && list.getTail().getFirst()!=element ){
					count++;
				}
				
				else{
					repeated = 0;
					list = list.getTail();
					
				}
			}
			
			
		
		}
		
		return count;
		
		
	}
	
	
	public int secIguales(int k){
		
		ListIF<T> list = new ListDynamic<T>(this);
		int total = 0;
		int count = 0;
		
		while(list.getLength() > 0){
		
			Integer element = (Integer) list.getFirst();
			
			if(k==element)
				count++;
			
			else{
				if(count>total)
					total = count;
				count = 0;
			}
			list = list.getTail();

		}
		
		return total;
		
	}
	
	
	public ListIF<T> noRepes(){
		
		StackIF<T> stack = new StackDynamic<T>(this);
		
		ListIF<T> newList = new ListDynamic<T>();
		
		while(!stack.isEmpty()){
			newList.insert(stack.getTop());
			stack.pop();
		}
		
		
		
		
		return noRepes(newList);
		
	}
	
	public ListIF<T> noRepes(ListIF<T> list){
		
		if(list.isEmpty()) return new ListDynamic<T>();
		
		T element = list.getFirst();
		list = noRepes(list.getTail());
		
		if(!list.contains(element))
			list.insert(element);
		
		return list;
		
	}
	
	

		

   
}

import eped.ComparatorBase;
import eped.list.ListDynamic;
import eped.list.ListIF;
import eped.queue.QueueDynamic;
import eped.queue.QueueIF;


public class Ordenar {
	
	
	
	public ListIF<Integer> quickSort(ListIF<Integer> lista){
		QueueIF<Integer> queue = new QueueDynamic<Integer>(lista);
		quickSort(queue);
		
		ListIF<Integer> list = new ListDynamic<Integer>();
		
		while(!queue.isEmpty()){
			list.insert(queue.getFirst());
			queue.remove();
		}
		
		return list;
 		
	}
	
	
	
	public void quickSort(QueueIF<Integer> queue){
		
			
		if(queue.getLength()<= 1) return;// queue;
		
		QueueIF<Integer> menores = new QueueDynamic<Integer>();
		QueueIF<Integer> mayores = new QueueDynamic<Integer>();
		QueueIF<Integer> iguales = new QueueDynamic<Integer>();
		
		
		iguales.add(queue.getFirst());
		queue.remove();
		
		
		while(!queue.isEmpty()){
			
			int pivot = iguales.getFirst();
			int nextValue = queue.getFirst();
			queue.remove();
			
			if(nextValue < pivot)
				menores.add(nextValue);
			else if(nextValue > pivot)
				mayores.add(nextValue);
			else
				iguales.add(nextValue);
			
		}
			
		quickSort(menores);
		quickSort(mayores);
		
		addALL(queue,mayores);
		addALL(queue,iguales);
		addALL(queue,menores);
		
		//return queue;
				
	}
	
	public void addALL(QueueIF<Integer> a, QueueIF<Integer> b){
		
		while(!b.isEmpty()){
			a.add(b.getFirst());
			b.remove();
		}
	}
	
	

}




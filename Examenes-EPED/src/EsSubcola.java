import eped.IteratorIF;
import eped.queue.QueueDynamic;
import eped.queue.QueueIF;


public class EsSubcola extends QueueDynamic<Integer>{
	

	boolean isSubqueue (QueueIF<Integer> mainQueue){
		
		IteratorIF<Integer> itContenedora = mainQueue.getIterator();

		int posicionEnCurso = 0;

		boolean esSubcola  = false;

		while(!esSubcola && itContenedora.hasNext()){

			itContenedora.reset();
			for(int i = 0; i< posicionEnCurso; i++){

				itContenedora.getNext();

			}

			IteratorIF<Integer> itSubcola = getIterator();

			boolean comprobados = true;

			while(comprobados && itContenedora.hasNext() && itSubcola.hasNext()){

				if(!itContenedora.getNext().equals(itSubcola.getNext())){

					comprobados = false;
				}

			}

		
			if(comprobados && !itSubcola.hasNext()){
				esSubcola = true;
			}

			posicionEnCurso ++;

		}

		return esSubcola;


	}

}

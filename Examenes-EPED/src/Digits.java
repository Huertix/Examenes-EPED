import eped.list.ListDynamic;
import eped.list.ListIF;


public class Digits {
	
	public ListIF<Integer> digits(long input){
		
		ListIF<Integer> salida = new ListDynamic();
		
		while(input!=0){
			long digit = input % 10;
			input = input / 10;
			salida.insert((int) digit);
		}
		
		return salida;
		
	}

}

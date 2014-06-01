import java.util.Date;
import java.util.Random;

import eped.list.ListDynamic;
import eped.list.ListIF;
import eped.stack.StackDynamic;
import eped.stack.StackIF;
import eped.tree.BTreeAVL;
import eped.tree.BTreeIF;
import eped.tree.BTreeIterator;
import eped.tree.BTreeSearch;


public class main {

	public static void main(String[] args) {
		
		
		
		
		main m = new main();
		
		/*
		long b = 303208845;
		
		ListIF<Integer> a = new Digits().digits(b);
		
		ListIF<Integer> repetidos = new Repeated().repeated(a);
		
		while(!a.isEmpty()){
			System.out.println(a.getFirst());
			a = a.getTail();
		}
		
		System.out.println("----------");
		
		
		
		while(!repetidos.isEmpty()){
			System.out.println(repetidos.getFirst());
			repetidos = repetidos.getTail();
		}
		
		
		System.out.println("----------");
		System.out.println("----------");
		
		BTreeSearch<Integer> bts = new BTreeSearch<Integer>();
		
		StackIF<Integer> stack = m.stackGenerator(13);
		
		while(!stack.isEmpty()){
			bts.insert(stack.getTop());
			System.out.println(stack.getTop());
			stack.pop();
		}
		
		m.printTree(bts.getBTree());
		
		
		
		
		bts.remove(62);
		bts.remove(27);
		bts.remove(8);
		bts.remove(11);
		bts.remove(58);
		bts.remove(5);
		bts.remove(92);
		bts.insert(5);
		bts.remove(3);
		bts.remove(46);
		bts.remove(67);
		bts.remove(52);
		bts.remove(5);
	
		
		m.printTree(bts.getBTree());
		
		
		
		
		
		System.out.println("----------");
		System.out.println("----------");
		
		
		
		m.newDic();
		
		System.out.println("----------");
		System.out.println("----------");
		
	
		BTreeAVL<Integer> avl = new BTreeAVL<Integer>();
		
		StackIF<Integer> stack = m.stackGenerator(999999);
		
		
		
		
		while(!stack.isEmpty()){
			avl = (BTreeAVL<Integer>) avl.insert(stack.getTop());
			//m.printTree(avl);
			stack.pop();
		}
		
		
		long lStartTime = new Date().getTime();
		
		while(avl != null){
			int out = avl.findMin().getRoot();
			
			//System.out.println("min: "+ out);
			
			avl = (BTreeAVL<Integer>) avl.remove(out);
		}
		//m.printTree(avl);
		
		
		long lEndTime = new Date().getTime(); // end time
        
		long difference = lEndTime - lStartTime; // check different
		 
		System.out.println("Elapsed milliseconds: " + difference);
	
		
		
		ListIF<Integer> list = m.listGenerator(1);
		
		//System.out.println("Sin Orden");
		
		//m.printList(list);
		
		
		
		Ordenar ord = new Ordenar();
		
		
		
		list = ord.quickSort(list);
		//System.out.println("Con Orden");
		//m.printList(list);
		
		
		
		
		
		
		ListIF<Integer> list = m.listGenerator(4);
		A1 insertAtLast = new A1(list);
		
		m.printList(insertAtLast.getList());
		
		insertAtLast.insertAtTheEnd(5);
		
		insertAtLast.getList();
		
		
		System.out.println("----------");
		m.printList(insertAtLast.getList());
		
		//----------------------------------------------------------------
		
		System.out.println("----------\n");
		System.out.println("Stack");
		System.out.println("----------");
		
		A2 a2 = new A2();
		StackIF<Integer> stack = m.stackGenerator(5);
		
		m.printStack(stack);
		
		stack = a2.reverse(stack);
		System.out.println("----------");
		m.printStack(stack);
		*/
		
		//B1 b1 = new B1();
		
		//B2 b2 = new B2();
		
		//C1 c1 = new C1();
		
		C2 c2 = new C2();
		
		m.printList(c2.getList());
		
		c2.remove();
		System.out.println("----------");
		
		m.printList(c2.getList());
		
		
		
		
		
		
	}
	
	
	public void printStack(StackIF<Integer> stack){
		StackIF<Integer> aux = new StackDynamic<Integer>(stack);
		
		while(!aux.isEmpty()){
			System.out.println(aux.getTop());
			aux.pop();
		}
	}
	
	public void printList(ListIF<Integer> list){
		ListIF<Integer> aux = new ListDynamic<Integer>(list);
		
		while(!aux.isEmpty()){
			System.out.println(aux.getFirst());
			aux = aux.getTail();
		}
	}
		
		
	public void newDic(){
		
		Dictionary dic = new Dictionary();
		dic.addDefinition("alicia", "Commun Female Name");
		dic.addDefinition("alicia", "nombre comun fenenino");
		dic.addDefinition("alicate", "Tool");
		dic.addDefinition("absurdo", "Algo sin sentido");
		dic.addDefinition("abadía", "Lugar de rezo");
		dic.addDefinition("tortilla", "comida española salada");
		dic.addDefinition("tortilla", "Tipical Spanish food \"omenet\"");
		dic.addDefinition("torta", "dulce tipico del sur");
		dic.addDefinition("laptop", "Ordenador Portatil");
		dic.addDefinition("abad", "Máximo responsable de la abadía");

		System.out.println(dic.getDefinition("hola"));
		System.out.println(dic.getDefinition("torta"));
		System.out.println();
		System.out.println(dic.getDefinition("absurdo",0));
		
		System.out.println();
		ListIF<String> output = dic.getDefinitions("abadía");
		
		while(!output.isEmpty()){
			System.out.println(output.getFirst());
			output = output.getTail();
		}
	}
	
	
	
	
	public void printTree(BTreeIF<Integer> tree){
		
		BTreeIterator<Integer> it = new BTreeIterator<Integer>(tree ,BTreeIF.PREORDER);
		System.out.println("Salida");
		while(it.hasNext()){
			System.out.println(it.getNext());
		}
		System.out.println();	
	}
	
	
	public ListIF<Integer> listGenerator(int items){
		
		Random rd =  new Random(4);
		
		ListIF<Integer> list = new ListDynamic<Integer>();
		
		for(int i=0;i<items;i++){
			list.insert(rd.nextInt(100));
		}
		
		return list;
	}
	
	
	public StackIF<Integer> stackGenerator(int items){
		
		//Random rd =  new Random(4);
		
		StackIF<Integer> stack = new StackDynamic<Integer>();
		
		for(int i=0;i<items;i++){
			//stack.push(rd.nextInt(100));
			stack.push(i);
		}
		
		return stack;
	}
}

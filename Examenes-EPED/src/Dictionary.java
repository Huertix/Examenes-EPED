import eped.IteratorIF;
import eped.list.ListDynamic;
import eped.list.ListIF;
import eped.tree.TreeDynamic;
import eped.tree.TreeIF;


public class Dictionary {

	TreeIF<TreeIF<String>> tree;
	
	public Dictionary(){
		tree = new TreeDynamic<TreeIF<String>>();
	}
	
	public void addDefinition(String term, String definition){
		
		TreeIF<TreeIF<String>> treeSelected = addDefinition(tree,term); // retorna el arbol donde se insertara la defición
																		// última letra del termino
		if(treeSelected !=null){
			TreeIF<String> treeSon = treeSelected.getRoot();
			treeSon.addChild(new TreeDynamic<String>(definition));
		}
	}
	
	
	private TreeIF<TreeIF<String>> addDefinition(TreeIF<TreeIF<String>> auxTree, String nextString){
	
		if(nextString.length() == 0) return auxTree; // Retorna el arbol donde se insertara el string;
		
		else{
			String[] auxString = nextString.split(""); // Seara la cadena de caracteres
			
			String character = auxString[1]; //Coge 1º Caracter
			
			IteratorIF<TreeIF<TreeIF<String>>> it = auxTree.getChildren().getIterator();// Iterador de lo hijos
			
			boolean found = false;
			while(it.hasNext() && !found){
				
				TreeIF<TreeIF<String>> nextTree = it.getNext();
				
				if(nextTree.getRoot().getRoot().equals(character)){ // si encuentra el caracter, profundiza en el arbol
					auxTree = addDefinition(nextTree, nextString.substring(1));
					found=true;
				}
			}
				
			if(!found){ // Si no lo ha encontrado, crea un nuevo arbol, inserta el caracter y lo inserta como hijo de la raíz 
				
				TreeIF<TreeIF<String>> newTree = new TreeDynamic<TreeIF<String>>(new TreeDynamic<String>(character));
				auxTree.addChild(newTree);
				auxTree = addDefinition(newTree, nextString.substring(1));				
			}
					
			return auxTree;
		}	
	}
	
	
	
	public String getDefinition(String term){
	
		TreeIF<TreeIF<String>> output = getDefinition(tree,term);
		
		if(output !=null){
			TreeIF<String> auxOutput = output.getRoot();
			ListIF<TreeIF<String>> list =auxOutput.getChildren();
			String str = list.getFirst().getRoot();
			return str;
		}
		
		else
			return "Termino no existe";	
	}
	
	private TreeIF<TreeIF<String>> getDefinition(TreeIF<TreeIF<String>> auxTree, String term){
		
		if(term.length()==0) return auxTree;
		
		else{
			
			String[] auxString = term.split("");
			
			String character = auxString[1];
			
			IteratorIF<TreeIF<TreeIF<String>>> it = auxTree.getChildren().getIterator();
			boolean found = false;
			while(it.hasNext()  && !found){
				
				TreeIF<TreeIF<String>> nextTree = it.getNext();
				
				if(nextTree.getRoot().getRoot().equals(character)){
					auxTree = getDefinition(nextTree, term.substring(1));
					found = true;
				}
					
			}
			
			if(!found){
				return null;
				
			}
			
			return auxTree;
			
			
		}
		
	}
	
	
	public String getDefinition(String term, int index){
		
		ListIF<String> output = getDefinitions(term);
		
		int count = 0;
		while(!output.isEmpty() && count < index){
			output = output.getTail();
			count++;
		}
		
		String strOutput = output.getFirst();
		
		if(strOutput!=null)
			return strOutput;
		else
			return "No hay Descripción en index:" + index;
		
		
		
		
		
	}
	
	
	public ListIF<String> getDefinitions(String term){
		
		ListIF<String> list = new ListDynamic<String>();
		
		TreeIF<TreeIF<String>> output = getDefinition(tree,term);
		
		IteratorIF<TreeIF<String>> it = output.getRoot().getChildren().getIterator();
		
		
		while(it.hasNext()){
			TreeIF<String> treeSelected = it.getNext();
			if(treeSelected != null)
				list.insert(treeSelected.getRoot().toString());
		
		}
		
		return list;

	}







}

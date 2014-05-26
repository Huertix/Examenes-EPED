package eped.tree;

import eped.ComparatorBase;
import eped.IteratorIF;

public class BTreeAVL<T> implements BTreeIF<T>{
	
	T root;
	BTreeIF<T> left;
	BTreeIF<T> right;
	int height;
	
	public BTreeAVL(){
		this.root = null;
		this.left = null;
		this.right = null;
	}
		
	public BTreeAVL(T element){
		this();
		this.setRoot(element);
	}
	
	public BTreeAVL(BTreeIF<T> tree){
		this();
		T tRoot = tree.getRoot();
		BTreeIF<T> tLeft = tree.getLeftChild();
		BTreeIF<T> tRight = tree.getRightChild();
		
		this.setRoot(tRoot);
		this.setLeftChild(new BTreeDynamic<T> (tLeft));
		this.setRightChild(new BTreeDynamic<T> (tRight));
	}
		
	
	
	public BTreeIF<T> insert (T element){
		return insert(element, this);
	}
	
	private BTreeIF<T> insert(T element, BTreeIF<T> tree){
		
		if(tree==null || tree.isEmpty()) return new BTreeAVL<T>(element);
		
		Comparator<T> comp =  new Comparator<T>();
		
		if(comp.isLess(element, tree.getRoot())){
			
			tree.setLeftChild(insert(element,tree.getLeftChild()));
		}
		
		else if(comp.isGreater(element, tree.getRoot())){
	
			tree.setRightChild(insert(element,tree.getRightChild()));
		}
		
		else{
			//System.out.println("Repetido " + element);
			}
			
		return balance(tree);
	}
	
	
	public BTreeIF<T> remove(T element){
		return remove(element, this);
	}
	
	private BTreeIF<T> remove(T element, BTreeIF<T> tree){
		if(tree==null || tree.isEmpty()) return tree;
		
		Comparator<T> comp =  new Comparator<T>();
		
		if(comp.isLess(element, tree.getRoot())){
				tree.setLeftChild(remove(element, tree.getLeftChild()));
		}
		
		else if(comp.isGreater(element, tree.getRoot())){
			tree.setRightChild(remove(element,tree.getRightChild()));
		}
		
		else if(tree.getLeftChild()!=null && tree.getRightChild()!= null){
			tree.setRoot(findMin(tree.getRightChild()).getRoot());
			tree.setRightChild(remove(tree.getRoot(),tree.getRightChild()));
		}
	
		
		else
			tree = (tree.getLeftChild() != null) ? tree.getLeftChild() : tree.getRightChild();
		
		
		return balance(tree);
	}
	
	public BTreeIF<T> balance(){
		return balance(this);
	}
	
	public BTreeIF<T> balance(BTreeIF<T> tree){
		
		if(tree == null) return tree;
		
		int left = getHeight(tree.getLeftChild());
		int right = getHeight(tree.getRightChild());
		
		if( left - right > 1){
			
			int lLeft = getHeight(tree.getLeftChild().getLeftChild());
			int lRight = getHeight(tree.getLeftChild().getRightChild());
			if( lLeft >= lRight){
				tree = rotateWithLeftChild(tree);
			}
			
			else{
				tree = doubleWithLeftChild(tree);
			}
		}
		
		else if(getHeight(tree.getRightChild()) - getHeight(tree.getLeftChild()) > 1){
			if(getHeight(tree.getRightChild().getRightChild()) >= getHeight(tree.getRightChild().getLeftChild())){
				tree = rotateWithRightChild(tree);
			}
			
			else{
				tree = doubleWithRightChild(tree);
			}	
			
		}
		else{}
		
		tree.setHeight(Math.max(getHeight(tree.getLeftChild()),getHeight(tree.getRightChild())) + 1);
		return tree;
		
		
		
	}

	
	public BTreeIF<T> rotateWithLeftChild(BTreeIF<T> tree){
		BTreeIF<T> leftTree =tree.getLeftChild();
		tree.setLeftChild(leftTree.getRightChild());
		leftTree.setRightChild(tree);

		int height = Math.max( getHeight(tree.getLeftChild()), getHeight(tree.getRightChild())) +1;
		tree.setHeight(height);

		height = Math.max(getHeight(tree.getLeftChild()), tree.getHeight()) + 1;
		leftTree.setHeight(height);
		
		return leftTree;
	}
	
	public BTreeIF<T> doubleWithLeftChild(BTreeIF<T> tree){
		tree.setLeftChild(rotateWithRightChild((BTreeAVL<T>) tree.getLeftChild()));
		return rotateWithLeftChild( (BTreeAVL<T>) tree);
	}
	

	public BTreeIF<T> rotateWithRightChild(BTreeIF<T>tree){
		BTreeIF<T> rightTree = tree.getRightChild();
		tree.setRightChild(rightTree.getLeftChild());
		rightTree.setLeftChild(tree);

		int height = Math.max(getHeight(tree.getLeftChild()), getHeight(tree.getRightChild())) +1;
		tree.setHeight(height);

		height = Math.max(getHeight(rightTree.getRightChild()), tree.getHeight()) + 1;
		rightTree.setHeight(height);
		
		return rightTree;
	}
		
	public BTreeIF<T> doubleWithRightChild(BTreeIF<T> tree){
		tree.setRightChild(rotateWithLeftChild( (BTreeAVL<T>) tree.getRightChild()));
		return rotateWithRightChild( (BTreeAVL<T>) tree);
	}
	
	

	
	
	public BTreeIF<T> findMin(){
		
		return findMin(this);
	}
	
	public BTreeIF<T> findMin(BTreeIF<T> tree){
		if(tree.getLeftChild() == null) return tree;
		return findMin(tree.getLeftChild());
	}
	
	public BTreeIF<T> findMax(){
		
		return findMax(this);
	}
	
	public BTreeIF<T> findMax(BTreeIF<T> tree){
		if(tree.getRightChild() == null) return tree;
		return findMax(tree.getRightChild());
	}
	
	
	@Override
	public T getRoot() {
		return this.root;
	}
	
	@Override
	public void setRoot(T element) {
		if(element != null)
			this.root = element;	
	}

	@Override
	public BTreeIF<T> getRightChild() {
		return this.right;
	}

	@Override
	public BTreeIF<T> getLeftChild() {
		return this.left;
	}
	
	
	public void setHeight(int i){
		this.height = i;
	}
	
	
	public int getHeight(){
		return this.height;
	}
	
	private int getHeight(BTreeIF<T> tree){
		
		return tree == null ? -1 : tree.getHeight();	
	}
	

	public void setLeftChild (BTreeIF<T> tree){
		this.left = tree;
	}
	
	public void setRightChild(BTreeIF<T> tree){
		this.right = tree;
	}
	
	public void removeLeftChild(){
		this.left = null;
		
	}
	
	public void removeRightChild(){
		this.right = null;
	}
	
	public boolean isLeaf(){
		return this.root != null && left==null && right==null;
	}
	
	public boolean isEmpty(){
		return this.root == null;
		
	}
	
	public boolean contains(T element){
		return this.root.equals(element) || this.left.contains(element) || this.right.contains(element);
		
	}
	
	public boolean containsPreorden(T element){
		if(element == null) return false;
		return element.equals(getRoot()) || 
				(getLeftChild() != null)? getLeftChild().contains(element) : false ||
				(getRightChild() != null)? getRightChild().contains(element) : false;
	}
	
	public boolean containsCentinela(T element){
		
		boolean found = false;
		IteratorIF<T> it = getIterator (BTreeIF.INORDER);
		
		while(!found && it.hasNext()){
			T anElement = it.getNext();
			found = anElement.equals(element);
		}
		
		return found;
	}
	
	public IteratorIF<T> getIterator(int type){
		BTreeIF<T> handler = new BTreeDynamic<T> (this);
		return new BTreeIterator<T> (handler, type);
	}
	
	public int hashCode(){
		return 31 * 31 * ((root == null) ? 0 : root.hashCode()) +
					31 * ((left == null) ? 0 : left.hashCode()) + 
					31 * ((right == null)? 0 : right.hashCode());
	}
	
	private class Comparator<T> extends ComparatorBase<T>{
		
		private Comparator(){
			super();
		}
	}
}
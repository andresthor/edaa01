package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    private boolean addReturn;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = add(root, x);	// Anropar rekursiva versionen av add
		return addReturn;
	}
	
	/**
	 * Recursive method that adds the specified element to the tree if
	 * no duplicate exists.
	 * @param localRoot root of the subtree to be checked
	 * @param x element to be added
	 * @return a node with the element that was to be added
	 * 
	 * @post the boolean addReturn is set to true if the element was
	 * added, otherwise set to false.
	 */
	private BinaryNode<E> add(BinaryNode<E> localRoot, E x) {
		if (localRoot == null) {
			// Elementet finns inte i tr�d - vi l�gger till det
			addReturn = true;
			size++;
			return new BinaryNode<E>(x);
		} else if (x.compareTo(localRoot.element) == 0) {
			// Elementet finns i tr�d - vi returnerar den noden ist�llet
			addReturn = false;
			return localRoot;
		} else if (x.compareTo(localRoot.element) < 0) {
			// Elementet �r mindre �n localRoot.element - vi f�rs�ker l�gga in
			// det i v�nster subtr�d
			localRoot.left = add(localRoot.left, x);
			return localRoot;
		} else {
			// Elementet �r st�rre �n localRoot.element - vi f�rs�ker l�gga in
			// det i h�ger subtr�d
			localRoot.right = add(localRoot.right, x);
			return localRoot;
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	/**
	 * Recursive method that computes the height of the subtree with
	 * localRoot as its root
	 * @param localRoot root of the subtree
	 * @return height of the subtree
	 */
	private int height(BinaryNode<E> localRoot) {
		if (localRoot != null)
			return 1 + Math.max(height(localRoot.left),		// J�mf�r v�nster och h�ger subtr�d
								height(localRoot.right));	// Returnerar 1 + storleken av det st�rre
		return 0;
	}

	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printInOrder(root); // Anropar den rekursiva metoden printInOrder
	}

	/**
	 * Recursive method that prints the tree contents in inorder
	 * @param localRoot the root of the subtree to be printed
	 */
	private void printInOrder(BinaryNode<E> localRoot) {
		if (localRoot != null) {
			printInOrder(localRoot.left); 		   // Skriver ut v�nster subtr�d
			System.out.println(localRoot.element); // Skriver ut subtr�dets root
			printInOrder(localRoot.right); 		   // Skriver ut v�nster subtr�d
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size]; // Skapar array som kan inneh�lla alla elementen
		root = buildTree(a, 0, toArray(root, a, 0) - 1); // Anropar den rekursiva metoden buildTree
	}
	
	/**
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns first empty position
	 * @param n root of the subtree
	 * @param a array that the elements will be added to
	 * @param index first available position in the array
	 * @return index of last inserted element + 1 (first empty position of
	 * the array)
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n != null) {
			index = toArray(n.left, a, index);     // V�nster subtr�d l�ggs i array a
			a[index] = n.element; 				   // Roten l�ggs i array a
			return toArray(n.right, a, index + 1); // H�ger subtr�d l�ggs i array a
		}
		return index;
	}
	
	/**
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if (first > last)
			return null;

		int middle = (last + first) / 2;				  // Mittpunkten ska bli roten
		BinaryNode<E> localRoot =
				new BinaryNode<E>(a[middle]);
		localRoot.left = buildTree(a, first, middle - 1); // Skapar v�nster subtr�d med rekursivt anrop
		localRoot.right = buildTree(a, middle + 1, last); // Skapar h�ger subtr�d med rekursivt anrop
		return localRoot;
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BSTVisualizer bst = new BSTVisualizer("Tree Drawing", 1000, 600);
		
		for (int i = 50; i > 0; i--) { // L�gger in heltal fr�n 1-50
			tree.add(i);
		}
		tree.printTree();	// Skriver ut nodernas inneh�ll
		tree.rebuild();		// Balanserar tr�det
		System.out.println("Height = " + tree.height() + " size = " + tree.size());
		bst.drawTree(tree);
	}
	
	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
}


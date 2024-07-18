package RedBoxInventorySystem.RedBoxFunctions.Common;

public class BinarySearchTree<E extends Comparable<E>> {

    // subClass
    public class Node<T> {
        T key;
        Node<T> leftChild;
        Node<T> rightChild;

        // Constructor
        public Node() {
            System.out.println("The root node has been created");
        }

        // Constructor with parameters
        Node(T element) {
            key = element;
        }

    }

    // Main class
    Node<E> root;
    int size = 0;

    // Default constructor
    public BinarySearchTree() {
        root = null;
    }

    // Parameterized constructor
    public BinarySearchTree(E element) {
        size++;
        Node<E> newNode = new Node<>(element);
        root = newNode;
    }

    // Call Method to insert a new node into a tree
    public void insertNode(E movie) {
        if (root == null) {
            root = new Node<>(movie);
        } else if (isContains(movie)) {
            System.out.println("The element already exist in the tree");
        } else {
            root = insertNode(root, movie);
        }

    }

    // Recursive Method to add a new Node to the tree
    public Node<E> insertNode(Node<E> currNode, E objectE) {
        if (currNode == null) {
            size++;
            return new Node<>(objectE);
        } else if (objectE.compareTo(currNode.key) > 0) {
            currNode.rightChild = insertNode(currNode.rightChild, objectE);
        } else {
            currNode.leftChild = insertNode(currNode.leftChild, objectE);
        }
        return currNode;

    }

    // remove node from tree
    public boolean remove(E element) {
        if (root == null) {
            return false;
        } else if (!isContains(element)) {
            System.out.println("The element does not exist in tree");
            return false;
        }
        root = removeNode(root, element);
        return true;
    }

    /*
     * Inorder Successor of a node is defined as the node with the smallest key
     * greater than the key of the input node
     * source: Digital Ocean
     * How to remove an element recursively:
     * 1) Need to search for the node to be removed and determine if it has children
     * or not
     * *If no children- Just delete
     * *If a single child - Copy that child to the node
     * *If two children - Determine the next highest element (inOrder successor) in
     * the right subtree
     * replace the node to be removed with the inorder successor. delete the inorder
     * successor duplicate
     * (can be found by finding the minimum value in the right child of the node )
     */

    public Node<E> removeNode(Node<E> currNode, E objectE) {
        if (currNode == null) {
            return currNode;
        }

        if (objectE.compareTo(currNode.key) > 0) {
            currNode.rightChild = removeNode(currNode.rightChild, objectE);
        } else if (objectE.compareTo(currNode.key) < 0) {
            currNode.leftChild = removeNode(currNode.leftChild, objectE);
        } else {
            // Case 2: One Child
            if (currNode.leftChild == null) {
                return currNode.rightChild;
            } else if (currNode.rightChild == null) {
                return currNode.leftChild;
            }

            // Case 3: Two Children
            // find the smallest data that is on the rightChild
            currNode.key = findInOrderSuccessor(currNode.rightChild);
            currNode.rightChild = removeNode(currNode.rightChild, currNode.key);

        }
        return currNode;
    }

    // find the smallest number in the current tree
    public E findInOrderSuccessor(Node<E> currNode) {
        E minimum = currNode.key;
        while (root.leftChild != null) {
            minimum = currNode.leftChild.key;
            root = root.leftChild;
        }
        return minimum;

    }

    // get size of Tree
    public int getSize() {
        return size;
    }

    public Node<E> getRoot() {
        return root;
    }

    // check to see if the node exist in the tree
    public Boolean isContains(E element) {
        return isContains(root, element);
    }

    public Boolean isContains(Node<E> currNode, E objectE) {
        if (currNode == null) {
            return false;
        } else if (objectE.compareTo(currNode.key) < 0) {
            return isContains(currNode.leftChild, objectE);
        } else if (objectE.compareTo(currNode.key) > 0) {
            return isContains(currNode.rightChild, objectE);
        }
        return true;
    }

    // find movie
    public E findNode(E element) {
        return findNode(root, element);
    }

    public E findNode(Node<E> currNode, E objectE) {
        if (currNode == null) {
            return null;
        } else if (objectE.compareTo(currNode.key) < 0) {
            return findNode(currNode.leftChild, objectE);
        } else if (objectE.compareTo(currNode.key) > 0) {
            return findNode(currNode.rightChild, objectE);
        }
        return currNode.key;
    }

    // print the tree
    public void printTree() {
        printInOrder(root);
        System.out.println();

    }

    public void printInOrder(Node<E> currNode) {
        if (currNode != null) {
            printInOrder(currNode.leftChild);
            System.out.println("-" + currNode.key.toString());
            printInOrder(currNode.rightChild);
        }
    }
}

// NOTES:
/*
 * if(string1 > string2 val++)
 * if(string1 < string2 val--)
 */
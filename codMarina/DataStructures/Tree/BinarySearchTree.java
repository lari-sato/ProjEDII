//  Giovana Simões Franco	    RA: 10417646
//  Julia Santos Oliveira		RA: 10417672
//  Larissa Yuri Sato		    RA: 10418318
//  Marina Haru Marcoulakis	    RA: 10417370 
package src.DataStructures.Tree;

import src.DataStructures.Node.Node;

public class BinarySearchTree {

    protected Node root;
    
    public Node getRoot() { 
        return root; 
    }

    public void setRoot(Node root) { 
        this.root = root;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int getDegree() {
        if (isEmpty()) {
            return -1;
        }
        
        return getDegree(root);
    }
    
    public int getHeight() {
        if (isEmpty()) {
            return -1;
        }
        
        return root.getHeight();
    }

    public void insert(Object value) {
        root = insertRec(root, value);
    }

    public void remove(Object value) {
        root = removeRec(root, value);
    }

	public Node find(Object value) {
        return findRec(root, value);
    }
	
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Árvore vazia.");
        } else {
            printTree(root, 0);
        }
    }

	protected Object minValue(Node node) {
        Object minVal = node.getValue();
        while (node.getLeftNode() != null) {
            minVal = node.getLeftNode().getValue();
            node = node.getLeftNode();
        }
        return minVal;
    }

    private Node removeRec(Node node, Object value) {
        if (node == null) {
            return null;
        }

        Comparable<Object> comparableValue = (Comparable<Object>) value;
        Comparable<Object> nodeValue = (Comparable<Object>) node.getValue();

        if (comparableValue.compareTo(nodeValue) < 0) {
            node.setLeftNode(removeRec(node.getLeftNode(), value)); 
        } else if (comparableValue.compareTo(nodeValue) > 0) {
            node.setRightNode(removeRec(node.getRightNode(), value)); 
        } else {
            if (node.getLeftNode() == null) {
                return node.getRightNode(); 
            } else if (node.getRightNode() == null) {
                return node.getLeftNode(); 
            }
            node.setValue(minValue(node.getRightNode()));
            node.setRightNode(removeRec(node.getRightNode(), node.getValue()));
        }
        return node;
    }

	private Node insertRec(Node node, Object value) {
        if (node == null) {
            return new Node(value); 
        }

        Comparable<Object> comparableValue = (Comparable<Object>) value;
        Comparable<Object> nodeValue = (Comparable<Object>) node.getValue();

        if (comparableValue.compareTo(nodeValue) < 0) {
            node.setLeftNode(insertRec(node.getLeftNode(), value)); 
        } else if (comparableValue.compareTo(nodeValue) > 0) {
            node.setRightNode(insertRec(node.getRightNode(), value)); 
		}
        return node;
    }

    private Node findRec(Node node, Object value) {
        if (node == null || node.getValue().equals(value)) {
            return node; 
        }

        Comparable<Object> comparableValue = (Comparable<Object>) value;
        Comparable<Object> nodeValue = (Comparable<Object>) node.getValue();

        if (comparableValue.compareTo(nodeValue) < 0) {
            return findRec(node.getLeftNode(), value); 
        }
        return findRec(node.getRightNode(), value); 
    }

    private void printTree(Node node, int level) {
        if (node != null) {
            printTree(node.getRightNode(), level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(node.getValue());
            printTree(node.getLeftNode(), level + 1);
        }
    }
    
    private int getDegree(Node node) {
        if (node == null) {
            return -1;
        }
        
        int leftDegree = getDegree(node.getLeftNode());
        int rightDegree = getDegree(node.getRightNode());

        return 1 + Math.max(leftDegree, rightDegree);
    }
}

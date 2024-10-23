//  Giovana Simões Franco	    RA: 10417646
//  Julia Santos Oliveira		RA: 10417672
//  Larissa Yuri Sato		    RA: 10418318
//  Marina Haru Marcoulakis	    RA: 10417370 
package src.DataStructures.Tree;

import src.DataStructures.Node.Node;

public class BalanceBinaryTree extends BinarySearchTree {

    @Override
    public void insert(Object value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, Object value) {
        if (node == null) {
            return new Node(value); 
        }

        Comparable<Object> comparableValue = (Comparable<Object>) value;
        Comparable<Object> nodeValue = (Comparable<Object>) node.getValue();

        if (comparableValue.compareTo(nodeValue) < 0) {
            node.setLeftNode(insertRec(node.getLeftNode(), value)); // Insert in the left subtree
        } else if (comparableValue.compareTo(nodeValue) > 0) {
            node.setRightNode(insertRec(node.getRightNode(), value)); // Insert in the right subtree
        }


        return balance(node);
    }

    @Override
    public void remove(Object value) {
        root = removeRec(root, value);
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

        return balance(node);
    }



    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.getLeftNode()) < 0) {
                node.setLeftNode(leftRotate(node.getLeftNode())); 
            }
            return rightRotate(node); 
        }

        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getRightNode()) > 0) {
                node.setRightNode(rightRotate(node.getRightNode())); 
            }
            return leftRotate(node); 
        }

        return node;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        int leftHeight = node.getLeftNode() == null ? -1 : node.getLeftNode().getHeight();
        int rightHeight = node.getRightNode() == null ? -1 : node.getRightNode().getHeight();
        return leftHeight - rightHeight;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.getLeftNode();
        Node transfer = newRoot.getRightNode();

        newRoot.setRightNode(node);
        node.setLeftNode(transfer);


        return newRoot;
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.getRightNode();
        Node transfer = newRoot.getLeftNode();

        newRoot.setLeftNode(node);
        node.setRightNode(transfer);

        return newRoot;
    }
}

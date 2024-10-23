//  Giovana Simões Franco	    RA: 10417646
//  Julia Santos Oliveira		RA: 10417672
//  Larissa Yuri Sato		    RA: 10418318
//  Marina Haru Marcoulakis	    RA: 10417370 
package src.DataStructures.Node;

public class Node {
    private Node rightNode;
    private Node leftNode;
    private Node parent;
    private Object value;

    public Node(Object value) {
        this.value = value;
    }

    public Node(Object value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.rightNode = null;
        this.leftNode = null;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
        

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Node getParent() {
        return parent;
    }

    public Object getValue() {
        return value;
    }

    
    public int getLevel() {
        if (isRoot()) {
            return 0;
        }
        return 1 + parent.getLevel();
    }

    public int getHeight() {
        if (isLeaf()) {
            return 0;
        }
        return 1 + Math.max(
            getLeftNode() == null ? 0 : getLeftNode().getHeight(),
            getRightNode() == null ? 0 : getRightNode().getHeight()
        );
    }

    public boolean isRoot() {
        return parent == null;
    }
    
    public boolean isLeaf() {
        return getRightNode() == null && getLeftNode() == null;
    }

    public int degree() {
        int degree = 0;
        if (getLeftNode() != null) degree++;
        if (getRightNode() != null) degree++;
        return degree;
    }

    public float visit() {
        return Float.NaN;
    }

    public void print() {
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }
}

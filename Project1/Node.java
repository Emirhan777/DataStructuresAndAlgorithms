// create Node class to design the structure of the AVL Tree Node
class Node {
    float element;
    String name;
    int h;  //for height
    int nullZeroOrLeaf;  //-1 0 or 1
    Node leftChild;
    Node rightChild;

    //default constructor to create null node
    public Node() {
        name=null;
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
        nullZeroOrLeaf=0;
    }

    // parameterized constructor
    public Node(float element) {
        name=null;
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
        nullZeroOrLeaf=0;
    }

    public Node(String name, float element) {
        this.name=name;
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
        nullZeroOrLeaf=0;
    }

    public Node(String name, float element, int isLeaf) {
        this.name=name;
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
        this.nullZeroOrLeaf = isLeaf;
    }
}

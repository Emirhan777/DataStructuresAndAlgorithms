import java.util.Locale;

// this is the class for constructing the AVL Tree, includes methods regarding the AVL Tree operations (insert, remove, target, rank, divide...)
class ConstructAVLTree {
    private Node rootNode;

    //Constructor to set null value to the rootNode
    //this is the constructor of the rootNode which is initially null
    public ConstructAVLTree() {
        rootNode = null;
    }


    // create checkEmpty() method to check whether the AVL Tree is empty or not
    public boolean checkEmpty() {
        if (rootNode == null)
            return true;
        else
            return false;
    }

    //Insert
    //With this method, we insert a new node to the AVL Tree,
    //In each iteration from root node to the leaf node, output is printed as welcome
    //End of the method calls balance method which saves AVL Tree's balance property
    public void insertElement(String name, float element) {
        rootNode = insertElement(name, element, rootNode);
    }

    private Node insertElement(String name, float element, Node node) {
        //System.out.println("INSERT");
        //first we check if the node is null, if null we launch a new node and assign the node in place of the null node
        if (node == null)
            node = new Node(name, element);

        //if the searched value is smaller than the node's data element's value we go left child and apply the same method recursively
        else if (element < node.element) {
            System.out.println(node.name + " welcomed " + name);
            node.leftChild = insertElement(name, element, node.leftChild);
        }

        //if the searched value is greater than the node's data element's value we go right child and apply the same method recursively
        else if (element > node.element) {
            System.out.println(node.name + " welcomed " + name);
            node.rightChild = insertElement(name, element, node.rightChild);
        }

        //we get and assign height value of the node, height is an attribute of the node object
        node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;

        return balance(node);
    }

    //Remove
    //With this method, we insert an existing node from the AVL Tree,
    //Outputs are printed as ... left the family, replaced by ...
    //Outputs are different for cases where nobody(null node) replaces leaving node
    //End of the method calls balance method which saves AVL Tree's balance property
    public void remove(float element) {
        rootNode = remove(element, rootNode);
    }


    boolean isReplacedDuplicateRemoved=true;
    private Node remove( float element, Node node )
    {
        if( node == null ) //case of the node being null node (checked first)
            return node;   // If the node is not in the AVL Tree, the method ends

        //if the searched value is smaller than the node's data element's value we go left child and apply the same method recursively
        else if( element < node.element )
            node.leftChild = remove(element, node.leftChild);

        //if the searched value is greater than the node's data element's value we go right child and apply the same method recursively
        else if( element > node.element )
            node.rightChild = remove(element, node.rightChild);


        else
        {
            //case of the node having only one child node or no child node
            if ((node.leftChild == null) || (node.rightChild == null))
            {
                Node temporaryNode = null;
                if (temporaryNode == node.leftChild) //case of the node having only one child as left child
                    temporaryNode = node.rightChild;
                else                                 //case of the node having only one child as right child
                    temporaryNode = node.leftChild;


                if (temporaryNode == null)           //case of the node having no child
                {
                    if(isReplacedDuplicateRemoved) { //for this output to be printed, this iteration must not be for removing the duplicate node
                        System.out.println(node.name + " left the family, replaced by " + "nobody");
                    }
                    else {
                        //System.out.println("Yes correct");
                        isReplacedDuplicateRemoved=true;
                    }
                    temporaryNode = node;
                    node = null;
                }
                else { // One child case
                    if(isReplacedDuplicateRemoved) { //for this output to be printed, this iteration must not be for removing the duplicate node
                        System.out.println(node.name + " left the family, replaced by " + temporaryNode.name);
                    }
                    else {
                        //System.out.println("Yes correct");
                        isReplacedDuplicateRemoved=true;
                    }
                    node = temporaryNode; // the nonempty child node is assigned to the node
                }
            }
            else
            {
                // case of the node having two children, we replace the minimum node of the right subtree
                System.out.println(node.name + " left the family, replaced by " + findMin(node.rightChild).name);
                Node temp = findMin(node.rightChild);

                // duplicate the minimum node of the right subtree (assign the values to the leaving node)
                node.name = temp.name;
                node.element = temp.element;
                //node.h = temp.h;  //height of the node might change
                node.nullZeroOrLeaf=temp.nullZeroOrLeaf;

                // check if the replaced duplicate is removed, if not removed, duplicate removal operation has no output
                isReplacedDuplicateRemoved=false;

                // we must remove the dublicate
                node.rightChild = remove(node.element, node.rightChild);
            }
        }

        return balance( node ); // balance for AVL tree property and refresh height values
    }

    // getHeight method which gets the height of the AVL Tree or the subtree (tree beneath the input node)
    private int getHeight(Node node) {
        return node == null ? -1 : node.h;
    }

    //this method compares left node height and right node height, then returns maximum height
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {

        return Math.max(leftNodeHeight, rightNodeHeight);
    }




    //Rotations and Balance
    //rotate methods applies rotation to the tree without violating the binary search tree property
    //are used for restoring AVL Tree balance property

    //left-left case
    private Node rotateLeftChild(Node node) {
        Node node1 = node.leftChild;
        node.leftChild = node1.rightChild;
        node1.rightChild = node;
        node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        node1.h = getMaxHeight(getHeight(node1.leftChild), node.h) + 1;
        return node1;
    }

    //right-right case
    private Node rotateRightChild(Node node) {
        Node node2 = node.rightChild;
        node.rightChild = node2.leftChild;
        node2.leftChild = node;
        node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        node2.h = getMaxHeight(getHeight(node2.rightChild), node.h) + 1;
        return node2;
    }

    //left-right case, the method rotates leftwards then rightwards
    private Node doubleLeftChild(Node node) {
        node.leftChild = rotateRightChild(node.leftChild);
        return rotateLeftChild(node);
    }

    //left-right case, the method rotates leftwards then rightwards
    private Node doubleRightChild(Node node) {
        node.rightChild = rotateLeftChild(node.rightChild);
        return rotateRightChild(node);
    }


    //this method applies rotations in right orders to restore AVL Tree property
    private Node balance( Node node )
    {
        if( node == null )
            return node;

        if(  getHeight( node.leftChild ) -  getHeight( node.rightChild ) > 1 )
            if(  getHeight( node. leftChild. leftChild ) >=  getHeight( node. leftChild.rightChild ) )
                node = rotateLeftChild( node );
            else
                node = doubleLeftChild( node );
        else
        if(  getHeight( node.rightChild ) -  getHeight( node. leftChild ) > 1 )
            if(  getHeight( node.rightChild.rightChild ) >=  getHeight( node.rightChild. leftChild ) )
                node = rotateRightChild( node );
            else
                node = doubleRightChild( node );

        node.h = Math.max(  getHeight( node. leftChild ),  getHeight( node.rightChild ) ) + 1;
        return node;
    }








//Target (INTEL_TARGET)
    public boolean searchTarget(float element1, float element2) {
        return searchTarget(rootNode, element1, element2);
    }

    private boolean searchTarget(Node head, float element1, float element2) {
        boolean check = false;
        while ((head != null) && !check) {
            float headElement = head.element;
            if (element1 < headElement && element2 < headElement)
                head = head.leftChild;
            else if (element1 > headElement && element2 > headElement)
                head = head.rightChild;
            else {
                check = true;
                System.out.println("Target Analysis Result: "+ head.name + " " + String.format(Locale.US,"%.3f", (float) head.element ));
                break;
            }
            check = searchTarget(head, element1, element2);
        }
        return check;
    }



//Rank (here are old trials for INTEL_RANK)


//    public boolean searchSameRankedElements(float element) {
//        return searchSameRankedElements(rootNode, element);
//    }
//
//    private boolean searchSameRankedElements(Node head, float element) {
//        boolean check = false;
//
//        while ((head != null) && !check) {
//
//            float headElement = head.element;
//            if (element < headElement)
//                head = head.leftChild;
//            else if (element > headElement)
//                head = head.rightChild;
//            else {
//                //System.out.println("The height of the node is "+ head.h);
//                //System.out.println("The depth of the node is "+ (rootNode.h-head.h));
//                System.out.print("Rank Analysis Result: ");
//                preorderTraversalIfSameRank(head);
//                System.out.println("");
//                check = true;
//                break;
//            }
//            check = searchSameRankedElements(head, element);
//        }
//
//        return check;
//
//    }
//
//
//
//    public void preorderTraversalIfSameRank(Node searchedNode) {
//        preorderTraversalIfSameRank(rootNode, searchedNode);
//    }
//
//    private void preorderTraversalIfSameRank(Node head, Node searchedNode) {
//        if (head != null) {
//            if((rootNode.h-head.h)==(rootNode.h-searchedNode.h)) {
//                System.out.print(head.name + " " + head.element + " ");
//            }
//            preorderTraversalIfSameRank(head.leftChild, searchedNode);
//            preorderTraversalIfSameRank(head.rightChild, searchedNode);
//        }
//    }




//Rank (INTEL_RANK)


    //first, we find the level (depth) of the node whose data element value is taken as input
    private int findDepthFromRoot(Node node, float element) {
        if (node == null) {
            return -1;
        }
        if (node.element == element) { //check if same
            return 0;
        } else if (element < node.element) {
            int leftDepth = findDepthFromRoot(node.leftChild, element);
            return leftDepth != -1 ? leftDepth + 1 : -1;
        } else {
            int rightDepth = findDepthFromRoot(node.rightChild, element);
            return rightDepth != -1 ? rightDepth + 1 : -1;
        }
    }

    // this method outputs nodes' elements values which has the same depth as the input node
    public void outputNodesSameDepth(float element) {

        System.out.print("Rank Analysis Result:");
        outputNodesSameDepth(rootNode, element, 0);
    }

    private void outputNodesSameDepth(Node node, float element, int depth) {
        if (node != null) {
            if (depth == findDepthFromRoot(rootNode, element)) { //check if same depth, if same depth then output
                System.out.print(" " + node.name + " ");
                System.out.print(String.format(Locale.US,"%.3f", (float) node.element ));
                //System.out.print(" ");
            }
            outputNodesSameDepth(node.leftChild, element, depth + 1);
            outputNodesSameDepth(node.rightChild, element, depth + 1);
        }
    }









//Divide
//    public int countLeafNodes() {
//        return countLeafNodes(rootNode);
//    }
//
//    private int countLeafNodes(Node node) {
//        if (node == null) {
//            return 0;
//        }
//        else if ( node.leftChild == null  && node.rightChild == null ) {
//            node.nullZeroOrLeaf=1;
//            return 1;
//        }
//
//        else if ( node.leftChild == null ) {
//            //if(node.rightChild.nullZeroOrLeaf==0){return countLeafNodes(node.rightChild);}
//            if(node.rightChild.nullZeroOrLeaf==1){return countLeafNodes(node.rightChild)+1;}
//        }
//        else if ( node.rightChild == null) {
//            //if(node.leftChild.nullZeroOrLeaf==0){return countLeafNodes(node.leftChild);}
//            if(node.leftChild.nullZeroOrLeaf==1){return countLeafNodes(node.leftChild)+1;}
//        }
//
//
//        else if ( node.leftChild.nullZeroOrLeaf==1 &&  node.rightChild.nullZeroOrLeaf==1 ){
//            node.nullZeroOrLeaf=-1;
//            return 1;
//        }
//        int leftCount = countLeafNodes(node.leftChild);
//        int rightCount = countLeafNodes(node.rightChild);
//        return leftCount + rightCount;
//    }




//Divide (INTEL_DIVIDE)

    //finds the maximum number of independent nodes, independent node means no two node is related with child-parent relation
    public int maxNumIndependentNodes(){
        beginTheMethod(rootNode);
        return maxNumIndependentNodes(rootNode);
    }

    static int maxNumIndependentNodes(Node root)
    {
        if (root == null)
            return 0;
        if (root.nullZeroOrLeaf != 0)
            return root.nullZeroOrLeaf;
        if (root.leftChild == null && root.rightChild == null)
            return root.nullZeroOrLeaf = 1;

        // find the independent node number below the input node
        int numBelow = maxNumIndependentNodes(root.leftChild) + maxNumIndependentNodes(root.rightChild);

        // find the independent node number below the input node plus input node
        int numBelowPlusOne = 1;


        if (root.leftChild != null)
        {
            numBelowPlusOne += (maxNumIndependentNodes(root.leftChild.leftChild) + maxNumIndependentNodes(root.leftChild.rightChild));
        }
        if (root.rightChild != null)
        {
            numBelowPlusOne += (maxNumIndependentNodes(root.rightChild.leftChild) + maxNumIndependentNodes(root.rightChild.rightChild));
        }

        // return the max value among the two options
        return root.nullZeroOrLeaf = Math.max(numBelow, numBelowPlusOne);
    }

    //this method is for beginning the method applied on the initial node (root node) and all the nodes below once at a time (max will be found)
    static void beginTheMethod(Node root) {
        if(root!=null) {
            root.nullZeroOrLeaf=0;
            beginTheMethod(root.leftChild);
            beginTheMethod(root.rightChild);
        }
    }







    /////////////////////////Additional Methods :  //////////////////////////////////////////////////////////////


    //the method that returns whether the element is in the Binary Search Tree
    public boolean isInTree(float element) {
        return isInTree(rootNode, element);
    }

    private boolean isInTree(Node node, float element) {
        boolean check = false;
        while ((node != null) && !check) {
            float headElement = node.element;
            if (element < headElement)
                node = node.leftChild;
            else if (element > headElement)
                node = node.rightChild;
            else {
                check = true;
                break;
            }
            check = isInTree(node, element);
        }
        return check;
    }



    //  method for preorder traversal of the Binary Search Tree
    public void preorderTraversal() {
        preorderTraversal(rootNode);
    }

    private void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(String.format(Locale.US,"%.3f", (float) node.element ) + " ");
            preorderTraversal(node.leftChild);
            preorderTraversal(node.rightChild);
        }
    }





    //FindMin and FindMax methods

    public float findMin( )
    {
        if( checkEmpty( ) )
            ;
        return findMin( rootNode ).element;
    }

    private Node findMin( Node node )
    {
        if( node == null )
            return node;

        while( node.leftChild != null )
            node = node.leftChild;
        return node;
    }


    public float findMax( )
    {
        if( checkEmpty( ) )
            ;
        return findMax( rootNode ).element;
    }

    private Node findMax( Node node )
    {
        if( node == null )
            return node;

        while( node.rightChild != null )
            node = node.rightChild;
        return node;
    }


}

package BinaryTrees;

public class BinaryTreeUse {
    //now let's create a print function to print our tree
    public static void PrintTree(BinaryTreeNode<Integer> root){
        //base case
        if(root == null){
            return;
        }
        System.out.println(root.data);
        PrintTree(root.left);
        PrintTree(root.right);
    }
    public static void main(String[] args) {
        //created node with data as 1
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1); // these uses constructor that we added if we don't add we have to create root node and set it's data as root.data = 1; in next line

        BinaryTreeNode<Integer> rootLeft = new BinaryTreeNode<Integer>(2); //created node with data as 2
        BinaryTreeNode<Integer> rootRight = new BinaryTreeNode<Integer>(3);//created node with data as 3
        /*By writeing below two lines what we do is we create connect left and right node to root, means we have 
         * made connections, now root will have reference of left node and reference of right node as well
         */
        root.left = rootLeft;
        root.right = rootRight;
        
        BinaryTreeNode<Integer> tworight = new BinaryTreeNode<Integer>(4);
        BinaryTreeNode<Integer> threeLeft = new BinaryTreeNode<Integer>(5);

        rootLeft.right = tworight;
        rootRight.left = threeLeft;

        PrintTree(root);
    }
}

package BinaryTrees;
//import java.Math;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import queues.QueueEmptyException;

public class RemoveLeafNode {
    public static int Height(BinaryTreeNode<Integer> root){
        if(root == null){
            return 0;
        }
        int leftheight = Height(root.left);
        int rightHeight = Height(root.right);
        return 1+ Math.max(leftheight,rightHeight);
    }
    public static boolean isBalanced(BinaryTreeNode<Integer> root){
        if(root == null){
            return true;
        }
        int leftHeight = Height(root.left);
        int rightHeight = Height(root.right);
        if(Math.abs(leftHeight-rightHeight)>1){
            return false;
        }
        boolean isLeftBalanced = isBalanced(root.left);
        boolean isRightBalanced = isBalanced(root.right);

        return isLeftBalanced && isRightBalanced;
    }
    public static BinaryTreeNode<Integer>removeLeaves(BinaryTreeNode<Integer> root){
        if(root == null){
            return null;
        }
        if(root.left == null && root.right == null){
            return null;
        }
        //removeLeaves(root.left);
        //removeLeaves(root.right);
        /*The above commented code(in line 16 & 17) will not remove leaf node it will only return null value to root(dry run it)
         * to remove we have to update root's left and right node means if we write root.left = removeLeaves(root.left)
         * than as function is called if leaf node does not have left and right node than according to code at line 13
         * it will return null and root.left will get updated to null so once it is updated to null means ur leaf node is removed
         */
        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);
        return root;  //return root because the root is still the same 
    }
    public static BinaryTreeNode<Integer> TakeInputLevelWise()throws QueueEmptyException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Root data: ");
        int rootData = sc.nextInt();
        //base case
        if(rootData == -1){
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        Queue<BinaryTreeNode<Integer>> PendingChildren = new LinkedList<BinaryTreeNode<Integer>>();

        PendingChildren.add(root);
        while(!PendingChildren.isEmpty()){
            //Below front node is created and data is added in it by removing from queue named PendingChildren
            BinaryTreeNode<Integer> front = PendingChildren.poll();
            //below is code to ask for it's left child and connect
            System.out.println("Enter Left Child of "+ front.data);
            int left = sc.nextInt();
            if(left != -1){
                BinaryTreeNode<Integer> LeftChild = new BinaryTreeNode<Integer>(left);
                front.left = LeftChild;
                PendingChildren.add(LeftChild);
            }
            /*above we have created a node named LeftChild if value for left is not null than it enters if loop
             * and the prev data that we stored in front by removing from queue stored in front node in that node
             * we attached to it's left[means connected it] this is for left node and left node can also have children
             * do adding in queue
             */
            //repeat same for right child
            System.out.println("Enter Right Child of "+ front.data);
            int right = sc.nextInt();
            if(right != -1){
                BinaryTreeNode<Integer> RightChild = new BinaryTreeNode<Integer>(right);
                front.right = RightChild;
                PendingChildren.add(RightChild);
            }
        }
        return root;
    }
    public static void PrintTreeDetailed(BinaryTreeNode<Integer> root){
        //base case
        if(root == null){
            return;
        }
        System.out.print(root.data + " : ");
        if(root.left!= null){
            System.out.print("L"+root.left.data + ", ");
        }
        if(root.right != null){
            System.out.print("R"+root.right.data);
        }
        System.out.println();

        PrintTreeDetailed(root.left);
        PrintTreeDetailed(root.right);
    }
    public static void main(String[] args) throws QueueEmptyException {
        BinaryTreeNode<Integer> root = TakeInputLevelWise();
        System.out.println();
        System.out.println("The Binary Tree you entered is as follow: ");
        PrintTreeDetailed(root);
        System.out.println();
        System.out.println("The Binary tree after removing leaf node is as follow: ");
        BinaryTreeNode<Integer> newRoot = removeLeaves(root);
        PrintTreeDetailed(newRoot);
    }
}

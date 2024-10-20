package BinaryTrees;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import queues.QueueEmptyException;
public class NumberOfNodesInBinaryTree {
    //Below is the function that will take input level wise
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
    //Below is function to count total number of nodes present in Binary Tree
    public static int numNodes(BinaryTreeNode<Integer> root){
        //base case
        if(root == null){
            return 0;
        }
        int leftCount = numNodes(root.left);
        int rightCount = numNodes(root.right);
        //Hypothesis is 1+ it will return 1 for root in similar way due to recurssion child will act as root and return 1 of itself at end everything will be added
        return 1+ leftCount + rightCount;
    }
    public static BinaryTreeNode<Integer> TakeTreeInputBetter(boolean isRoot, int parentData, boolean isLeft){
        if(isRoot){
            System.out.println("Enter root data: ");
        }else{
            if(isLeft){
                System.out.println("Enter Left child of "+ parentData);
            }else{
                System.out.println("Enter Right child of " + parentData);
            }
        }
        Scanner sc = new Scanner(System.in);
        int rootData = sc.nextInt();
        if(rootData == -1){
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer> (rootData);
        BinaryTreeNode<Integer> leftChild = TakeTreeInputBetter(false,rootData,true);
        BinaryTreeNode<Integer> rightChild = TakeTreeInputBetter(false,rootData,false);
        root.left = leftChild;
        root.right = rightChild;
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
    public static void main(String[] args) throws QueueEmptyException{
        //BinaryTreeNode<Integer> root = TakeTreeInputBetter(true,0,false);
        BinaryTreeNode<Integer> root = TakeInputLevelWise();
        System.out.println();
        System.out.println("The Binary Tree you entered is as follow: ");
        PrintTreeDetailed(root);
        System.out.println();
        System.out.println("Number of nodes for given binary tree is: "+ numNodes(root));
    }
}

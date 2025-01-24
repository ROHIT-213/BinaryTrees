package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import queues.QueueEmptyException;
/*Problem statement
Asked in companies like paytm and Acko

For a given Binary of type integer, find and return the ‘Diameter’.

Diameter of a Tree
The diameter of a tree can be defined as the maximum distance between two leaf nodes.
Here, the distance is measured in terms of the total number of nodes present along the path of the two leaf nodes, including both the leaves. */
public class DiameterOfBinaryTree {
    public static int height(BinaryTreeNode<Integer> root){
        if(root == null){
            return 0;
        }
        int Leftheight = height(root.left);
        int Rightheight = height(root.right);
        return 1+Math.max(Leftheight, Rightheight);
    }
    public static int diameterofBinaryTree(BinaryTreeNode<Integer> root){
        //base case if tree is empty than tree's diameter is 0
        if(root == null){
            return 0;
        }
        //calculate diameter consedering all three possibilities
        int DiameterThroughRoot = 1+height(root.left)+height(root.right);
        int DiameterInLeftSubtree = diameterofBinaryTree(root.left);
        int DiameterInRightSubtree = diameterofBinaryTree(root.right);
        return Math.max(Math.max(DiameterInLeftSubtree, DiameterInRightSubtree),DiameterThroughRoot);
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
        int d = diameterofBinaryTree(root);
        System.out.println();
        System.out.println("The Binary Tree you entered is as follow: ");
        PrintTreeDetailed(root);
        System.out.println();
        System.out.println("Diameter is: " + d);
    }
}

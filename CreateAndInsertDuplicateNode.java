package BinaryTrees;
/*
 * Problem statement (Easy)
For a given a Binary Tree of type integer, duplicate every node of the tree and attach it to the left of itself.

The root will remain the same. So you just need to insert nodes in the given Binary Tree.

 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import queues.QueueEmptyException;

public class CreateAndInsertDuplicateNode {
    public static void insertDuplicateNode(BinaryTreeNode<Integer> root){
        if(root == null){
            return;
        }
        BinaryTreeNode<Integer> OriginalLeft = root.left;
        //create a duplicate node to attach in left of the current node
        BinaryTreeNode<Integer> Duplicate = new BinaryTreeNode<Integer> (root.data);
        root.left = Duplicate;
        //Attach original left node to Duplicate's left node 
        Duplicate.left = OriginalLeft;

        insertDuplicateNode(OriginalLeft);
        insertDuplicateNode(root.right);
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
        System.out.println("Binary tree entered by you:- ");
        PrintTreeDetailed(root);
        System.out.println();
        insertDuplicateNode(root);
        System.out.println();
        System.out.println("Binary Tree With Duplicate Node is as follow:- ");
        PrintTreeDetailed(root);
    }
}

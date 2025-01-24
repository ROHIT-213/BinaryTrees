package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import queues.QueueEmptyException;

public class NewBalancedBinartTree {
    public static BalancedTreeReturn isBalancedBetter(BinaryTreeNode<Integer> root){
        if(root == null){
            int height = 0;
            boolean isbal = true;
            BalancedTreeReturn ans = new BalancedTreeReturn();
            ans.height = height;
            ans.isBalanced = isbal;
            return ans;
        }
        BalancedTreeReturn LeftOutput = isBalancedBetter(root.left);
        BalancedTreeReturn RightOutput = isBalancedBetter(root.right);
        boolean isbal = true;
        int height = 1+Math.max(LeftOutput.height, RightOutput.height);

        if(Math.abs(LeftOutput.height - RightOutput.height)>1){
            isbal = false;
        }
        if(!LeftOutput.isBalanced || !RightOutput.isBalanced){
            isbal = false;
        }

        BalancedTreeReturn ans = new BalancedTreeReturn();
        ans.height = height;
        ans.isBalanced = isbal;
        return ans;
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
        System.out.println("Is your Binary tree Balanced (true/false): "+ isBalancedBetter(root).isBalanced);
    }
}

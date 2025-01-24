package BinaryTrees;

/*
NOTE - 1.Sequence of PostOrder is {left,right,root}
       2.Sequence of InOrder is {left,root,right}
       3.Sequence of PreOrder is {root,left,right} 
Problem statement
For a given postorder and inorder traversal of a Binary Tree of type integer stored in an array/list, create the binary tree using the given two arrays/lists. You just need to construct the tree and return the root.

Note:
Assume that the Binary Tree contains only unique elements.  */

public class ConstructTreeFromPostorderAndInorder {
    public static BinaryTreeNode<Integer> buildTreeHelper(int post[],int in[],int sipost,int eipost,int siIn,int eiIn){
        if(sipost>eipost){
            return null;
        }
        int rootData = post[eipost];
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        int rootIndex = -1;
        for(int i = siIn;i<=eiIn;i++){
            if(in[i]==rootData){
                rootIndex = i;
                break;
            }
        }
        int sipostLeft = sipost;
		int siInLeft = siIn;
		int eiInLeft = rootIndex-1;
		int siInRight = rootIndex+1;;
		int eipostRight = eipost-1;
		int eiInRight = eiIn;

        int LeftSubtreeLength = eiInLeft - siInLeft+1;
        int eipostLeft = sipostLeft+LeftSubtreeLength -1;
        int sipostRight = eipostLeft +1;

        BinaryTreeNode<Integer> leftSubtree = buildTreeHelper(post, in, sipostLeft, eipostLeft, siInLeft, eiInLeft);
        BinaryTreeNode<Integer> rightSubtree = buildTreeHelper(post, in, sipostRight, eipostRight, siInRight, eiInRight);
        root.left = leftSubtree;
        root.right = rightSubtree;
        return root;
    }
    public static BinaryTreeNode<Integer> buildTree(int postOrder[], int inOrder[]){
        BinaryTreeNode<Integer> root = buildTreeHelper(postOrder, inOrder, 0, postOrder.length-1, 0, inOrder.length-1);
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
    public static void main(String[] args) {
        int postOrder[] = {4,5,2,6,7,3,1};
        int InOrder[] = {4,2,5,1,6,3,7};
        BinaryTreeNode<Integer> root = buildTree(postOrder,InOrder);
        PrintTreeDetailed(root);
    }
}

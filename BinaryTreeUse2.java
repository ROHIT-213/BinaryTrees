package BinaryTrees;
import java.util.Scanner;
public class BinaryTreeUse2 {
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
    public static BinaryTreeNode<Integer> TakeTreeInput(){
        System.out.println("Enter root data: ");
        Scanner sc = new Scanner(System.in);
        int rootData = sc.nextInt();
        if(rootData == -1){
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        BinaryTreeNode<Integer> leftChild = TakeTreeInput();
        BinaryTreeNode<Integer> rightChild = TakeTreeInput();
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
    public static void main(String[] args) {
        // BinaryTreeNode<Integer> root = TakeTreeInput();
        // PrintTreeDetailed(root);
        BinaryTreeNode<Integer> root = TakeTreeInputBetter(true, 0, true);
        PrintTreeDetailed(root);
    }
}

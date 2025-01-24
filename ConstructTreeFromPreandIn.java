package BinaryTrees;
/*
 * NOTE - 1.Sequence of PostOrder is {left,right,root}
       2.Sequence of InOrder is {left,root,right}
       3.Sequence of PreOrder is {root,left,right}
 */
public class ConstructTreeFromPreandIn {
    public static BinaryTreeNode<Integer> buildTreeFromPreInHelper(int pre[],int in[],int siPre, int eiPre,int siIn,int eiIn){
        if(siPre>eiPre){
            return null;
        }
        /*As we know in preorder start of preorder is our root so you might write rootData as pre[0]
         but that is wrong write int rootData = pre[siPre] bcs sipre is Start index of preorder and 
         in that while returning this helper function we may pass sipre as 0 or1 or 2 anything bcs preorder
         can be in any sequence or inside any sequence */
        int rootData = pre[siPre]; 
        //now you have the root create a node for root
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);

        int rootIndex = -1;
        for(int i = siIn; i<=eiIn;i++){
            if(in[i]== rootData){
                rootIndex = i;
                break;
            }
        }
        // assuming root is present in Inorder
        //now we need lot of indices
        int siPreLeft = siPre+1;
        int siInLeft = siIn;
        int eiInLeft = rootIndex-1;
        int siInRight = rootIndex+1;
        int eiPreRight = eiPre;
        int eiInRight = eiIn;

        /*If you have all eight values that is mentioned above than our work is almost done we will find Left 
         * subtree by calling recursion
         */

        int LeftSubtreeLength = eiInLeft - siInLeft +1; 
        int eiPreLeft = siPreLeft + LeftSubtreeLength -1;
        int siPreRight = eiPreLeft+1;

        BinaryTreeNode<Integer> leftSubtree = buildTreeFromPreInHelper(pre, in, siPreLeft, eiPreLeft, siInLeft, eiInLeft);
        BinaryTreeNode<Integer> rightSubtree = buildTreeFromPreInHelper(pre, in, siPreRight, eiPreRight, siInRight, eiInRight);
        root.left = leftSubtree;
        root.right = rightSubtree;
        return root;
    }
    public static BinaryTreeNode<Integer> buildTreeFromPreIn(int Pre[], int In[]){
        BinaryTreeNode<Integer> root = buildTreeFromPreInHelper(Pre, In, 0, Pre.length-1, 0, In.length-1);
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
        int in[] = {4,2,5,1,3};
        int pre[] = {1,2,4,5,3};
        BinaryTreeNode<Integer> root = buildTreeFromPreIn(pre, in);
        PrintTreeDetailed(root);
    }
}

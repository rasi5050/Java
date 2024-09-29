package com.rasi.binarytree;

public class Main {
    public static void main(String[] args) {

        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        binaryTree.insert(4);
        binaryTree.insert(2);
        binaryTree.insert(5);
        binaryTree.insert(1);
        binaryTree.insert(3);
        BinaryTree.Node<Integer> root = binaryTree.getRoot();

        binaryTree.preorderTraversal(root);
        System.out.println();
        binaryTree.inorderTraversal(root);
        System.out.println();
        binaryTree.postorderTraversal(root);
        System.out.println();


        System.out.println(binaryTree.depth(root));

        binaryTree.deleteNode(1);

        binaryTree.inorderTraversal(root);
        System.out.println();


        System.out.println(binaryTree.getRoot());

        binaryTree.insert(1);
        binaryTree.insert(1);


        binaryTree.inorderTraversal(root);
        System.out.println();

        binaryTree.deleteNode(1);

        binaryTree.inorderTraversal(root);
        System.out.println();

        for (Integer val: binaryTree) {
            System.out.println(val + " ");
        }

    }

}
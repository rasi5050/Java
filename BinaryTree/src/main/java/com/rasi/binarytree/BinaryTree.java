package com.rasi.binarytree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new LevelOrderIterator(root);
//        add more here
    }
private class LevelOrderIterator implements Iterator<T> {
    private Queue<Node<T>> queue = new Queue<>();

    public LevelOrderIterator(Node<T> root) {

        if (root != null) queue.enqueue(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException("No more elements in the tree");

        Node<T> node = queue.dequeue();

        if (node.left != null) queue.enqueue(node.left);
        if (node.right != null) queue.enqueue(node.right);
        return node.val;
    }
}
    /*if i dont declare Node as static i will have to access the node by first creating an object of parent class, then an object of node class,
        since its static, i can directly instantiate Node class

    if not static:
    BinaryTree<T> tree = new BinaryTree<>();
    BinaryTree<T>.Node<T> node = tree.new Node<>(value);  // Correct for non-static inner class


    if static:
    Node<T> node = new Node<>(value);  // Invalid without `static`
         */
    static class Node<T> {
        private T val;
        private Node<T> left, right;

        public Node(T value) {
            this.val = value;
            this.left=this.right=null;
        }
        public T getVal() {return val;}
        public Node<T> getLeft() {return left;}
        public Node<T> getRight() {return right;}

        @Override
        public String toString() {
            return val.toString();
        }
    }

    private Node<T> root;

    public BinaryTree() {
        root=null;
    }

    public void insert(T val) {
        Node<T> node = new Node<T>(val);
        if (root==null) {
            root = node;
            return;
        }

        Queue<Node<T>> queue = new Queue<>();
        queue.enqueue(root);

        while(!queue.isEmpty()) {
            Node<T> current = queue.dequeue();

            if (current.left==null) {
                current.left=node;
                break;
            } else {
                queue.enqueue(current.left);
            }

            if (current.right==null) {
                current.right=node;
                break;
            } else {
                queue.enqueue(current.right);
            }

        }
    }
    public void preorderTraversal(Node<T> node){
        if (node==null) {
            return;
        }
        System.out.print(node + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    public void inorderTraversal(Node<T> node){
        if (node==null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node + " ");
        inorderTraversal(node.right);
    }


    public void postorderTraversal(Node<T> node){
        if (node==null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node + " ");
    }

    public Node<T> getRoot() {
        return root;
    }
//    Search (Finding a Node)
    public Node<T> search(Node<T> root, T val) {
       if (root==null || root.val.equals(val))
           return root;
       Node<T> left = search(root.left, val);
       if (left!=null) return left;
       else return search(root.right, val);

    }

    public int depth(Node<T> root) {
        if (root==null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
//    delete a node
//    public void delete(Node<T> root, T val){
//        if (root==null) return;
//
//        if (root.val==val)
//
//    }

    private Node<T> findTheDeepestNode(Node<T> root) {

        Queue<Node<T>> queue = new Queue<>();
        queue.enqueue(root);
        Node<T> node = null;
        while (!queue.isEmpty()) {
            node = queue.dequeue();

            if (node.left!=null) queue.enqueue(node.left);

            if (node.right!=null) queue.enqueue(node.right);

        }
        return node;
    }

    public void deleteNode(T val){
        if (root==null) return;

//        if only single node in tree
        if(root.left == null && root.right ==null) {
          if(root.val.equals(val)) root=null;
          root=null;
          return;
        }

        Queue<Node<T>> queue = new Queue<>();
        queue.enqueue(root);
        Node<T> node = null;
        while(!queue.isEmpty()) {
            node=queue.dequeue();
            if (node.val.equals(val)){

                Node<T> deepestNode = findTheDeepestNode(root);
                node.val=deepestNode.val;
                deleteDeepestNode(root, deepestNode);
                return;
            }
            if (node.left!=null) queue.enqueue(node.left);
            if (node.right!=null) queue.enqueue(node.right);
        }
        throw new NoSuchElementException("Value " + val + " not found in the tree.");
    }

    private void deleteDeepestNode(Node<T> root, Node<T> deepestNode) {
        Queue<Node<T>> queue = new Queue<>();
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            Node<T> temp = queue.dequeue();
            if (temp.left!=null) {
                if(temp.left==deepestNode) {
                    temp.left = null;
                    return;
                }
                else {
                      queue.enqueue(temp.left);
                }
            }

            if(temp.right!=null) {
                if (temp.right==deepestNode) {
                    temp.right=null;
                    return;
                }
                else {
                    queue.enqueue(temp.right);
                }
            }
        }
    }

}

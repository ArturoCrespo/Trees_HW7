package edu.miracosta.cs113 ;

import java.util.Scanner;

public class BinaryTree<E> {

    protected Node<E> root;

    public BinaryTree() {
        root = null;
    }

    protected BinaryTree(Node<E> newRoot) {
        root = newRoot;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     *
     * @return E - The data stored at the root of the binary tree
     */
    public E getData() {
        return root.data;
    }

    /**
     * @return BinaryTree<E> - Tree representing the left subtree starting at the root
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * @return BinaryTree<E> - Tree representing the right subtree starting at the root
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /**
     * @return boolean value representing whether or not the current node is a leaf node
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * @return - String value representing the Binary Tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Recursive helper method for toString. Traverses binary tree with
     * a pre-order traversal
     *
     * @param node  - Current node traversal is on
     * @param depth - Which level the traversal is at (Used for spacing the output)
     * @param sb    - StringBuilder for appending each successive node
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Recursive method for parsing a binary tree from its String representation
     *
     * @param scan - Scanner object which points to a file containing data for binary tree
     * @return     - Binary tree read from Scanner
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node() {
            this(null);
        }

        public Node(E newData) {
            data = newData;
            left = null;
            right = null;
        }

        public String toString() {
            if (data == null) {
                return "";
            }
            return data.toString();
        }
    }
}
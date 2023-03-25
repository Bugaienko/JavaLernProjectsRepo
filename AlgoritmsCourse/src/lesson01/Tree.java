package lesson01;

/**
 * @author Sergii Bugaienko
 */

public class Tree {
    private Tree left;
    private Tree right;
    private Integer value;
    private static Tree root;

    public Tree(int value) {
        this.value = value;
    }

    public Tree() {}

    private boolean isNotExist(Tree node) {
        return node != null && node.value != null;
    }

    public  void insert (Integer value) {
        root = doInsert(root, value);
    }

    public Tree doInsert(Tree node, int value) {
        if (node == null) {
            return new Tree(value);
        } else if (value < node.value) {
            node.left = doInsert(node.left, value);
        } else {
            node.right = doInsert(node.right, value);
        }
        return node;
    }

    public Tree getRoot() {
        return root;
    }
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Tree node) {
        if (!isNotExist(node)) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.print("[" + node.value + "]");
        inOrderTraversal(node.right);
    }
    public void inDirectTraversal() {
        inDirectTraversal(root);
        System.out.println();
    }

    private void inDirectTraversal(Tree node) {
        if (!isNotExist(node)) {
            return;
        }
        System.out.print("[" + node.value + "] ");
        inDirectTraversal(node.left);
        inDirectTraversal(node.right);

    }

    public void inReverseTraversal() {
        inReverseTraversal(root);
        System.out.println();
    }
    private void inReverseTraversal(Tree node) {
        if (!isNotExist(node)) {
            return;
        }
        inReverseTraversal(node.left);
        inReverseTraversal(node.right);
        System.out.print("[" + node.value + "] ");

    }

    public Tree search(int value) {
        return search(root, value);
    }

    private Tree search(Tree node, int value) {
        if (node == null) return null;
        if (node.value == value) {
            return node;
        }

        if (value < node.value) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public Tree getMin() {
        return getMin(root);
    }

    private Tree getMin(Tree node) {
        if (!isNotExist(node)) {
            return null;
        }
        if (!isNotExist(node.left)) {
            return node;
        }

        return getMin(node.left);
    }

    public Tree getMax() {
        return getMax(root);
    }

    private Tree getMax(Tree node) {
        if (!isNotExist(node)) {
            return null;
        }
        if (!isNotExist(node.right)) {
            return node;
        }

        return getMax(node.right);
    }

    @Override
    public String toString() {
        return "Node " + value;
    }
}

package lesson01;

/**
 * @author Sergii Bugaienko
 */

public class BinaryTreeImpl {
    public static void main(String[] args) {
        Integer[] digits = {9, 3, 2, 1, 7, 6, 10, 12, 11, 14};

        Tree tree = new Tree();


        for (int i = 0; i < digits.length; i++) {
            tree.insert(digits[i]);
        }
        System.out.println("Root: " + tree.getRoot());

        System.out.println("inOrderTraversal: ");
        tree.inOrderTraversal();

        System.out.println(tree.search(12));
        System.out.println("inDirectTraversal: ");
        tree.inDirectTraversal();

        System.out.println("inReverseTraversal: ");
        tree.inReverseTraversal();

        System.out.println("Min: " + tree.getMin());
        System.out.println("Max: " + tree.getMax());
    }
}

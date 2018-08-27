package pete.eremeykin.treetraversal;

import java.util.Random;

class TreeGenerator {

    private class TreeGeneratorNode implements BinaryTreeTraversal.Node {

        private final Object value;
        TreeGeneratorNode left;
        TreeGeneratorNode right;

        public TreeGeneratorNode(Object value) {
            this.value = value;
        }

        @Override
        public TreeGeneratorNode left() {
            return left;
        }

        @Override
        public TreeGeneratorNode right() {
            return right;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }


    private final int nodes;
    private int no = 1;
    private Random random = new Random(32425);
    private boolean left = true;

    public TreeGenerator(int nodes) {
        this.nodes = nodes;
    }

    public BinaryTreeTraversal.Node generate() {
        TreeGeneratorNode root = new TreeGeneratorNode(no++);
        for (int i = 0; i < nodes; i++) {
            TreeGeneratorNode newNode = new TreeGeneratorNode(no++);
            TreeGeneratorNode randomParent = pickRandom(root);
            if (left) {
                randomParent.left = newNode;
            } else {
                randomParent.right = newNode;
            }
        }
        return root;
    }

    TreeGeneratorNode pickRandom(TreeGeneratorNode root) {
        TreeGeneratorNode current = root;
        TreeGeneratorNode next = random.nextBoolean() ? root.left() : root.right();
        while (next != null) {
            current = next;
            left = random.nextBoolean();
            next = left ? next.left : next.right;
        }
        return current;
    }


}

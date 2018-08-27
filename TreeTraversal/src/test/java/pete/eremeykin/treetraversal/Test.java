package pete.eremeykin.treetraversal;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class Test {

    private class TestNode implements BinaryTreeTraversal.Node {

        Object value;

        public TestNode(Object value) {
            this.value = value;
        }

        @Override
        public BinaryTreeTraversal.Node left() {
            switch (this.toString()) {
                case "Les":
                    return new TestNode("Cathy");
                case "Cathy":
                    return new TestNode("Alex");
                case "Sam":
                    return new TestNode("Nancy");
                case "Violet":
                    return new TestNode("Tony");
                default:
                    return null;
            }
        }

        @Override
        public BinaryTreeTraversal.Node right() {
            switch (this.toString()) {
                case "Les":
                    return new TestNode("Sam");
                case "Sam":
                    return new TestNode("Violet");
                case "Violet":
                    return new TestNode("Wendy");
                case "Cathy":
                    return new TestNode("Frank");
                default:
                    return null;
            }
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private TestNode root = new TestNode("Les");

    @org.junit.Test
    public void testSampleInorder() {
        BinaryTreeTraversal btt = new RecursiveTreeTraversal();
        Iterable<BinaryTreeTraversal.Node> res = btt.inorder(root);
        assertThat(res.toString(), equalTo("[Alex, Cathy, Frank, Les, Nancy, Sam, Tony, Violet, Wendy]"));
    }

    @org.junit.Test
    public void testSamplePostorder() {
        BinaryTreeTraversal btt = new RecursiveTreeTraversal();
        Iterable<BinaryTreeTraversal.Node> res = btt.postorder(root);
        assertThat(res.toString(), equalTo("[Alex, Frank, Cathy, Nancy, Tony, Wendy, Violet, Sam, Les]"));
    }

    @org.junit.Test
    public void testSamplePreorder() {
        BinaryTreeTraversal btt = new RecursiveTreeTraversal();
        Iterable<BinaryTreeTraversal.Node> res = btt.preorder(root);
        assertThat(res.toString(), equalTo("[Les, Cathy, Alex, Frank, Sam, Nancy, Violet, Tony, Wendy]"));
    }

}

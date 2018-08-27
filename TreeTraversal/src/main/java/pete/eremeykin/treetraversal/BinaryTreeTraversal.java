package pete.eremeykin.treetraversal;

public interface BinaryTreeTraversal {

    interface Node {

        Node left();

        Node right();

    }

    public Iterable<Node> preorder(Node node);

    public Iterable<Node> inorder(Node node);

    public Iterable<Node> postorder(Node node);


}

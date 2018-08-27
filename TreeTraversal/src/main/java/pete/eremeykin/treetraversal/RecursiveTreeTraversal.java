package pete.eremeykin.treetraversal;

import java.util.ArrayList;
import java.util.List;

public class RecursiveTreeTraversal implements BinaryTreeTraversal {


    @Override
    public Iterable<Node> preorder(Node node) {
        List<Node> res = new ArrayList<>();
        preorder(node, res);
        return res;
    }

    private void preorder(Node node, List<Node> list) {
        if (node == null)
            return;
        list.add(node);
        preorder(node.left(), list);
        preorder(node.right(), list);
    }


    @Override
    public Iterable<Node> inorder(Node node) {
        List<Node> res = new ArrayList<>();
        inorder(node, res);
        return res;
    }

    private void inorder(Node node, List<Node> list) {
        if (node == null)
            return;
        inorder(node.left(), list);
        list.add(node);
        inorder(node.right(), list);
    }

    @Override
    public Iterable<Node> postorder(Node node) {
        List<Node> res = new ArrayList<>();
        postorder(node, res);
        return res;
    }

    private void postorder(Node node, List<Node> list) {
        if (node == null) {
            return;
        }
        postorder(node.left(), list);
        postorder(node.right(), list);
        list.add(node);
    }
}

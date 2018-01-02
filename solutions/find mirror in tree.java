import java.util.*;

/*
Given a Binary tree, the problem is to find mirror of a given node. The mirror of a node is a node which exist at the mirror position of node in opposite subtree at the root.
 
                                         1
            2                                                           3
7                   4                                             5             6 


2 >< 3
7 >< 6
4 >< 5

In: root of BT
node: node to find the mirr of

Q
BT, is this BST ? No
is there a link from node to parent? No
balanced? No
Node value => irrelevant/ we don't compare node values


root : 1
node : 4

1)Search and replicate opposite
start from root, 
traverse the tree (DFT works better)
keep the track of directions
stop when reached to node.

replay the opposite of the directions

TC: n + h SC: h



unit tests

T0)  no elm tree, 

T1)  1 elem tree
tree:: 1
root : 1
n: 1
pathToNode = {1}
orderedPath = {1}

mirror = 1
current = 1
orderedPath = {}
// ret 1

T0)  node is outside of tree

*/

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("============");
        Node root = s.new Node(0);
        Node l1 = s.new Node(1);
        root.left = l1;
        Node r1 = s.new Node(2);
        root.right = r1;

        System.out.println("ANS");
        System.out.println(s.findMirror(root, l1));
        System.out.println("ANS");
        System.out.println(s.findMirror(root, r1));
    }

    class Node {
        int val;
        Node left;
        Node right;
        Boolean visited = false;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString(){
            return "Node: " + val;
        }
    }


    public Node findMirror(Node root, Node n) {
        if (root == null || n == null) {
            return null;
        }

        Stack<Node> pathToNode = getPath(root, n);

        if (pathToNode.isEmpty()) {
            return null;
        }

        // reverse the path
        Stack<Node> orderedPath = new Stack<Node>();
        while (pathToNode.isEmpty() == false) {
            orderedPath.push(pathToNode.pop());
        }

        // navigate to the mirror, mirror and current starts from root
        Node mirror = orderedPath.pop();
        Node current = mirror;
        while (orderedPath.isEmpty() == false && mirror != null) {
            Node next = orderedPath.pop();

            if (current.left == next) {
                mirror = mirror.right;
            }

            if (current.right == next) {
                mirror = mirror.left;
            }

            current = next;
        }

        return mirror;
    }

    private Stack<Node> getPath(Node root, Node n) {
        Stack<Node> st = new Stack<Node>();
        root.visited = true;
        st.push(root);

        while (st.isEmpty() == false) {
            Node current = st.peek();
            if (n == current) {
                return st;
            }

            Node univisitedChild = getUnvisitedChild(current);

            if (univisitedChild != null) {
                univisitedChild.visited = true;
                st.push(univisitedChild);
            } else {
                st.pop();
            }
        }

        return st;
    }

    private Node getUnvisitedChild(Node parent) {
        if (parent.left != null && parent.left.visited == false) {
            return parent.left;
        }

        if (parent.right != null && parent.right.visited == false) {
            return parent.right;
        }

        return null;
    }
}

import java.lang.Math;
import java.util.*;

/*

                100
        50                 150
    25      75       125          175
Convert a BST into a Doubly Linked List

Given this API signature: Node treeToList(Node root)
Implement the function.
Requirement: in-place.


1)middle & two trees
get head put in the middle


2)left first
  * get most left node
  if no right
    remove from tree 
    add to the LL
   if right
    remove from tree 
    add to the LL
    Process the right sub tree
 repeat until tree is empty

 TC:n SC:1/n


                 100
        50                 150
    25      75       125          175

    25 50 75 100 125 150 175


// flat left
flat right

        
*/


class Solution {

    class Node{
        Node left;
        Node right;
        int v;

        public Node(int n) {
            this.v = n;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Node n100 = s.new Node(100);
        Node n50 = s.new Node(50);
        Node n150 = s.new Node(150);
        n100.left = n50;
        n100.right = n150;

        Node n25 = s.new Node(25);
        Node n75 = s.new Node(75);
        n50.left = n25;
        n50.right = n75;

        treePrint(new Node[]{n100});
        Node end = treeToList(null, n100);
        lPrint(end);
    }

    private static void lPrint(Node n){
        while(n != null) {
            System.out.print(n.v);
            System.out.print(" ");
            n = n.left;
        }
        System.out.println();
    }

    private static void treePrint(Node[] n){
        ArrayList<Node> c = new ArrayList<Node>();
        for(Node k: n){
            System.out.print(k.v);
            System.out.print(" ");

            if(k.left != null) {
                c.add(k.left);
            }

            if(k.right != null) {
                c.add(k.right);
            }
        }
        System.out.println(" ");

        Node l[] = new Node[c.size()];
        l = c.toArray(l);

        if(l.length != 0) {
            treePrint(l);    
        }
    }

    /*
                100
        50                 150
    25      75       125          175
    25 50 75 100 125 150 175

    begin = null
    root = 100

    leftEnd = treeToList(null, 50); ===> 15 50 75 ++++
    rightBegin 15 50 75 100

    ret treeToList(100, 150);  ===> 15 50 75 100 125 150 ... 175


    begin = null
    root = 50
    leftEnd = treeToList(null, 25); ===> 25 +++
    rightBegin = 25 50
    ret  treeToList(50, 75); ===> 25 50 75 +++

    begin = null
    root = 25
    rightBegin = 25
    ret treeToList(25, null); => 25 ++

    begin = 50
    root = 75
    rightBegin = 50 75
    */

    public static Node treeToList(Node begin, Node root) {

        if (root == null){
            return begin;
        }
        
        if (root.left == null) {

            if(begin != null) {
                begin.right = root;
                root.left = begin;
            }
            
        } else {
            Node leftEnd = treeToList(begin, root.left);
            leftEnd.right = root;
            root.left = leftEnd;
        }
        Node rightBegin = root;

        return treeToList(rightBegin, root.right);
    }
}
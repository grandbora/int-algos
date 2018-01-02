import java.lang.Math;
import java.util.*;

/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.


Qs
is this balanced
what happens when root is 0
what happens when there ir one child, is there a path there?
    1
   / \
  2   3
        \
         6

repetitions may happen
no negatives

1)
traverse recursively all the paths
sum the paths

n: nodes
p: paths

tc: n + p  sc: max(p, depth)

*/


class Solution {

    class Node {
      String value;
      Node left;
      Node right;

      public Node(String value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
      }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        Node nrr = s.new Node("6", null, null);
        
        Node nl = s.new Node("0", null, null);
        Node nr = s.new Node("6", null, nrr);

        Node n = s.new Node("3", nl, nr);


        System.out.println(getPathSum(n));
    }

    public static Integer getPathSum(Node n) {
      List<String> paths = getPaths(n, n.value);

      int sum = 0;
      for(int i=0; i<paths.size(); i++) {
        Integer pathVal = Integer.parseInt(paths.get(i));
        sum += pathVal;
      }

      return sum;
    }

    private static ArrayList<String> getPaths(Node n, String prefix) {

      ArrayList results = new ArrayList<String>();

      //leaf
      if(n.left == null && n.right == null) {
        results.add(prefix);
        return results;
      }

      //parent
      if(n.left != null) {
        String newPrefix = prefix + n.left.value; // TODO: use String builder..
        results.addAll(getPaths(n.left, newPrefix));
      }

      if(n.right != null) {
        String newPrefix = prefix + n.right.value;
        results.addAll(getPaths(n.right, newPrefix));
      }

      return results;
    }

/*
TODO:
Unit tests


t2

    1
   / \
  2   3
       \
        6

getpaths(3, 13)
getpaths(6, 136)














t1
    1
   / \
  2   3

  
  n:1
  pre="1"
  newPrefix = "12"
  results.addAll getpaths(2, 12)
  results = [12]

  newPrefix 13
  results.addAll(getPaths(3, 13));
  RETURN results = [12, 13]



  getpaths(2, 12):
  RET [12]

  getpaths(3, 13):
  RET [13]




edge cases

*/

}

